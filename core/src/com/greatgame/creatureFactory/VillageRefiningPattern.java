package com.greatgame.creatureFactory;

import com.greatgame.actions.TalkAction;
import com.greatgame.behaviour.CreatureBehaviour;
import com.greatgame.environment.Behaviour;
import com.greatgame.explorationBehaviourState.VillagerExplorationBehaviourState;
import com.greatgame.factory.BehaviourRefiningPattern;

public class VillageRefiningPattern extends BehaviourRefiningPattern {
    public VillageRefiningPattern(String name) {
        super(name);
    }

    @Override
    public void refine(Behaviour behaviour) {
        CreatureBehaviour creatureBehaviour = (CreatureBehaviour)behaviour;
        behaviour.allowAction(new TalkAction(behaviour.getEnvironment().getPlayer(), creatureBehaviour));
        creatureBehaviour.setState(new VillagerExplorationBehaviourState(creatureBehaviour, null));
    }
}
