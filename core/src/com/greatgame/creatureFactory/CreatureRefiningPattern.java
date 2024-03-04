package com.greatgame.creatureFactory;

import com.greatgame.actions.AttackAction;
import com.greatgame.environment.Behaviour;
import com.greatgame.factory.BehaviourRefiningPattern;

public class CreatureRefiningPattern extends BehaviourRefiningPattern {
    public CreatureRefiningPattern(String name) {
        super(name);
    }

    @Override
    public void refine(Behaviour behaviour) {
        behaviour.allowAction(new AttackAction(behaviour.getEnvironment().getPlayer(), behaviour));
    }
}
