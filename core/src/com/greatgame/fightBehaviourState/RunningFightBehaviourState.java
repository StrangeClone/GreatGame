package com.greatgame.fightBehaviourState;

import com.badlogic.gdx.math.Vector2;
import com.greatgame.actions.FightModeMovementAction;
import com.greatgame.application.Mode;
import com.greatgame.application.explorationMode.ExplorationMode;
import com.greatgame.behaviour.CreatureBehaviour;
import com.greatgame.explorationBehaviourState.IdleExplorationBehaviourState;

import static com.greatgame.explorationBehaviourState.ExplorationBehaviourState.PIXELS_PER_METER;

public class RunningFightBehaviourState extends FightBehaviourState {

    public RunningFightBehaviourState(CreatureBehaviour behaviour) {
        super(behaviour);
    }

    @Override
    public void act(float delta) {
        manageMovement();
        manageDeSpawn();
        super.act(delta);
    }

    private void manageMovement() {
        if(currentAction == null && !validatingMovement && actions > 0) {
            validatingMovement = true;
            Vector2 direction = new Vector2(behaviour.getPosition().sub(getEnvironment().getPlayer().getPosition())).nor();
            Thread thread = new Thread(() -> {
                for (float factor = behaviour.getSpeed() * PIXELS_PER_METER; factor > 0; factor -= PIXELS_PER_METER / 2) {
                    Vector2 destination = new Vector2(behaviour.getPosition()).add(new Vector2(direction).scl(factor));
                    FightModeMovementAction action = new FightModeMovementAction(getEnvironment(), behaviour, destination);
                    if(action.validate()) {
                        currentAction = action;
                        action.start();
                        validatingMovement = false;
                        break;
                    }
                }
            });
            thread.start();
        }
    }

    private void manageDeSpawn() {
        if (getEnvironment().dist(getEnvironment().getPlayer().getPosition(), behaviour.getPosition()) > 50 * PIXELS_PER_METER) {
            behaviour.remove();
        }
    }

    @Override
    public void changeMode(Mode newMode) {
        if (newMode instanceof ExplorationMode) {
            behaviour.setState(new IdleExplorationBehaviourState(behaviour));
        }
    }
}
