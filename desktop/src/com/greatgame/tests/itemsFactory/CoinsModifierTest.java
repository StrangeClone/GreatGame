package com.greatgame.tests.itemsFactory;

import com.greatgame.creature.ConcreteCreature;
import com.greatgame.entities.Creature;
import com.greatgame.items.ConcreteItem;
import com.greatgame.itemsFactory.ItemInitializer;
import com.greatgame.tests.TestLauncher;

import static com.greatgame.behaviour.ItemBehaviour.itemsFactory;
import static com.greatgame.tests.TestLauncher.assertTrue;

public class CoinsModifierTest {
    public static void main(String[] args) {
        TestLauncher.launchTest("CoinsModifier", () -> {
            ItemInitializer.initializeItems();
            testModify();
        });
    }


    private static void testModify() {
        ConcreteItem item = (ConcreteItem) itemsFactory.create("silver coins").getItem();
        assertTrue(item.canBeCollected(), "coins can't be collected");

        Creature dude = new ConcreteCreature();
        item.collect(dude);
        assertTrue(dude.getCoins() > 0, "coins not increased");
    }
}
