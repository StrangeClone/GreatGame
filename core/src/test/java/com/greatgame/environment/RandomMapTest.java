package com.greatgame.environment;

import junit.framework.TestCase;

public class RandomMapTest extends TestCase {
    RandomMap<String> stringRandomMap;
    @Override
    protected void setUp() {
        stringRandomMap = new RandomMap<>();
        stringRandomMap.setWeight("Hello", 1);
        stringRandomMap.setWeight("World!", 2);
        stringRandomMap.setWeight("GreatGame", 1);
    }

    public void testGetWeight() {
        assertEquals(stringRandomMap.getWeight("Hello"), 1);
        assertEquals(stringRandomMap.getWeight("World!"), 2);
        assertEquals(stringRandomMap.getWeight("Dude"), 0);
    }

    public void testGenerate() {
        int v1 = 0, v2 = 0, v3 = 0;
        int tests = 100000;
        for(int i = 0; i < tests; i++) {
            String value = stringRandomMap.generate();
            assertNotNull(value);
            switch (value) {
                case "Hello":
                    v1++;
                    break;
                case "World!":
                    v2++;
                    break;
                case "GreatGame":
                    v3++;
                    break;
            }
        }
        float p1 = (float) v1 / tests, p2 = (float) v2 / tests, p3 = (float) v3 / tests;
        assertTrue(p1 > 0.24 && p1 < 0.26);
        assertTrue(p2 > 0.47 && p1 < 0.52);
        assertTrue(p3 > 0.24 && p3 < 0.26);
    }
}