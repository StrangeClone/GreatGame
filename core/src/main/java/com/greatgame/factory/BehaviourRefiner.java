package com.greatgame.factory;

import com.greatgame.environment.Behaviour;

import java.util.HashMap;
import java.util.Map;

public class BehaviourRefiner {
    public static final BehaviourRefiner behaviourRefiner = new BehaviourRefiner();
    Map<String, BehaviourRefiningPattern> patterns;
    private BehaviourRefiner() {
        patterns = new HashMap<>();
    }
    public void addPattern(BehaviourRefiningPattern pattern) {
        patterns.put(pattern.getName(), pattern);
    }
    public void refine(Behaviour behaviour) {
        BehaviourRefiningPattern pattern = patterns.get(behaviour.getType());
        if(pattern != null) {
            pattern.refine(behaviour);
        }
    }
}
