package com.greatgame.tests.itemsFactory;

import com.greatgame.creature.ConcreteCreature;
import com.greatgame.entities.Creature;
import com.greatgame.entities.Item;
import com.greatgame.entities.ItemSlot;
import com.greatgame.itemsFactory.ItemInitializer;
import com.greatgame.tests.TestLauncher;

import static com.greatgame.behaviour.ItemBehaviour.itemsFactory;
import static com.greatgame.tests.TestLauncher.assertEquals;

public class WeaponModifierTest {
    public static void main(String[] args) {
        TestLauncher.launchTest("WeaponModifier", () -> {
            ItemInitializer.initializeItems();
            testModify();
        });
    }

    private static void testModify() {
        Creature dude = new ConcreteCreature();
        Item sword = itemsFactory.create("short sword").getItem();
        sword.equip(dude);
        assertEquals(sword, dude.getItem(ItemSlot.Primary), "Sword not equipped");
    }
}
