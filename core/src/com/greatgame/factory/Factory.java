package com.greatgame.factory;

import java.util.HashMap;
import java.util.Map;

public class Factory<E> {
    private final Map<String, Pattern<E>> patterns;
    public Factory() {
        patterns = new HashMap<>();
    }
    public void addPattern(Pattern<E> pattern) {
        patterns.put(pattern.getName(), pattern);
    }

    public final E create(String name) {
        Pattern<E> pattern = patterns.get(name);
        if(pattern == null) {
            throw new IllegalArgumentException("Pattern named " + name + " not found");
        }
        return pattern.build();
    }
}
