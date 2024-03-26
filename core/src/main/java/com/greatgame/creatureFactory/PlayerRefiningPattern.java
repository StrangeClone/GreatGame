package com.greatgame.creatureFactory;

import com.greatgame.behaviour.CreatureBehaviour;
import com.greatgame.environment.Behaviour;
import com.greatgame.explorationBehaviourState.PlayerExplorationBehaviourState;
import com.greatgame.factory.BehaviourRefiningPattern;

public class PlayerRefiningPattern extends BehaviourRefiningPattern {
    public PlayerRefiningPattern(String name) {
        super(name);
    }

    @Override
    public void refine(Behaviour behaviour) {
        CreatureBehaviour creatureBehaviour = (CreatureBehaviour) behaviour;
        creatureBehaviour.setState(new PlayerExplorationBehaviourState(creatureBehaviour, null));
    }
}
