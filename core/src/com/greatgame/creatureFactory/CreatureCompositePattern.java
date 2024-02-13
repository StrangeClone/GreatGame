package com.greatgame.creatureFactory;

import com.badlogic.gdx.graphics.Texture;
import com.greatgame.behaviour.CreatureBehaviour;
import com.greatgame.creature.ConcreteCreature;
import com.greatgame.entities.Creature;
import com.greatgame.factory.Pattern;

import java.util.ArrayList;
import java.util.List;

public class CreatureCompositePattern extends Pattern<CreatureBehaviour> {
    Texture texture;
    List<CreaturePatternModifier> modifiers;
    public CreatureCompositePattern(String name, Texture texture) {
        super(name);
        this.texture = texture;
        modifiers = new ArrayList<>();
    }

    public CreatureCompositePattern modify(CreaturePatternModifier modifier) {
        modifiers.add(modifier);
        return this;
    }

    @Override
    public CreatureBehaviour build() {
        Creature creature = new ConcreteCreature(getName());
        for (CreaturePatternModifier m : modifiers) {
            m.modify(creature);
        }
        //TODO: set allowed actions
        return new CreatureBehaviour(texture, creature);
    }
}
