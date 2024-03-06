package com.greatgame.world;

import com.badlogic.gdx.math.Vector2;
import com.greatgame.environment.Environment;
import com.greatgame.environment.Location;

import java.util.Map;

public interface WorldGenerator {
    Location generate(int x, int y);
    Map<Vector2, Location> getGeneratedLocations();
    void generateContents(long seed, Environment stage, int x, int y);
}
