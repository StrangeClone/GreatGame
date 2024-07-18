package com.greatgame.items;

import com.greatgame.creature.ConcreteCreature;
import com.greatgame.entities.Creature;
import com.greatgame.entities.Item;
import com.greatgame.entities.ItemSlot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

public class WeaponTest {

    @Test
    public void testEquip() {
        Creature dude = new ConcreteCreature();
        Item sword = new ConcreteItem("", 10, 10, 0, null, new WeaponManager(
                new ConcreteWeapon(8, 1, "fencing")));
        sword.equip(dude);
        assertSame(sword, dude.getItem(ItemSlot.Primary));
        sword.unEquip();
        assertNull(dude.getItem(ItemSlot.Primary));
    }
}
