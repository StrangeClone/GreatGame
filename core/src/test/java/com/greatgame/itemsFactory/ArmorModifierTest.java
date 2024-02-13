package com.greatgame.itemsFactory;

import com.greatgame.creature.ConcreteCreature;
import com.greatgame.entities.Creature;
import com.greatgame.entities.ItemSlot;
import com.greatgame.items.ConcreteItem;
import junit.framework.TestCase;

import static com.greatgame.behaviour.ItemBehaviour.itemsFactory;

public class ArmorModifierTest extends TestCase {

    @Override
    protected void setUp() throws Exception {
        ItemInitializer.initializeItems();
    }

    public void testModify() {
        ConcreteItem helm = (ConcreteItem) itemsFactory.create("helm").getItem();
        assertTrue(helm.canBeEquipped());

        Creature dude = new ConcreteCreature();
        helm.equip(dude);
        assertEquals(10, dude.getAC());
        assertEquals(helm, dude.getItem(ItemSlot.Head));
    }
}