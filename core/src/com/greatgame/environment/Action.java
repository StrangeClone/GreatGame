package com.greatgame.environment;

public abstract class Action {
    private final String name;
    protected long endTime = 0;
    public Action(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean finished() {
        return System.currentTimeMillis() > endTime;
    }

    abstract public boolean validate();
    public void start(long durationInMillis) {
        endTime = System.currentTimeMillis() + durationInMillis;
    }
    abstract public void update(float delta);
}
