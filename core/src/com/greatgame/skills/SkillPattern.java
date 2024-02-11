package com.greatgame.skills;

import com.greatgame.entities.Skill;
import com.greatgame.factory.Pattern;

public class SkillPattern extends Pattern<Skill> {
    BonusCalculator calculator;
    SkillUpGrader upGrader;
    public SkillPattern(String name, BonusCalculator calculator, SkillUpGrader upGrader) {
        super(name);
        this.calculator = calculator;
        this.upGrader = upGrader;
    }

    @Override
    public Skill build() {
        return new ConcreteSkill(getName(), calculator, upGrader);
    }
}
