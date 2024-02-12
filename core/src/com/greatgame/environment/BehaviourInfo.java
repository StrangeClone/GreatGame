package com.greatgame.environment;

import java.util.List;
import java.util.Optional;

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

    public abstract void apply(List<Behaviour> locationContents);

    protected Optional<Behaviour> getCorrespondentBehaviour(List<Behaviour> locationContents) {
        return locationContents.stream().filter(b -> b.getName().equals(behaviourName)).findAny();
    }
}
