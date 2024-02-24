package com.greatgame.tests.contentGenerator;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.greatgame.contentGenerators.ConcreteBiome;
import com.greatgame.contentGenerators.ConcreteStructure;
import com.greatgame.creatureFactory.CreatureInitializer;
import com.greatgame.environment.Behaviour;
import com.greatgame.environment.BiomeNames;
import com.greatgame.environment.Location;
import com.greatgame.environment.StructureNames;
import com.greatgame.itemsFactory.ItemInitializer;
import com.greatgame.skills.SkillInitializer;
import com.greatgame.tests.TestLauncher;
import com.greatgame.world.ConcreteEnvironment;
import com.greatgame.world.World;

import static com.greatgame.tests.TestLauncher.assertTrue;

public class VillageGeneratorTest {
    public static void main(String[] args) {
        TestLauncher.launchTest("VillageGeneratorTest", () -> {
            World world = initEnvironment(StructureNames.Village);
            int houses = 0, villagers = 0;
            for(Actor a : world.getEnvironment().getStage().getActors()) {
                Behaviour b = (Behaviour) a;
                if("house".equals(b.getType())) {
                    houses++;
                } else if("villager".equals(b.getType())) {
                    villagers++;
                }
            }
            assertTrue(houses >= villagers, "Error: not enough houses");
            assertTrue(houses >= 4 && houses <= 8, "Error: wrong number of houses");
        });
    }

    static World initEnvironment(StructureNames structureNames) {
        SkillInitializer.initializeSkills();
        ItemInitializer.initializeItems();
        CreatureInitializer.initializeCreatures();
        World world = new World(23, new ConcreteEnvironment());
        Location location = new Location(0, 0, new ConcreteBiome(BiomeNames.GrassLand),
                new ConcreteStructure(structureNames));
        location.generate(23, world.getEnvironment(), 0, 0);
        return world;
    }
}
