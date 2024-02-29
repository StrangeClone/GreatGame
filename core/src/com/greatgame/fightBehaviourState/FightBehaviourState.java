package com.greatgame.fightBehaviourState;

import com.greatgame.behaviour.BehaviourState;
import com.greatgame.behaviour.CreatureBehaviour;
import com.greatgame.environment.Action;

public abstract class FightBehaviourState extends BehaviourState {
    int actions;
    Action currentAction;
    boolean active = false;

    public FightBehaviourState(CreatureBehaviour behaviour) {
        super(behaviour);
        this.behaviour = behaviour;
    }

    public void activate() {
        active = true;
        actions = 2;
    }

    @Override
    public void act(float delta) {
        if (currentAction != null && isActive()) {
            currentAction.update(delta);
            if(currentAction.finished()) {
                actions--;
                currentAction = null;
            }
        }
    }

    public void deactivate() {
        active = false;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isDone() {
        return actions == 0;
    }
}
