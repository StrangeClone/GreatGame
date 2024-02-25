package com.greatgame.skills;

import com.greatgame.entities.Characteristic;

import java.util.ArrayList;
import java.util.List;

import static com.greatgame.skills.ConcreteSkill.skillFactory;

public class SkillInitializer {
    public static void initializeSkills() {
        skillFactory.addPattern(new SkillPattern("archery", new StandardBonusCalculator(Characteristic.Agility), null));
        skillFactory.addPattern(new SkillPattern("bite", new StandardBonusCalculator(Characteristic.Physique), null));
        skillFactory.addPattern(new SkillPattern("fencing", new StandardBonusCalculator(Characteristic.Physique), null));
        skillFactory.addPattern(new SkillPattern("unarmed fight", new StandardBonusCalculator(Characteristic.Physique), null));
        skillFactory.addPattern(new SkillPattern("vitality", new StandardBonusCalculator(Characteristic.Physique), new VitalityUpGrader()));
    }

    public static List<String> playerAvailableSkills() {
        List<String> result = new ArrayList<>();
        result.add("archery");
        result.add("fencing");
        result.add("unarmed fight");
        result.add("vitality");
        return result;
    }
}
