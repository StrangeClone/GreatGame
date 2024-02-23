package com.greatgame.behaviour;

import com.greatgame.environment.Environment;

public abstract class BehaviourState {
    protected CreatureBehaviour behaviour;
    public BehaviourState(CreatureBehaviour behaviour) {
        this.behaviour = behaviour;
    }
    void changeBehaviour(BehaviourState newState) {
        behaviour.state = newState;
    }

    protected Environment getEnvironment() {
        return behaviour.getEnvironment();
    }

    public abstract void act(float delta);
}
