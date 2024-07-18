package com.greatgame.skills;

import com.greatgame.creature.ConcreteCreature;
import com.greatgame.entities.Characteristic;
import com.greatgame.entities.Creature;
import com.greatgame.entities.Skill;
import org.junit.jupiter.api.Test;

import static com.greatgame.skills.ConcreteSkill.skillFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StandardBonusCalculatorTest {

    @Test
    public void testCalculate() {
        SkillInitializer.initializeSkills();
        Skill skill = skillFactory.create("fencing");
        Creature creature = new ConcreteCreature();
        creature.setCharacteristic(Characteristic.Physique, 15);
        skill.levelUp(creature);
        assertEquals(skill.getBonus(creature), 3);
        skill = skillFactory.create("archery");
        creature.setCharacteristic(Characteristic.Agility, 15);
        skill.levelUp(creature);
        skill.levelUp(creature);
        assertEquals(skill.getBonus(creature), 4);
    }
}
