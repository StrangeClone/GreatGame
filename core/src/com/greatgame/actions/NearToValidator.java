package com.greatgame.actions;

import com.greatgame.environment.Behaviour;
import com.greatgame.environment.Environment;

public class NearToValidator implements ActionValidator {
    private final Behaviour creature, item;
    float distance;
    public NearToValidator(Behaviour creature, Behaviour item, float distance) {
        this.creature = creature;
        this.item = item;
        this.distance = distance;
    }
    @Override
    public boolean validate(Environment environment) {
        return environment.dist(creature.getPosition(), item.getPosition()) < distance;
    }
}
