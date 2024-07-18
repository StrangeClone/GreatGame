package com.greatgame.factory;

import org.junit.jupiter.api.Test;

import static com.greatgame.environment.RandomMap.randomGenerator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FactoryTest {
    Factory<Integer> integerFactory;

    protected void setUp() {
        integerFactory = new Factory<>();
        integerFactory.addPattern(new Pattern<>("Even") {
            @Override
            public Integer build() {
                return randomGenerator.nextInt(0, 100) * 2;
            }
        });
        integerFactory.addPattern(new Pattern<>("Odd") {
            @Override
            public Integer build() {
                return randomGenerator.nextInt(0, 100) * 2 + 1;
            }
        });
    }

    @Test
    public void testCreate() {
        setUp();
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
