package com.greatgame.items;

import com.greatgame.creature.ConcreteCreature;
import com.greatgame.entities.Creature;
import com.greatgame.entities.Item;
import com.greatgame.entities.ItemSlot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArmorTest {

    @Test
    public void testEquip() {
        Creature dude = new ConcreteCreature();
        Item helm = new ConcreteItem("", 10, 10, 0, null, new Armor(ItemSlot.Head, 3));
        helm.equip(dude);
        assertSame(dude.getItem(ItemSlot.Head), helm);
        assertEquals(11, dude.getAC());
        Item chainMail = new ConcreteItem("", 10, 10, 0, null, new Armor(ItemSlot.Chest, 3));
        chainMail.equip(dude);
        assertSame(chainMail, dude.getItem(ItemSlot.Chest));
        assertEquals(14, dude.getAC());
        helm.unEquip();
        assertNull(dude.getItem(ItemSlot.Head));
        assertEquals(11, dude.getAC());
        chainMail.unEquip();
        assertNull(dude.getItem(ItemSlot.Chest));
        assertEquals(8, dude.getAC());
    }
}
