package com.greatgame.behaviour;

import com.greatgame.creature.ConcreteCreature;
import com.greatgame.environment.Location;
import com.greatgame.items.ConcreteItem;
import junit.framework.TestCase;

public class ItemBehaviourTest extends TestCase {

    public void testDamage() {
        ItemBehaviour behaviour = new ItemBehaviour(null, new ConcreteItem("", 10, 10, 0, null, null));
        behaviour.setName("thing");
        behaviour.setOriginalLocation(new Location(0, 0, null, null));
        behaviour.damage(3);
        assertEquals(7, behaviour.getItem().getHP());
    }
}