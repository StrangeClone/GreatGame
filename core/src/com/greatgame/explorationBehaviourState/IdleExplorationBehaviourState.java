package com.greatgame.explorationBehaviourState;

import com.badlogic.gdx.math.Vector2;
import com.greatgame.behaviour.CreatureBehaviour;
import com.greatgame.environment.ModeName;

import static com.greatgame.environment.RandomMap.randomGenerator;

public class IdleExplorationBehaviourState extends ExplorationBehaviourState {
    private final Vector2 direction;
    private long nextChangeTime = System.currentTimeMillis();
    boolean moves;

    public IdleExplorationBehaviourState(CreatureBehaviour behaviour, boolean moves) {
        super(behaviour);
        direction = new Vector2();
        this.moves = moves;
    }

    @Override
    public void act(float delta) {
        if(moves && delta != 0) {
            generateDirection();
            move(delta);
        } if (behaviour.isHostile() && getEnvironment().freeView(behaviour, getEnvironment().getPlayer()) &&
                getEnvironment().dist(new Vector2(behaviour.getX(), behaviour.getY()),
                        new Vector2(getEnvironment().getPlayer().getX(),
                                getEnvironment().getPlayer().getY())) < 4 * PIXELS_PER_METER) {
            getEnvironment().triggerModeChange(ModeName.fightMode);
        }
    }

    private void move(float delta) {
        Vector2 newPosition = new Vector2(behaviour.getX(), behaviour.getY()).
                add(direction.x * delta * behaviour.getSpeed() * PIXELS_PER_METER / 3,
                        direction.y * delta * behaviour.getSpeed() * PIXELS_PER_METER / 3);
        if(!setPosition(newPosition.x, newPosition.y)) {
            direction.setZero();
        }
        if(getEnvironment().dist(newPosition, new Vector2(getEnvironment().getPlayer().getX(),
                getEnvironment().getPlayer().getY())) > PIXELS_PER_METER * 100) {
            getEnvironment().getStage().getActors().removeValue(behaviour, true);
        }
    }

    private void generateDirection() {
        Vector2 playerPosition = new Vector2(getEnvironment().getPlayer().getX(), getEnvironment().getPlayer().getY());
        Vector2 thisPosition = new Vector2(behaviour.getX(), behaviour.getY());
        if (!behaviour.isHostile() &&
                getEnvironment().freeView(behaviour, getEnvironment().getPlayer()) &&
                getEnvironment().dist(thisPosition, playerPosition) < PIXELS_PER_METER * 4) {
            direction.set(thisPosition.sub(playerPosition)).nor();
        } else if (System.currentTimeMillis() > nextChangeTime) {
            nextChangeTime += randomGenerator.nextLong(1, 6) * 1000;
            if (randomGenerator.nextBoolean()) {
                double randomAngle = randomGenerator.nextDouble(0,Math.PI * 2);
                Vector2 randomDirection = new Vector2((float) Math.sin(randomAngle), (float) Math.cos(randomAngle));
                direction.set(randomDirection).nor();
            } else {
                direction.setZero();
            }
        }
    }
}