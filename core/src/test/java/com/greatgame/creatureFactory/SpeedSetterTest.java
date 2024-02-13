package com.greatgame.creatureFactory;

import com.greatgame.entities.Creature;
import com.greatgame.itemsFactory.ItemInitializer;
import com.greatgame.skills.SkillInitializer;
import junit.framework.TestCase;

import static com.greatgame.behaviour.CreatureBehaviour.creaturesFactory;

public class SpeedSetterTest extends TestCase {

    @Override
    protected void setUp() throws Exception {
        SkillInitializer.initializeSkills();
        ItemInitializer.initializeItems();
        CreatureInitializer.initializeCreatures();
    }

    public void testModify() {
        Creature creature = creaturesFactory.create("wolf").getCreature();
        assertEquals(12, creature.getSpeed());
    }
}