package com.greatgame.environment;

public abstract class BehaviourInfo {
    protected String behaviourName;
    protected int HP;

    public BehaviourInfo(String name, int HP) {
        behaviourName = name;
        this.HP = HP;
    }

    public String getBehaviourName() {
        return behaviourName;
    }

    public int getHP() {
        return HP;
    }

    public abstract boolean apply(Behaviour behaviour);
}
