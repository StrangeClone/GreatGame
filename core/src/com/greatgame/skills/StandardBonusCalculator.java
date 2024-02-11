package com.greatgame.skills;

import com.greatgame.entities.Characteristic;
import com.greatgame.entities.Creature;
import com.greatgame.entities.Skill;

public class StandardBonusCalculator implements BonusCalculator {
    Characteristic c;
    public StandardBonusCalculator(Characteristic c) {
        this.c = c;
    }
    @Override
    public int calculate(Skill skill, Creature creature) {
        int cBonus = creature.getCharacteristicBonus(c);
        int level = skill.getLevel();
        return cBonus + level;
    }
}
