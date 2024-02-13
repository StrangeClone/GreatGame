package com.greatgame.creatureFactory;

import com.greatgame.entities.Creature;

public class SkillGiver implements CreaturePatternModifier {
    String skillName;
    int level;
    public SkillGiver(String skillName, int level) {
        this.skillName = skillName;
        this.level = level;
    }
    @Override
    public void modify(Creature creature) {
        for (int i = 0; i < level; i++) {
            creature.upgradeSkill(skillName);
        }
    }
}
