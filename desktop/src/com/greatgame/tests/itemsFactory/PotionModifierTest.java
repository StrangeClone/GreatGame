package com.greatgame.tests.itemsFactory;

import com.greatgame.creature.ConcreteCreature;
import com.greatgame.entities.Creature;
import com.greatgame.entities.Item;
import com.greatgame.itemsFactory.ItemInitializer;
import com.greatgame.tests.TestLauncher;

import static com.greatgame.behaviour.ItemBehaviour.itemsFactory;
import static com.greatgame.tests.TestLauncher.assertTrue;

public class PotionModifierTest {
    public static void main(String[] args) {
        TestLauncher.launchTest("PotionModifier", () -> {
            ItemInitializer.initializeItems();
            testModify();
        });
    }

    private static void testModify() {
        Creature dude = new ConcreteCreature();
        dude.setHP(3);
        Item potion = itemsFactory.create("healing potion").getItem();
        potion.use(dude);
        assertTrue(dude.getHP() == dude.getMaxHP(), "HP not increased");
    }
}
