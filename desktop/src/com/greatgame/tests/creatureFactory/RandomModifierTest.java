package com.greatgame.tests.creatureFactory;

import com.greatgame.creatureFactory.CreatureInitializer;
import com.greatgame.entities.Creature;
import com.greatgame.itemsFactory.ItemInitializer;
import com.greatgame.skills.SkillInitializer;
import com.greatgame.tests.TestLauncher;

import static com.greatgame.behaviour.CreatureBehaviour.creaturesFactory;
import static com.greatgame.tests.TestLauncher.assertTrue;

public class RandomModifierTest {
    public static void main(String[] args) {
        TestLauncher.launchTest("RandomModifier", () -> {
            SkillInitializer.initializeSkills();
            ItemInitializer.initializeItems();
            CreatureInitializer.initializeCreatures();
            int p1 = 0, p2 = 0, p3 = 0, p4 = 0;
            for(int i = 0; i < 1000; i++) {
                Creature villager = creaturesFactory.create("villager").getCreature();
                if("short sword".equals(villager.getInventory().get(0).getType())) {
                    p1++;
                } else if("chain mail".equals(villager.getInventory().get(0).getType())) {
                    p2++;
                } else if("long bow".equals(villager.getInventory().get(0).getType())) {
                    p3++;
                } else if("healing potion".equals(villager.getInventory().get(0).getType())) {
                    p4++;
                }
            }
            assertTrue(p1 > 0 && p2 > 0 && p3 > 0 && p4 >0, "Corrupted generation");
        });
    }
}
