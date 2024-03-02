package com.greatgame.creatureFactory;

import com.greatgame.behaviour.CreatureBehaviour;
import com.greatgame.explorationBehaviourState.PlayerExplorationBehaviourState;

public class PlayerBehaviourModifier implements CreatureBehaviourModifier {
    private static final PlayerBehaviourModifier instance = new PlayerBehaviourModifier();
    private PlayerBehaviourModifier() {}
    public static PlayerBehaviourModifier getInstance() {
        return instance;
    }
    @Override
    public void modify(CreatureBehaviour behaviour) {
        behaviour.setState(new PlayerExplorationBehaviourState(behaviour, null));
    }
}
