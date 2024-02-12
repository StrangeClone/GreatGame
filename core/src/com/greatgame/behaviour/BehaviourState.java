package com.greatgame.behaviour;

public abstract class BehaviourState {
    private CreatureBehaviour behaviour;
    void changeBehaviour(BehaviourState newState) {
        behaviour.state = newState;
    }

    public abstract void act(float delta);
}
