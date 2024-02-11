package com.greatgame.skills;

import com.greatgame.entities.Creature;
import com.greatgame.entities.Skill;

public class VitalityUpGrader implements SkillUpGrader {

    @Override
    public void upgrade(Skill skill, Creature creature) {
        creature.updateMaxHP();
    }
}
