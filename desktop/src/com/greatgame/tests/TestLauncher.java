package com.greatgame.tests;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

/**
 * Class to test code that uses Gdx and can't be run outside a Gdx window
 */
public class TestLauncher {
    public static void launchTest(String name, Runnable test) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setForegroundFPS(60);
        config.setTitle(name);
        new Lwjgl3Application(new ApplicationAdapter() {
            @Override
            public void create() {
                try {
                    test.run();
                    System.out.println("Test of " +  name + " completed successfully");
                    Gdx.app.exit();
                } catch (AssertionError error) {
                    System.out.println("Assertion failed: " + error.getMessage());
                    Gdx.app.exit();
                }
            }
        }, config);
    }

    public static void assertTrue(boolean predicate, String errorMessage) {
        if(!predicate) {
            throw new AssertionError(errorMessage);
        }
    }

    public static void assertFalse(boolean predicate, String errorMessage) {
        if(predicate) {
            throw new AssertionError(errorMessage);
        }
    }

    public static void assertEquals(Object o1, Object o2, String errorMessage) {
        if(!o1.equals(o2)) {
            throw new AssertionError(errorMessage);
        }
    }
}
