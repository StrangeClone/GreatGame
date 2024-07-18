package com.greatgame.skills;

import com.greatgame.creature.ConcreteCreature;
import com.greatgame.entities.Creature;
import com.greatgame.entities.Skill;
import org.junit.jupiter.api.Test;

import static com.greatgame.skills.ConcreteSkill.skillFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConcreteSkillTest {

    @Test
    public void testLevelUp() {
        SkillInitializer.initializeSkills();
        Creature dude = new ConcreteCreature();
        Skill skill = skillFactory.create("fencing");
        skill.levelUp(dude);
        skill.levelUp(dude);
        assertEquals(skill.getLevel(), 2);
    }
}
