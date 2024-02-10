package com.greatgame.environment;

import java.util.*;

public class Location {
    public final int x, y;
    public final Biome biome;
    public final Structure structure;
    private final Map<String, BehaviourInfo> behaviourInfos;

    public Location(int x, int y, Biome biome, Structure structure) {
        this.x = x;
        this.y = y;
        this.biome = biome;
        this.structure = structure;
        behaviourInfos = new HashMap<>();
    }

    public void addInfo(BehaviourInfo info) {
        behaviourInfos.put(info.getBehaviourName(), info);
    }

    public Map<String, BehaviourInfo> getBehaviourInfos() {
        return behaviourInfos;
    }
}
