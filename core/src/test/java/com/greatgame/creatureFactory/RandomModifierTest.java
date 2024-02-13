package com.greatgame.creatureFactory;

import com.greatgame.entities.Creature;
import com.greatgame.itemsFactory.ItemInitializer;
import com.greatgame.skills.SkillInitializer;
import junit.framework.TestCase;

import static com.greatgame.behaviour.CreatureBehaviour.creaturesFactory;

public class RandomModifierTest extends TestCase {

    @Override
    protected void setUp() throws Exception {
        SkillInitializer.initializeSkills();
        ItemInitializer.initializeItems();
        CreatureInitializer.initializeCreatures();
    }

    public void testModify() {
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
        assertTrue(p1 > 0 && p2 > 0 && p3 > 0 && p4 >0);
    }
}