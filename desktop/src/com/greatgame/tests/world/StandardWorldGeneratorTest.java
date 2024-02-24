package com.greatgame.tests.world;

import com.greatgame.environment.BiomeNames;
import com.greatgame.environment.Location;
import com.greatgame.tests.TestLauncher;
import com.greatgame.world.ConcreteEnvironment;
import com.greatgame.world.World;

import static com.greatgame.tests.TestLauncher.assertEquals;

public class StandardWorldGeneratorTest {
    public static void main(String[] args) {
        TestLauncher.launchTest("StandardWorldGenerator", StandardWorldGeneratorTest::testGenerate);
    }

    private static void testGenerate() {
        World world = new World(23, new ConcreteEnvironment());
        Location location = world.generate(0, 0);
        assertEquals(location, world.generate(0, 0), "Error with location 0,0");
        assertEquals(location.getBiome().getName(), BiomeNames.GrassLand, "Error with biome in 0,0");
        Location location1 = world.generate(1, 0);
        assertEquals(location1.getBiome().getName(), BiomeNames.Forest, "Error with biome in 1,0");
        Location location2 = world.generate(0, 1);
        assertEquals(location2.getBiome().getName(), BiomeNames.Forest, "Error with biome in 0,1");
        Location location3 = world.generate(1 ,1);
        assertEquals(location3.getBiome().getName(), BiomeNames.Forest, "Error with biome in 1,1");
    }
}
