package com.greatgame.tests.creatureFactory;

import com.greatgame.creatureFactory.CreatureInitializer;
import com.greatgame.entities.Creature;
import com.greatgame.entities.ItemSlot;
import com.greatgame.itemsFactory.ItemInitializer;
import com.greatgame.skills.SkillInitializer;
import com.greatgame.tests.TestLauncher;

import static com.greatgame.behaviour.CreatureBehaviour.creaturesFactory;
import static com.greatgame.tests.TestLauncher.assertEquals;

public class ItemEquipperTest {
    public static void main(String[] args) {
        TestLauncher.launchTest("ItemEquipper", () -> {
            SkillInitializer.initializeSkills();
            ItemInitializer.initializeItems();
            CreatureInitializer.initializeCreatures();
            Creature creature = creaturesFactory.create("bandit").getCreature();
            assertEquals("helm", creature.getItem(ItemSlot.Head).getType(), "helm not equipped");
            assertEquals("leather armor", creature.getItem(ItemSlot.Chest).getType(), "armor not equipped");
            assertEquals("short sword", creature.getItem(ItemSlot.Primary).getType(), "sword not equipped");
        });
    }
}
