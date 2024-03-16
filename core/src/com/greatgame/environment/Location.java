package com.greatgame.environment;

import java.util.*;

public class Location {
    public final int x, y;
    public final Biome biome;
    public final Structure structure;
    private final Map<String, BehaviourInfo> behavioursInfo;
    private int screenX, screenY;

    public Location(int x, int y, Biome biome, Structure structure) {
        this.x = x;
        this.y = y;
        this.biome = biome;
        this.structure = structure;
        behavioursInfo = new HashMap<>();
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
        behavioursInfo.put(info.getBehaviourName(), info);
    }

    public Map<String, BehaviourInfo> getBehaviourInfo() {
        return behavioursInfo;
    }

    public Biome getBiome() {
        return biome;
    }

    public Structure getStructure() {
        return structure;
    }

    public void generate(long seed, Environment environment, int x, int y) {
        long s = (seed + x) * 23 + y;
        if(structure != null) {
            structure.generate(environment, this, s);
        }
        biome.generate(environment, this, s);
        for (BehaviourInfo current : behavioursInfo.values()) {
            Behaviour b = findBehaviour(environment, current.behaviourName);
            if (!current.apply(b)) {
                b.remove();
            }
        }
    }

    private Behaviour findBehaviour(Environment environment, String name) {
        return environment.getStage().getRoot().findActor(name);
    }

    @Override
    public String toString() {
        String result = "[position: (" + x + "," + y + ") " + biome.getName() + " ";
        if (structure != null) {
            result += structure.getName() + " ";
        }
        result += "screen: (" + screenX + "," + screenY + ")]";
        return result;
    }
}
