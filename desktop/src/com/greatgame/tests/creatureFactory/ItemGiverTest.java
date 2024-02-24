package com.greatgame.tests.creatureFactory;

import com.greatgame.creatureFactory.CreatureInitializer;
import com.greatgame.entities.Creature;
import com.greatgame.itemsFactory.ItemInitializer;
import com.greatgame.skills.SkillInitializer;
import com.greatgame.tests.TestLauncher;

import static com.greatgame.behaviour.CreatureBehaviour.creaturesFactory;
import static com.greatgame.tests.TestLauncher.assertEquals;

public class ItemGiverTest {
    public static void main(String[] args) {
        TestLauncher.launchTest("ItemGiver", () -> {
            SkillInitializer.initializeSkills();
            ItemInitializer.initializeItems();
            CreatureInitializer.initializeCreatures();
            Creature creature = creaturesFactory.create("bandit").getCreature();
            assertEquals("silver coins", creature.getInventory().get(0).getType(), "Coins not collected");
        });
    }
}
