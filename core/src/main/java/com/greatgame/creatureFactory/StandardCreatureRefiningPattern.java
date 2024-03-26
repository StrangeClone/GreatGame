package com.greatgame.creatureFactory;

import com.greatgame.actions.AttackAction;
import com.greatgame.behaviour.CreatureBehaviour;
import com.greatgame.environment.Behaviour;
import com.greatgame.explorationBehaviourState.IdleExplorationBehaviourState;
import com.greatgame.factory.BehaviourRefiningPattern;

public class StandardCreatureRefiningPattern extends BehaviourRefiningPattern {
    boolean hostile;
    public StandardCreatureRefiningPattern(String name, boolean hostile) {
        super(name);
        this.hostile = hostile;
    }

    @Override
    public void refine(Behaviour behaviour) {
        behaviour.allowAction(new AttackAction(behaviour.getEnvironment().getPlayer(), behaviour));

        CreatureBehaviour creatureBehaviour = (CreatureBehaviour) behaviour;
        creatureBehaviour.setHostile(hostile);
        creatureBehaviour.setState(new IdleExplorationBehaviourState(creatureBehaviour));
    }
}
