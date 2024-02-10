package com.greatgame.factory;

import junit.framework.TestCase;

import static com.greatgame.environment.RandomMap.randomGenerator;

public class FactoryTest extends TestCase {
    Factory<Integer> integerFactory;

    @Override
    protected void setUp() {
        integerFactory = new Factory<>();
        integerFactory.addPattern(new Pattern<Integer>("Even") {
            @Override
            public Integer build() {
                return randomGenerator.nextInt(0, 100) * 2;
            }
        });
        integerFactory.addPattern(new Pattern<Integer>("Odd") {
            @Override
            public Integer build() {
                return randomGenerator.nextInt(0, 100) * 2 + 1;
            }
        });
    }

    public void testCreate() {
        for(int i = 0; i < 1000; i++) {
            assertEquals(0, integerFactory.create("Even") % 2);
            assertEquals(1, integerFactory.create("Odd") % 2);
        }
        boolean thrown = false;
        try {
            integerFactory.create("Hello");
        }catch (IllegalArgumentException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }
}