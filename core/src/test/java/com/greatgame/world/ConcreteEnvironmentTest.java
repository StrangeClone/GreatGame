package com.greatgame.world;

import com.badlogic.gdx.math.Vector2;
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
        assertTrue(environment.allowedPosition(behaviour2, 110, 100));
        assertFalse(environment.allowedPosition(behaviour2, 0, 0));
        assertFalse(environment.allowedPosition(behaviour2, 0, 50));
        assertFalse(environment.allowedPosition(behaviour2, -25, 50));
        assertTrue(environment.allowedPosition(behaviour1, 20, 50));
    }

    public void testFreeView() {
        Environment environment = new ConcreteEnvironment();
        Behaviour behaviour = new CreatureBehaviour(null, new ConcreteCreature());
        environment.addBehaviour(behaviour);
        behaviour.setHeight(100);
        behaviour.setWidth(100);
        assertFalse(environment.freeView(new Vector2(0, 0), new Vector2(50, 100)));
        assertFalse(environment.freeView(new Vector2(0, 0), new Vector2(0, 100)));
        assertFalse(environment.freeView(new Vector2(-100, 50), new Vector2(0, 50)));
        assertTrue(environment.freeView(new Vector2(-100, 50), new Vector2(-70, 50)));
        assertTrue(environment.freeView(new Vector2(100, 0), new Vector2(0, 100)));
        assertFalse(environment.freeView(new Vector2(-100, 50), new Vector2(40, 50)));
        assertFalse(environment.freeView(new Vector2(-100, 0), new Vector2(0, -50)));
        assertFalse(environment.freeView(new Vector2(-100, 0), new Vector2(0, -60)));
    }

    public void testCheckContents() {

    }
}