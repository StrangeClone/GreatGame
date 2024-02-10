package com.greatgame.environment;

import java.util.*;

public class RandomMap<V> {
    static public final Random randomGenerator = new Random();
    Map<V, Integer> weights;
    public RandomMap() {
        weights = new HashMap<>();
    }

    public void setWeight(V value, int weight) {
        weights.put(value, weight);
    }

    public int getWeight(V value) {
        Integer w = weights.get(value);
        return w != null ? w : 0;
    }

    public V generate() {
        Set<Map.Entry<V, Integer>> entries = weights.entrySet();
        int sum = 0;
        for(Map.Entry<V, Integer> entry : entries) {
            sum += entry.getValue();
        }
        int random = randomGenerator.nextInt(0,sum);
        for(Map.Entry<V, Integer> entry : entries) {
            if(random < entry.getValue()) {
                return entry.getKey();
            } else {
                random -= entry.getValue();
            }
        }
        return null;
    }
}
