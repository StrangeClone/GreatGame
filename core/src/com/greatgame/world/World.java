package com.greatgame.world;

import com.badlogic.gdx.math.Vector2;
import com.greatgame.environment.Environment;
import com.greatgame.environment.Location;

import java.util.Map;

public class World {
    private final long seed;
    private final WorldGenerator generator;

    private final Environment environment;

    public World(long seed, Environment environment) {
        this.seed = seed;
        generator = new StandardWorldGenerator(seed);
        this.environment = environment;
        environment.setWorld(this);
    }
    public Location generate(int x, int y) {
        return generator.generate(x,y);
    }

    public Map<Vector2, Location> getGeneratedLocations() {
        return generator.getGeneratedLocations();
    }

    public void generateContents(int x, int y) {
        generator.generateContents(seed, environment, x, y);
    }

    public Environment getEnvironment() {
        return environment;
    }
}
