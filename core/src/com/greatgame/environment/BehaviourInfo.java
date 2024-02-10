package com.greatgame.environment;

public abstract class BehaviourInfo {
    private String behaviourName;
    private int HP;

    public BehaviourInfo(String name, int HP) {
        behaviourName = name;
        this.HP = HP;
    }

    public String getBehaviourName() {
        return behaviourName;
    }

    public void setBehaviourName(String behaviourName) {
        this.behaviourName = behaviourName;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }
}
