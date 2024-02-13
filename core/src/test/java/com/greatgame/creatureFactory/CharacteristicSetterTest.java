package com.greatgame.creatureFactory;

import com.greatgame.entities.Characteristic;
import com.greatgame.entities.Creature;
import com.greatgame.itemsFactory.ItemInitializer;
import com.greatgame.skills.SkillInitializer;
import junit.framework.TestCase;

import static com.greatgame.behaviour.CreatureBehaviour.creaturesFactory;

public class CharacteristicSetterTest extends TestCase {

    @Override
    protected void setUp() throws Exception {
        SkillInitializer.initializeSkills();
        ItemInitializer.initializeItems();
        CreatureInitializer.initializeCreatures();
    }

    public void testModify() {
        Creature creature = creaturesFactory.create("bandit").getCreature();
        assertEquals(1, creature.getCharacteristicBonus(Characteristic.Physique));
        assertEquals(1, creature.getCharacteristicBonus(Characteristic.Agility));
    }
}