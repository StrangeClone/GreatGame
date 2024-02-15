package com.greatgame.environment;

public interface Structure {
    StructureNames getName();
    void generate(Environment environment, Location location, long seed);
}
