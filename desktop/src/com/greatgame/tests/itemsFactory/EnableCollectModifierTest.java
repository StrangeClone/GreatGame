package com.greatgame.tests.itemsFactory;

import com.greatgame.creature.ConcreteCreature;
import com.greatgame.entities.Creature;
import com.greatgame.entities.Item;
import com.greatgame.itemsFactory.ItemInitializer;
import com.greatgame.tests.TestLauncher;

import static com.greatgame.behaviour.ItemBehaviour.itemsFactory;
import static com.greatgame.tests.TestLauncher.assertEquals;

public class EnableCollectModifierTest {
    public static void main(String[] args) {
        TestLauncher.launchTest("EnableCollectModifier", () -> {
            ItemInitializer.initializeItems();
            testModify();
        });
    }

    private static void testModify() {
        Creature dude = new ConcreteCreature();
        Item item = itemsFactory.create("flower").getItem();
        item.collect(dude);
        assertEquals(item, dude.getInventory().get(0), "item not collected");
    }
}
