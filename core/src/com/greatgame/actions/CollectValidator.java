package com.greatgame.actions;

import com.greatgame.environment.Behaviour;
import com.greatgame.environment.Environment;

import static com.greatgame.explorationBehaviourState.ExplorationBehaviourState.PIXELS_PER_METER;

public class CollectValidator implements ActionValidator {
    private final Behaviour creature, item;
    public CollectValidator(Behaviour creature, Behaviour item) {
        this.creature = creature;
        this.item = item;
    }
    @Override
    public boolean validate(Environment environment) {
        return environment.dist(creature.getPosition(), item.getPosition()) < PIXELS_PER_METER;
    }
}
