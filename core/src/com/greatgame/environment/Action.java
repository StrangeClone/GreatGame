package com.greatgame.environment;

public abstract class Action {
    private final String name;
    public Action(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract boolean finished();

    abstract public boolean validate();
    public abstract void start();
    abstract public void update(float delta);
}
