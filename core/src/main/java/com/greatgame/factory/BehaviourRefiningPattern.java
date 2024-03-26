package com.greatgame.factory;

import com.greatgame.environment.Behaviour;

public abstract class BehaviourRefiningPattern {
    private final String name;
    public BehaviourRefiningPattern(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public abstract void refine(Behaviour behaviour);
}
