package com.greatgame.skills;

import com.greatgame.creature.ConcreteCreature;
import com.greatgame.entities.Characteristic;
import com.greatgame.entities.Creature;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class VitalityUpGraderTest {

    @Test
    public void testUpgrade() {
        SkillInitializer.initializeSkills();
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
