package com.greatgame.environment;

import java.util.List;

public interface Structure {
    StructureNames getName();
    List<Behaviour> generate(int seed);
}
