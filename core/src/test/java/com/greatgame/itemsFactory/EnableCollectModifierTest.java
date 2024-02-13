package com.greatgame.itemsFactory;

import com.greatgame.creature.ConcreteCreature;
import com.greatgame.entities.Creature;
import com.greatgame.entities.Item;
import com.greatgame.items.ConcreteItem;
import junit.framework.TestCase;

import static com.greatgame.behaviour.ItemBehaviour.itemsFactory;

public class EnableCollectModifierTest extends TestCase {

    @Override
    protected void setUp() throws Exception {
        ItemInitializer.initializeItems();
    }

    public void testModify() {
        Creature dude = new ConcreteCreature();
        Item item = itemsFactory.create("flower").getItem();
        item.collect(dude);
        assertEquals(item, dude.getInventory().get(0));
    }
}