package com.greatgame.environment;

import java.util.Scanner;

public abstract class BehaviourInfo {
    protected String behaviourName;
    protected int HP;

    public BehaviourInfo(String name, int HP) {
        behaviourName = name;
        this.HP = HP;
    }

    public BehaviourInfo(Scanner scanner) {
        behaviourName = scanner.next();
        HP = scanner.nextInt();
    }

    public String getBehaviourName() {
        return behaviourName;
    }

    public int getHP() {
        return HP;
    }

    public abstract boolean apply(Behaviour behaviour);

    @Override
    public String toString() {
        return behaviourName + " " + HP;
    }
}
