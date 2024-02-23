package com.greatgame.environment;

import java.util.*;
import java.util.stream.Collectors;

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
        return generate(randomGenerator);
    }

    public V generate(Random generator) {
        List<Map.Entry<V, Integer>> entries = weights.entrySet().stream().
                sorted(Comparator.comparing(o -> o.getKey().toString())).
                collect(Collectors.toList());
        int sum = 0;
        for(Map.Entry<V, Integer> entry : entries) {
            sum += entry.getValue();
        }
        int random = generator.nextInt(0,sum);
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
