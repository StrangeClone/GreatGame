package com.greatgame.skills;

import com.greatgame.entities.Skill;
import org.junit.jupiter.api.Test;

import static com.greatgame.skills.ConcreteSkill.skillFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SkillPatternTest {

    @Test
    public void testBuild() {
        SkillInitializer.initializeSkills();
        Skill skill = skillFactory.create("vitality");
        assertEquals("vitality", skill.getName());
        skill = skillFactory.create("fencing");
        assertEquals("fencing", skill.getName());
    }
}
