package com.greatgame.contentGenerators;

import com.greatgame.environment.Environment;
import com.greatgame.environment.Location;
import com.greatgame.environment.Structure;
import com.greatgame.environment.StructureNames;

public class ConcreteStructure implements Structure  {
    StructureNames structureName;
    ContentGenerator generator;
    public ConcreteStructure(StructureNames name) {
        structureName = name;
        if(structureName == StructureNames.Village) {
            generator = VillageGenerator.getInstance();
        } else if (structureName == StructureNames.Camp) {
            generator = CampGenerator.getInstance();
        }
    }
    @Override
    public StructureNames getName() {
        return structureName;
    }

    @Override
    public void generate(Environment environment, Location location, long seed) {
        generator.generate(environment, location, seed);
    }
}
