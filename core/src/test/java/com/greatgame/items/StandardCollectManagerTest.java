package com.greatgame.items;

import com.greatgame.creature.ConcreteCreature;
import com.greatgame.entities.Creature;
import com.greatgame.entities.Item;
import junit.framework.TestCase;

import java.util.List;

public class StandardCollectManagerTest extends TestCase {

    public void testCollect() {
        Creature dude = new ConcreteCreature();
        Item item = new ConcreteItem("", 10,10,0, new StandardCollectManager(), null);
        item.collect(dude);
        List<Item> inventory = dude.getInventory();
        assertEquals(inventory.get(0), item);
    }
}