package com.greatgame.creatureFactory;

import com.greatgame.entities.Creature;
import com.greatgame.itemsFactory.ItemInitializer;
import com.greatgame.skills.SkillInitializer;
import junit.framework.TestCase;

import static com.greatgame.behaviour.CreatureBehaviour.creaturesFactory;

public class SkillGiverTest extends TestCase {

    @Override
    protected void setUp() throws Exception {
        SkillInitializer.initializeSkills();
        ItemInitializer.initializeItems();
        CreatureInitializer.initializeCreatures();
    }

    public void testModify() {
        Creature creature = creaturesFactory.create("bandit").getCreature();
        assertEquals(1, creature.getLevel("fencing"));
        assertEquals(1, creature.getLevel("vitality"));
    }
}