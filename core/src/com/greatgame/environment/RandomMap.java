package com.greatgame.environment;

import java.util.*;

public class RandomMap<V> {
    static public final Random randomGenerator = new Random();
    Map<V, Integer> weights;
    private final Random generator;
    public RandomMap() {
        weights = new HashMap<>();
        generator = new Random();
    }
    public RandomMap(long seed) {
        weights = new HashMap<>();
        generator = new Random(seed);
    }

    public void setWeight(V value, int weight) {
        weights.put(value, weight);
    }

    public int getWeight(V value) {
        Integer w = weights.get(value);
        return w != null ? w : 0;
    }

    public V generate() {
        int sum = 0;
        for(Map.Entry<V, Integer> entry : weights.entrySet()) {
            sum += entry.getValue();
        }
        int random = generator.nextInt(0,sum);
        for(Map.Entry<V, Integer> entry : weights.entrySet()) {
            if(random < entry.getValue()) {
                return entry.getKey();
            } else {
                random -= entry.getValue();
            }
        }
        return null;
    }
}
