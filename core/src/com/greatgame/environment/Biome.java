package com.greatgame.environment;

public interface Biome {
    BiomeNames getName();

    void generate(Environment environment, Location location, long seed);
}
