package com.greatgame.environment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ActionTest {

    @Test
    public void testFinished() {
        Action emptyAction = new Action("empty") {
            long endTime;
            @Override
            public boolean finished() {
                return System.currentTimeMillis() > endTime;
            }

            @Override
            public boolean validate() {
                return false;
            }

            @Override
            public void start() {
                endTime = System.currentTimeMillis() + 2000;
            }

            @Override
            public void update(float delta) {}
        };
        assertEquals(emptyAction.getName(), "empty");
        emptyAction.start();
        assertFalse(emptyAction.finished());
        long start = System.currentTimeMillis();
        System.out.println(start);
        System.out.println((start + 2000));
        long current = start;
        while (current < start + 3000) {
            current = System.currentTimeMillis();
        }
        assertTrue(emptyAction.finished());
    }
}
