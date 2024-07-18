package com.greatgame.items;

import com.greatgame.creature.ConcreteCreature;
import com.greatgame.entities.Creature;
import com.greatgame.entities.Item;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class StandardCollectManagerTest {

    @Test
    public void testCollect() {
        Creature dude = new ConcreteCreature();
        Item item = new ConcreteItem("", 10,10,0, new StandardCollectManager(), null);
        item.collect(dude);
        List<Item> inventory = dude.getInventory();
        assertEquals(inventory.get(0), item);
    }
}
