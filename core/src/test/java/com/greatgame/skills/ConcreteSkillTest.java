package com.greatgame.skills;

import com.greatgame.creature.ConcreteCreature;
import com.greatgame.entities.Creature;
import com.greatgame.entities.Skill;
import junit.framework.TestCase;

import static com.greatgame.skills.ConcreteSkill.skillFactory;

public class ConcreteSkillTest extends TestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        SkillInitializer.initializeSkills();
    }

    public void testLevelUp() {
        Creature dude = new ConcreteCreature();
        Skill skill = skillFactory.create("fencing");
        skill.levelUp(dude);
        skill.levelUp(dude);
        assertEquals(skill.getLevel(), 2);
    }
}