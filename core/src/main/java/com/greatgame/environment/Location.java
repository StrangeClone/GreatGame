package com.greatgame.environment;

import com.greatgame.behaviour.CreatureBehaviourInfo;
import com.greatgame.behaviour.ItemBehaviourInfo;
import com.greatgame.contentGenerators.ConcreteBiome;
import com.greatgame.contentGenerators.ConcreteStructure;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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

    public Location(Scanner scanner) throws IOException {
        String token = scanner.next();
        if (!"position:".equals(token)) {
            throw new IOException("No position of location");
        }
        x = scanner.nextInt();
        y = scanner.nextInt();
        biome = parseBiome(scanner);
        structure = parseStructure(scanner);
        screenX = scanner.nextInt();
        screenY = scanner.nextInt();

        behavioursInfo = new HashMap<>();
        parseBehaviourInfo(scanner);
    }

    private Biome parseBiome(Scanner scanner) throws IOException {
        String token = scanner.next();
        for (BiomeNames s : BiomeNames.values()) {
            if (s.toString().equals(token)) {
                return new ConcreteBiome(s);
            }
        }
        throw new IOException("Unexpected biome in position " + x + " " + y);
    }

    private Structure parseStructure(Scanner scanner) throws IOException {
        String token = scanner.next();
        if ("screen:".equals(token)) {
            return null;
        }
        for (StructureNames s : StructureNames.values()) {
            if (s.toString().equals(token)) {
                scanner.next();
                return new ConcreteStructure(s);
            }
        }
        throw new IOException("Unexpected structure in position " + x + " " + y);
    }

    private void parseBehaviourInfo(Scanner scanner) throws IOException {
        for (String token = scanner.next(); !"over".equals(token); token = scanner.next()) {
            BehaviourInfo newInfo;
            if ("Creature".equals(token)) {
                newInfo = new CreatureBehaviourInfo(scanner);
            } else if ("Item".equals(token)) {
                newInfo = new ItemBehaviourInfo(scanner);
            } else {
                throw new IOException("Unexpected behaviour info in position " + x + " " + y);
            }
            behavioursInfo.put(newInfo.behaviourName, newInfo);
        }
    }

    public void setScreenPosition(int screenX, int screenY) {
        this.screenX = screenX;
        this.screenY = screenY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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
        StringBuilder result = new StringBuilder("position: " + x + " " + y + " " + biome.getName() + " ");
        if (structure != null) {
            result.append(structure.getName()).append(" ");
        }
        result.append("screen: ").append(screenX).append(" ").append(screenY).append(" ");
        for (BehaviourInfo info : behavioursInfo.values()) {
            result.append(info).append(" ");
        }
        result.append("over");
        return result.toString();
    }
}
