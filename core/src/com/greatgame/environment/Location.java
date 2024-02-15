package com.greatgame.environment;

import java.util.*;

public class Location {
    public final int x, y;
    public final Biome biome;
    public final Structure structure;
    private final Map<String, BehaviourInfo> behaviourInfos;
    private int screenX, screenY;

    public Location(int x, int y, Biome biome, Structure structure) {
        this.x = x;
        this.y = y;
        this.biome = biome;
        this.structure = structure;
        behaviourInfos = new HashMap<>();
    }

    public void setScreenPosition(int screenX, int screenY) {
        this.screenX = screenX;
        this.screenY = screenY;
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public void addInfo(BehaviourInfo info) {
        behaviourInfos.put(info.getBehaviourName(), info);
    }

    public Map<String, BehaviourInfo> getBehaviourInfos() {
        return behaviourInfos;
    }

    public Biome getBiome() {
        return biome;
    }

    public void generate(long seed, Environment environment, int x, int y) {
        long s = (seed + x) * 23 + y;
        if(structure != null) {
            structure.generate(environment, this, s);
        }
        biome.generate(environment, this, s);
    }
}
