package com.greatgame.behaviour;

public abstract class BehaviourState {
    protected CreatureBehaviour behaviour;
    void changeBehaviour(BehaviourState newState) {
        behaviour.state = newState;
    }

    public abstract void act(float delta);
}
