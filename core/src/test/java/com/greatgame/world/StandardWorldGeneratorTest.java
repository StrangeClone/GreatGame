package com.greatgame.world;

import com.greatgame.environment.Biome;
import com.greatgame.environment.BiomeNames;
import com.greatgame.environment.Location;
import junit.framework.TestCase;

public class StandardWorldGeneratorTest extends TestCase {
    public void testGenerate() {
        World world = new World(23, null);
        Location location = world.generate(0, 0);
        assertEquals(location, world.generate(0, 0));
        assertEquals(location.getBiome().getName(), BiomeNames.GrassLand);
        Location location1 = world.generate(1, 0);
        assertEquals(location1.getBiome().getName(), BiomeNames.Forest);
        Location location2 = world.generate(0, 1);
        assertEquals(location2.getBiome().getName(), BiomeNames.Forest);
        Location location3 = world.generate(1 ,1);
        assertEquals(location3.getBiome().getName(), BiomeNames.Forest);
    }
}