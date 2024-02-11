package com.greatgame.items;

import com.greatgame.creature.ConcreteCreature;
import com.greatgame.entities.ItemSlot;
import com.greatgame.entities.Creature;
import com.greatgame.entities.Item;
import junit.framework.TestCase;

import java.util.function.Predicate;

public class ArmorTest extends TestCase {

    public void testEquip() {
        Creature dude = new ConcreteCreature();
        Item helm = new ConcreteItem(10, 10, 0, null, new Armor(ItemSlot.Head, 3));
        helm.equip(dude);
        assertSame(dude.getItem(ItemSlot.Head), helm);
        assertEquals(11, dude.getAC());
        Item chainMail = new ConcreteItem(10, 10, 0, null, new Armor(ItemSlot.Chest, 3));
        chainMail.equip(dude);
        assertSame(chainMail, dude.getItem(ItemSlot.Chest));
        assertEquals(14, dude.getAC());

        assertThrownException(() -> chainMail.equip(dude), e -> e instanceof IllegalStateException);
        assertThrownException(() -> {
            Item helm2 = new ConcreteItem(10, 10, 0, null, new Armor(ItemSlot.Head, 1));
            helm2.equip(dude);
        }, e -> e instanceof IllegalStateException);

        helm.unEquip();
        assertNull(dude.getItem(ItemSlot.Head));
        assertEquals(11, dude.getAC());
        chainMail.unEquip();
        assertNull(dude.getItem(ItemSlot.Chest));
        assertEquals(8, dude.getAC());
    }

    private void assertThrownException(Runnable runnable, Predicate<Exception> predicate) {
        boolean thrown = false;
        try {
            runnable.run();
        } catch (Exception e) {
            if(predicate != null) {
                thrown = predicate.test(e);
            } else {
                thrown = true;
            }
        }
        assertTrue(thrown);
    }
}