package com.greatgame.fightBehaviourState;

import com.badlogic.gdx.math.Vector2;
import com.greatgame.actions.AttackAction;
import com.greatgame.actions.FightModeMovementAction;
import com.greatgame.application.Mode;
import com.greatgame.application.explorationMode.ExplorationMode;
import com.greatgame.behaviour.CreatureBehaviour;
import com.greatgame.explorationBehaviourState.IdleExplorationBehaviourState;

import static com.greatgame.explorationBehaviourState.ExplorationBehaviourState.PIXELS_PER_METER;

public class AggressiveFightBehaviourState extends FightBehaviourState {
    public AggressiveFightBehaviourState(CreatureBehaviour behaviour) {
        super(behaviour);
    }

    @Override
    public void act(float delta) {
        AttackAction attack = new AttackAction(behaviour, getEnvironment().getPlayer());
        if (currentAction == null &&
                actions > 0 &&
                !validatingMovement &&
                attack.validate()) {
            currentAction = attack;
            attack.start();
        } else {
            manageMovement();
        }
        super.act(delta);
    }

    private void manageMovement() {
        if(currentAction == null && !validatingMovement && actions > 0) {
            validatingMovement = true;
            CreatureBehaviour player = getEnvironment().getPlayer();
            Thread thread = new Thread(() -> {
                Vector2 destination;
                boolean actionStarted = false;
                if (getEnvironment().dist(behaviour.getPosition(), player.getPosition())
                        > behaviour.getSpeed() * PIXELS_PER_METER) {
                    Vector2 direction = new Vector2(player.getPosition().sub(behaviour.getPosition())).nor();
                    for (float factor = behaviour.getSpeed() * PIXELS_PER_METER; factor > 0; factor -= PIXELS_PER_METER / 2) {
                        destination = new Vector2(behaviour.getPosition()).add(new Vector2(direction).scl(factor));
                        if(validateMovementAction(destination)) {
                            actionStarted = true;
                            break;
                        }
                    }
                } else {
                    for(float angle = 0; angle < Math.PI * 2; angle += (float) Math.PI / 6) {
                        float d = player.getWidth() * 1.25f;
                        Vector2 delta = new Vector2(d * (float) Math.sin(angle), d * (float) Math.cos(angle));
                        destination = new Vector2(player.getPosition()).add(delta);
                        if(validateMovementAction(destination)) {
                            actionStarted = true;
                            break;
                        }
                    }
                }
                if (!actionStarted) {
                    actions--;
                }
            });
            thread.start();
        }
    }

    private boolean validateMovementAction(Vector2 destination) {
        FightModeMovementAction action = new FightModeMovementAction(getEnvironment(), behaviour, destination);
        if(action.validate()) {
            currentAction = action;
            action.start();
            validatingMovement = false;
            return true;
        }
        return false;
    }

    @Override
    public void changeMode(Mode newMode) {
        if (newMode instanceof ExplorationMode) {
            behaviour.setState(new IdleExplorationBehaviourState(behaviour));
        }
    }
}
