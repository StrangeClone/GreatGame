package com.greatgame.skills;

import com.greatgame.creature.ConcreteCreature;
import com.greatgame.entities.Characteristic;
import com.greatgame.entities.Creature;
import com.greatgame.entities.Skill;
import junit.framework.TestCase;

import static com.greatgame.skills.ConcreteSkill.skillFactory;

public class StandardBonusCalculatorTest extends TestCase {

    @Override
    protected void setUp() {
        SkillInitializer.initializeSkills();
    }

    public void testCalculate() {
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