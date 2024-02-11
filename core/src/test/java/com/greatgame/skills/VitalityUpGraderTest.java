package com.greatgame.skills;

import com.greatgame.creature.ConcreteCreature;
import com.greatgame.entities.Characteristic;
import com.greatgame.entities.Creature;
import junit.framework.TestCase;


public class VitalityUpGraderTest extends TestCase {
    @Override
    protected void setUp() {
        SkillInitializer.initializeSkills();
    }

    public void testUpgrade() {
        Creature creature = new ConcreteCreature();
        creature.upgradeSkill("vitality");
        assertEquals(12, creature.getMaxHP());
        creature.upgradeSkill("vitality");
        assertEquals(18, creature.getMaxHP());
        creature.setCharacteristic(Characteristic.Physique, 12);
        creature.upgradeSkill("vitality");
        assertEquals(28, creature.getMaxHP());
    }
}