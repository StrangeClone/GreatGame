package com.greatgame.world;

import com.greatgame.environment.Environment;
import com.greatgame.environment.Location;

public class World {
    private final long seed;
    private final WorldGenerator generator;

    private final Environment environment;

    public World(long seed, Environment environment) {
        this.seed = seed;
        generator = new StandardWorldGenerator(seed);
        this.environment = environment;
    }
    public Location generate(int x, int y) {
        return generator.generate(x,y);
    }

    public void generateContents(int x, int y) {
        generator.generateContents(seed, environment, x, y);
    }
}
