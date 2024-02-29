package com.greatgame.tests.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.greatgame.behaviour.CreatureBehaviour;
import com.greatgame.creature.ConcreteCreature;
import com.greatgame.creatureFactory.CreatureInitializer;
import com.greatgame.environment.Behaviour;
import com.greatgame.environment.Environment;
import com.greatgame.itemsFactory.ItemInitializer;
import com.greatgame.skills.SkillInitializer;
import com.greatgame.tests.TestLauncher;
import com.greatgame.world.ConcreteEnvironment;
import com.greatgame.world.World;

import java.util.List;

import static com.greatgame.behaviour.CreatureBehaviour.creaturesFactory;
import static com.greatgame.behaviour.ItemBehaviour.itemsFactory;
import static com.greatgame.tests.TestLauncher.assertFalse;
import static com.greatgame.tests.TestLauncher.assertTrue;

public class ConcreteEnvironmentTest {
    public static void main(String[] args) {
        TestLauncher.launchTest("ConcreteEnvironment", () -> {
            SkillInitializer.initializeSkills();
            ItemInitializer.initializeItems();
            CreatureInitializer.initializeCreatures();
            testFreePoint();
            testAllowedPosition();
            testFreeView();
            findPathTest();
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
        assertTrue(environment.allowedPosition(behaviour2, 110, 100, false),
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

        assertFalse(environment.allowedPosition(behaviour3, 0, 0, false),
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
        b1.setPosition(x1, y1);
        b2.setPosition(x2, y2);
        if (expected) {
            assertTrue(environment.freeView(b1, b2), "Error: freeView(" + x1 + ',' + y1 + ';' + x2 + ',' + y2 + ")");
        } else {
            assertFalse(environment.freeView(b1, b2), "Error: freeView(" + x1 + ',' + y1 + ';' + x2 + ',' + y2 + ")");
        }
    }

    static private void findPathTest() {
        Environment environment = new ConcreteEnvironment();
        environment.setWorld(new World(71, environment));
        environment.checkContents(0, 0);
        environment.getStage().clear();

        CreatureBehaviour mainCreature = creaturesFactory.create("player");
        Behaviour rock1 = itemsFactory.create("big rock");
        rock1.setPosition(200, 75);
        Behaviour rock2 = itemsFactory.create("big rock");
        rock2.setPosition(200, 400);
        environment.addBehaviour(mainCreature);
        environment.addBehaviour(rock1);
        environment.addBehaviour(rock2);

        Vector2 mainPos = new Vector2(mainCreature.getX(), mainCreature.getY());

        List<Vector2> path1 = environment.findPath(mainCreature,
                mainPos, new Vector2(350, 200));
        assertFalse(path1.isEmpty(), "Error: path1 is empty");
        checkPath(environment, mainCreature, path1);

        List<Vector2> path2 = environment.findPath(mainCreature,
                mainPos, new Vector2(425, 300));
        assertFalse(path2.isEmpty(), "Error: path2 is empty");
        checkPath(environment, mainCreature, path2);

        List<Vector2> path3 = environment.findPath(mainCreature,
                mainPos, new Vector2(200, 50));
        assertTrue(path3.isEmpty(), "Error: path3 isn't empty but (200, 50) is blocked");

        environment.getStage().clear();
        environment.addBehaviour(mainCreature);
        Behaviour tree1 = itemsFactory.create("tree");
        tree1.setPosition(300, 150);
        environment.addBehaviour(tree1);
        Behaviour tree2 = itemsFactory.create("tree");
        tree2.setPosition(200, 250);
        environment.addBehaviour(tree2);
        Behaviour tree3 = itemsFactory.create("tree");
        tree3.setPosition(400, 250);
        environment.addBehaviour(tree3);
        Behaviour tree4 = itemsFactory.create("tree");
        tree4.setPosition(300, 350);
        environment.addBehaviour(tree4);
        List<Vector2> path4 = environment.findPath(mainCreature,
                mainPos, new Vector2(300, 250));
        assertTrue(path4.isEmpty(), "Error: path4 isn't empty but there's no way to reach (300, 250)");
        List<Vector2> path5 = environment.findPath(mainCreature,
                mainPos, new Vector2(1000, 1000));
        assertTrue(path5.isEmpty(), "Error: path5 isn't empty, but (1000, 1000) is too far away");
    }

    private static void checkPath(Environment environment, CreatureBehaviour creature, List<Vector2> path) {
        for (Vector2 pos : path) {
            assertTrue(environment.allowedPosition(creature, pos.x, pos.y, true),
                    "Error in position: (" + pos.x + "," + pos.y + ")");
        }
    }
}
