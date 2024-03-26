package com.greatgame.factory;

public abstract class Pattern<E> {
    String name;

    public Pattern(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract E build();
}
