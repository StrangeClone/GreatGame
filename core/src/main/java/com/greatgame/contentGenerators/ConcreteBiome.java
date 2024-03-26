package com.greatgame.contentGenerators;

import com.greatgame.environment.Biome;
import com.greatgame.environment.BiomeNames;
import com.greatgame.environment.Environment;
import com.greatgame.environment.Location;

public class ConcreteBiome implements Biome {
    BiomeNames name;
    ContentGenerator generator;
    public ConcreteBiome(BiomeNames name) {
        this.name = name;
        if(name == BiomeNames.GrassLand) {
            generator = GrassLandGenerator.getInstance();
        } else if (name == BiomeNames.Forest) {
            generator = ForestGenerator.getInstance();
        }
    }

    @Override
    public BiomeNames getName() {
        return name;
    }

    @Override
    public void generate(Environment environment, Location location, long seed) {
        generator.generate(environment, location, seed);
    }
}
