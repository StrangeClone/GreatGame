package com.greatgame.world;

import com.greatgame.environment.Environment;
import com.greatgame.environment.Location;

public interface WorldGenerator {
    Location generate(int x, int y);
    void generateContents(long seed, Environment stage, int x, int y);
}
