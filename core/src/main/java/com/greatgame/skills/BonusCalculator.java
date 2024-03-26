package com.greatgame.skills;

import com.greatgame.entities.Creature;
import com.greatgame.entities.Skill;

public interface BonusCalculator {
    int calculate(Skill skill, Creature creature);
}
