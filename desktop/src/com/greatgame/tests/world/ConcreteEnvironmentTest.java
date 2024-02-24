package com.greatgame.tests.world;

import com.badlogic.gdx.graphics.Texture;
import com.greatgame.behaviour.CreatureBehaviour;
import com.greatgame.creature.ConcreteCreature;
import com.greatgame.environment.Behaviour;
import com.greatgame.environment.Environment;
import com.greatgame.tests.TestLauncher;
import com.greatgame.world.ConcreteEnvironment;
import com.greatgame.world.World;

import static com.greatgame.tests.TestLauncher.assertFalse;
import static com.greatgame.tests.TestLauncher.assertTrue;

public class ConcreteEnvironmentTest {
    public static void main(String[] args) {
        TestLauncher.launchTest("ConcreteEnvironment", () -> {
            testFreePoint();
            testAllowedPosition();
            testFreeView();
        });
    }

    private static void testFreePoint() {
        Environment environment = new ConcreteEnvironment();
        environment.setWorld(new World(23, environment));
        Behaviour behaviour = new CreatureBehaviour(new Texture("textures/creatures/villager.png"), new ConcreteCreature());
        environment.addBehaviour(behaviour);
        behaviour.setWidth(100);
        behaviour.setHeight(100);
        assertTrue(environment.freePoint(100, 100), "error in freePoint(100, 100)");
        assertFalse(environment.freePoint(0, 0), "error in freePoint(0,0)");
        assertFalse(environment.freePoint(25, 25), "error in freePoint(25,25)");
        assertFalse(environment.freePoint(-25, -25), "error in freePoint(-25, -25)");
        assertTrue(environment.freePoint(1000, 1000), "error in freePoint(1000, 1000)");
    }

    private static void testAllowedPosition() {
        Environment environment = new ConcreteEnvironment();
        environment.setWorld(new World(45, environment));
        Behaviour behaviour1 = new CreatureBehaviour(new Texture("textures/creatures/villager.png"), new ConcreteCreature());
        environment.addBehaviour(behaviour1);
        behaviour1.setWidth(100);
        behaviour1.setHeight(100);
        Behaviour behaviour2 = new CreatureBehaviour(new Texture("textures/creatures/villager.png"), new ConcreteCreature());
        behaviour2.setWidth(100);
        behaviour2.setHeight(100);
        assertTrue (environment.allowedPosition(behaviour2, 110, 100, false),
                "Error in allowedPosition(b2, 110, 110)");
        assertFalse(environment.allowedPosition(behaviour2, 0, 0, false),
                "Error in allowedPosition(b2, 0, 0)");
        assertFalse(environment.allowedPosition(behaviour2, 0, 50, false),
                "Error in allowedPosition(b2, 0, 50)");
        assertFalse(environment.allowedPosition(behaviour2, -25, 50, false),
                "Error in allowedPosition(b2, -25, 50)");
        assertFalse(environment.allowedPosition(behaviour2, 20, 50, false),
                "Error in allowedPosition(b2, 20, 50)");
        Behaviour behaviour3 = new CreatureBehaviour(new Texture("textures/creatures/villager.png"), new ConcreteCreature());
        behaviour3.setWidth(50);
        behaviour3.setHeight(50);

        assertFalse(environment.allowedPosition(behaviour3, 0,0, false),
                "Error in allowedPosition(b3, 0, 0)");
    }

    private static void testFreeView() {
        Environment environment = new ConcreteEnvironment();
        environment.setWorld(new World(71, environment));
        Behaviour behaviour = new CreatureBehaviour(new Texture("textures/creatures/villager.png"), new ConcreteCreature());
        environment.addBehaviour(behaviour);
        behaviour.setHeight(100);
        behaviour.setWidth(100);
        Behaviour viewer = new CreatureBehaviour(new Texture("textures/creatures/villager.png"), new ConcreteCreature());
        viewer.setHeight(100);
        viewer.setWidth(100);
        Behaviour target = new CreatureBehaviour(new Texture("textures/creatures/villager.png"), new ConcreteCreature());
        target.setHeight(100);
        target.setWidth(100);
        freeViewAtomicTest(environment, viewer, 0, 0, target, 50, 100, false);
        freeViewAtomicTest(environment, viewer, 0, 0, target, 0, 100, false);
        freeViewAtomicTest(environment, viewer, -100, 50, target, 0, 50, false);
        freeViewAtomicTest(environment, viewer, -100, 50, target, -70, 50, true);
        freeViewAtomicTest(environment, viewer, 150, 0, target, 0, 150, true);
        freeViewAtomicTest(environment, viewer, -100, 100, target, 200, 0, false);
        freeViewAtomicTest(environment, viewer, -100, 50, target, 40, 50, false);
        freeViewAtomicTest(environment, viewer, -100, 0, target, 0, -50, false);
        freeViewAtomicTest(environment, viewer, -100, 0, target, 0, -60, false);
    }

    static private void freeViewAtomicTest(Environment environment,
                                           Behaviour b1, float x1, float y1,
                                           Behaviour b2, float x2, float y2,
                                           boolean expected) {
        b1.setPosition(x1,y1);
        b2.setPosition(x2,y2);
        if(expected) {
            assertTrue(environment.freeView(b1, b2), "Error: freeView(" + x1 + ',' + y1 + ';' + x2 + ',' + y2 + ")");
        } else {
            assertFalse(environment.freeView(b1, b2), "Error: freeView(" + x1 + ',' + y1 + ';' + x2 + ',' + y2 + ")");
        }
    }
}
