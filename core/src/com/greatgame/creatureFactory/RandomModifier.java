package com.greatgame.creatureFactory;

import com.greatgame.entities.Creature;
import com.greatgame.environment.RandomMap;

public class RandomModifier implements CreaturePatternModifier {
    private final RandomMap<CreaturePatternModifier> modifiersMap;

    public RandomModifier() {
        modifiersMap = new RandomMap<>();
    }
    @Override
    public void modify(Creature creature) {
        modifiersMap.generate(creature.hashCode()).modify(creature);
    }

    public RandomModifier addModifier(CreaturePatternModifier modifier, int weight) {
        modifiersMap.setWeight(modifier, weight);
        return this;
    }
}
