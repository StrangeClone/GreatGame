package com.greatgame.behaviour;

import com.greatgame.creature.ConcreteCreature;
import com.greatgame.environment.Location;
import junit.framework.TestCase;

public class CreatureBehaviourTest extends TestCase {

    public void testDamage() {
        CreatureBehaviour behaviour = new CreatureBehaviour(null, new ConcreteCreature());
        behaviour.setName("dude");
        behaviour.setOriginalLocation(new Location(0, 0, null, null));
        behaviour.damage(3);
        assertEquals(3, behaviour.getCreature().getHP());
    }
}