package com.greatgame.environment;

import java.util.List;

public interface Biome {
    BiomeNames getName();
    List<Behaviour> generate(int seed);
}
