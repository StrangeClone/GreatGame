package com.greatgame.itemsFactory;

import com.greatgame.creature.ConcreteCreature;
import com.greatgame.entities.Creature;
import com.greatgame.items.ConcreteItem;
import junit.framework.TestCase;

import static com.greatgame.behaviour.ItemBehaviour.itemsFactory;

public class CoinsModifierTest extends TestCase {

    @Override
    protected void setUp() throws Exception {
        ItemInitializer.initializeItems();
    }

    public void testModify() {
        ConcreteItem item = (ConcreteItem) itemsFactory.create("silver coins").getItem();
        assertTrue(item.canBeCollected());

        Creature dude = new ConcreteCreature();
        item.collect(dude);
        assertTrue(dude.getCoins() > 0);
    }
}