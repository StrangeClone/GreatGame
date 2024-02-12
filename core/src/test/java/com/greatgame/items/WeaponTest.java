package com.greatgame.items;

import com.greatgame.creature.ConcreteCreature;
import com.greatgame.entities.Creature;
import com.greatgame.entities.Item;
import com.greatgame.entities.ItemSlot;
import junit.framework.TestCase;

public class WeaponTest extends TestCase {

    public void testEquip() {
        Creature dude = new ConcreteCreature();
        Item sword = new ConcreteItem("", 10, 10, 0, null, new Weapon(8, 1, "fencing"));
        sword.equip(dude);
        assertSame(sword, dude.getItem(ItemSlot.Primary));
        sword.unEquip();
        assertNull(dude.getItem(ItemSlot.Primary));
    }
}