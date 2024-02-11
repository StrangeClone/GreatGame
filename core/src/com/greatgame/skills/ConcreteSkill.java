package com.greatgame.skills;

import com.greatgame.entities.Creature;
import com.greatgame.entities.Skill;
import com.greatgame.factory.Factory;

public class ConcreteSkill implements Skill {
    public static final Factory<Skill> skillFactory = new Factory<>();
    private final String name;
    private int level;
    private final BonusCalculator calculator;
    private final SkillUpGrader skillUpGrader;
    public ConcreteSkill(String name, BonusCalculator calculator, SkillUpGrader upGrader) {
        this.name = name;
        this.calculator = calculator;
        skillUpGrader = upGrader;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getLevel() {
        return level;
    }

    public int getBonus(Creature creature) {
        return calculator.calculate(this, creature);
    }

    @Override
    public void levelUp(Creature creature) {
        level++;
        if(skillUpGrader != null) {
            skillUpGrader.upgrade(this, creature);
        }
    }

}
