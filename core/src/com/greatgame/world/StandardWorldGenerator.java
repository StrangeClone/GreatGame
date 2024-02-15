package com.greatgame.world;

import com.badlogic.gdx.math.Vector2;
import com.greatgame.contentGenerators.ConcreteBiome;
import com.greatgame.contentGenerators.ConcreteStructure;
import com.greatgame.environment.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class StandardWorldGenerator implements WorldGenerator {
    Map<Vector2, Location> locationMap;
    Random random;

    public StandardWorldGenerator(long seed) {
        locationMap = new HashMap<>();
        random = new Random(seed);
    }

    @Override
    public Location generate(int x, int y) {
        Location l = locationMap.get(new Vector2(x, y));
        if(l != null) {
            return l;
        }

        RandomMap<BiomeNames> randomMap = new RandomMap<>();
        randomMap.setWeight(BiomeNames.GrassLand, 1);
        randomMap.setWeight(BiomeNames.Forest, 1);
        updateRandomMap(randomMap, x - 1, y);
        updateRandomMap(randomMap, x + 1, y);
        updateRandomMap(randomMap, x, y - 1);
        updateRandomMap(randomMap, x, y + 1);
        Biome biome = new ConcreteBiome(randomMap.generate(random));
        l = defineNewLocation(biome, x, y);
        return l;
    }

    private void updateRandomMap(RandomMap<BiomeNames> randomMap, int x, int y) {
        Location location = locationMap.get(new Vector2(x, y));
        if (location != null) {
            BiomeNames name = location.getBiome().getName();
            randomMap.setWeight(name, randomMap.getWeight(name) + 1);
        }
    }

    private Location defineNewLocation(Biome biome, int x, int y) {
        Structure structure = null;
        if(biome.getName() == BiomeNames.GrassLand) {
            float r = random.nextFloat(0, 1);
            if(r < 0.05f) {
                structure = new ConcreteStructure(StructureNames.Village);
            } else if (r < 0.10f) {
                structure = new ConcreteStructure(StructureNames.Camp);
            }
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
