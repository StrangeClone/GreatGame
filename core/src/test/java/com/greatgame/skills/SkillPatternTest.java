package com.greatgame.skills;

import com.greatgame.entities.Skill;
import junit.framework.TestCase;

import static com.greatgame.skills.ConcreteSkill.skillFactory;

public class SkillPatternTest extends TestCase {
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        SkillInitializer.initializeSkills();
    }

    public void testBuild() {
        Skill skill = skillFactory.create("vitality");
        assertEquals("vitality", skill.getName());
        skill = skillFactory.create("fencing");
        assertEquals("fencing", skill.getName());
    }
}