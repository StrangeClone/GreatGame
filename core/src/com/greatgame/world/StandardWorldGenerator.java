package com.greatgame.world;

import com.badlogic.gdx.math.Vector2;
import com.greatgame.contentGenerators.ConcreteBiome;
import com.greatgame.contentGenerators.ConcreteStructure;
import com.greatgame.environment.*;

import java.util.*;

public class StandardWorldGenerator implements WorldGenerator {
    Map<Vector2, Location> locationMap;
    long seed;

    public StandardWorldGenerator(long seed) {
        locationMap = new HashMap<>();
        this.seed = seed;
    }

    @Override
    public Location generate(int x, int y) {
        Location l = locationMap.get(new Vector2(x, y));
        if(l != null) {
            return l;
        }

        RandomMap<BiomeNames> randomMap = new RandomMap<>((seed + x) * 23 + y);
        randomMap.setWeight(BiomeNames.GrassLand, 1);
        randomMap.setWeight(BiomeNames.Forest, 1);
        updateRandomMap(randomMap, x - 1, y);
        updateRandomMap(randomMap, x + 1, y);
        updateRandomMap(randomMap, x, y - 1);
        updateRandomMap(randomMap, x, y + 1);
        Biome biome = new ConcreteBiome(randomMap.generate());
        l = defineNewLocation(biome, x, y);
        return l;
    }

    @Override
    public Map<Vector2, Location> getGeneratedLocations() {
        return locationMap;
    }

    private void updateRandomMap(RandomMap<BiomeNames> randomMap, int x, int y) {
        Location location = locationMap.get(new Vector2(x, y));
        if (location != null) {
            BiomeNames name = location.getBiome().getName();
            randomMap.setWeight(name, randomMap.getWeight(name) + 1);
        }
    }

    private Location defineNewLocation(Biome biome, int x, int y) {
        Structure structure;
        if(biome.getName() == BiomeNames.GrassLand) {
            RandomMap<StructureNames> structureMap = new RandomMap<>((seed + x) * 23 + y);
            structureMap.setWeight(StructureNames.Village, 1);
            structureMap.setWeight(StructureNames.Camp, 1);
            structureMap.setWeight(null, 18);
            StructureNames name = structureMap.generate();
            structure = name != null ? new ConcreteStructure(name) : null;
        } else {
            structure = null;
        }
        Location l = new Location(x, y, biome, structure);
        locationMap.put(new Vector2(x, y), l);
        return l;
    }

    @Override
    public void generateContents(long seed, Environment stage, int x, int y) {
        locationMap.get(new Vector2(x, y)).generate(seed, stage, x, y);
    }
}
