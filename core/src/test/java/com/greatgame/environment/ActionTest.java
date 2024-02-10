package com.greatgame.environment;

import junit.framework.TestCase;

public class ActionTest extends TestCase {

    public void testFinished() {
        Action emptyAction = new Action("empty") {
            @Override
            public boolean validate() {
                return false;
            }
            @Override
            public void update(float delta) {}
        };
        assertEquals(emptyAction.getName(), "empty");
        emptyAction.start(2000);
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