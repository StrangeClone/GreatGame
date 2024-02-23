package com.greatgame.world;

import com.greatgame.behaviour.CreatureBehaviour;
import com.greatgame.creature.ConcreteCreature;
import com.greatgame.environment.Behaviour;
import com.greatgame.environment.Environment;
import junit.framework.TestCase;

public class ConcreteEnvironmentTest extends TestCase {

    public void testFreePoint() {
        Environment environment = new ConcreteEnvironment();
        Behaviour behaviour = new CreatureBehaviour(null, new ConcreteCreature());
        environment.addBehaviour(behaviour);
        behaviour.setWidth(100);
        behaviour.setHeight(100);
        assertTrue(environment.freePoint(100, 100));
        assertFalse(environment.freePoint(0, 0));
        assertFalse(environment.freePoint(25, 25));
        assertFalse(environment.freePoint(-25, -25));
        assertTrue(environment.freePoint(1000, 1000));
    }

    public void testAllowedPosition() {
        Environment environment = new ConcreteEnvironment();
        Behaviour behaviour1 = new CreatureBehaviour(null, new ConcreteCreature());
        environment.addBehaviour(behaviour1);
        behaviour1.setWidth(100);
        behaviour1.setHeight(100);
        Behaviour behaviour2 = new CreatureBehaviour(null, new ConcreteCreature());
        behaviour2.setWidth(100);
        behaviour2.setHeight(100);
        assertTrue(environment.allowedPosition(behaviour2, 110, 100, false));
        assertFalse(environment.allowedPosition(behaviour2, 0, 0, false));
        assertFalse(environment.allowedPosition(behaviour2, 0, 50, false));
        assertFalse(environment.allowedPosition(behaviour2, -25, 50, false));
        assertTrue(environment.allowedPosition(behaviour2, 20, 50, false));
        Behaviour behaviour3 = new CreatureBehaviour(null, new ConcreteCreature());
        behaviour3.setWidth(50);
        behaviour3.setHeight(50);
        assertFalse(environment.allowedPosition(behaviour3, 0,0, false));
    }

    public void testFreeView() {
        Environment environment = new ConcreteEnvironment();
        Behaviour behaviour = new CreatureBehaviour(null, new ConcreteCreature());
        environment.addBehaviour(behaviour);
        behaviour.setHeight(100);
        behaviour.setWidth(100);
        Behaviour viewer = new CreatureBehaviour(null, new ConcreteCreature());
        viewer.setHeight(100);
        viewer.setWidth(100);
        Behaviour target = new CreatureBehaviour(null, new ConcreteCreature());
        target.setHeight(100);
        target.setWidth(100);
        viewer.setPosition(0,0);
        target.setPosition(50, 100);
        assertFalse(environment.freeView(viewer, target));
        target.setPosition(0, 100);
        assertFalse(environment.freeView(viewer, target));
        viewer.setPosition(-100, 50);
        target.setPosition(0, 50);
        assertFalse(environment.freeView(viewer, target));
        viewer.setPosition(-100, 50);
        target.setPosition(-70, 50);
        assertTrue(environment.freeView(viewer, target));
        viewer.setPosition(100, 0);
        target.setPosition(0, 100);
        assertTrue(environment.freeView(target, viewer));
        viewer.setPosition(-100, 50);
        target.setPosition(40, 50);
        assertFalse(environment.freeView(viewer, target));
        viewer.setPosition(-100, 0);
        target.setPosition(0, -50);
        assertFalse(environment.freeView(target, viewer));
        target.setPosition(0, -60);
        assertFalse(environment.freeView(viewer, target));
    }

    public void testCheckContents() {

    }
}