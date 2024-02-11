package com.greatgame.skills;

import com.greatgame.entities.Creature;
import com.greatgame.entities.Skill;

public interface SkillUpGrader {
    void upgrade(Skill skill, Creature creature);
}
