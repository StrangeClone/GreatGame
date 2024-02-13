package com.greatgame.creatureFactory;

import com.greatgame.entities.Creature;
import com.greatgame.entities.ItemSlot;
import com.greatgame.itemsFactory.ItemInitializer;
import com.greatgame.skills.SkillInitializer;
import junit.framework.TestCase;

import static com.greatgame.behaviour.CreatureBehaviour.creaturesFactory;

public class ItemEquipperTest extends TestCase {

    @Override
    protected void setUp() throws Exception {
        SkillInitializer.initializeSkills();
        ItemInitializer.initializeItems();
        CreatureInitializer.initializeCreatures();
    }

    public void testModify() {
        Creature creature = creaturesFactory.create("bandit").getCreature();
        assertEquals("helm", creature.getItem(ItemSlot.Head).getType());
        assertEquals("leather armor", creature.getItem(ItemSlot.Chest).getType());
        assertEquals("short sword", creature.getItem(ItemSlot.Primary).getType());
    }
}