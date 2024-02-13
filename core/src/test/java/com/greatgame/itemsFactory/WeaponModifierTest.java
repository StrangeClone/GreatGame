package com.greatgame.itemsFactory;

import com.greatgame.creature.ConcreteCreature;
import com.greatgame.entities.Creature;
import com.greatgame.entities.Item;
import com.greatgame.entities.ItemSlot;
import junit.framework.TestCase;

import static com.greatgame.behaviour.ItemBehaviour.itemsFactory;

public class WeaponModifierTest extends TestCase {
    @Override
    protected void setUp() throws Exception {
        ItemInitializer.initializeItems();
    }

    public void testModify() {
        Creature dude = new ConcreteCreature();
        Item sword = itemsFactory.create("short sword").getItem();
        sword.equip(dude);
        assertEquals(sword, dude.getItem(ItemSlot.Primary));
    }
}