package com.greatgame.tests.itemsFactory;

import com.greatgame.creature.ConcreteCreature;
import com.greatgame.entities.Creature;
import com.greatgame.entities.ItemSlot;
import com.greatgame.items.ConcreteItem;
import com.greatgame.itemsFactory.ItemInitializer;
import com.greatgame.tests.TestLauncher;

import static com.greatgame.behaviour.ItemBehaviour.itemsFactory;
import static com.greatgame.tests.TestLauncher.assertTrue;

public class ArmorModifierTest {
    public static void main(String[] args) {
        TestLauncher.launchTest("ArmorModifier", () -> {
            ItemInitializer.initializeItems();
            testModify();
        });
    }

    private static void testModify() {
        ConcreteItem helm = (ConcreteItem) itemsFactory.create("helm").getItem();
        assertTrue(helm.canBeEquipped(), "Helm can't be equipped");

        Creature dude = new ConcreteCreature();
        helm.equip(dude);
        assertTrue(dude.getAC() == 10, "AC not modified");
        assertTrue(helm == dude.getItem(ItemSlot.Head), "Helm not equipped");
    }
}
