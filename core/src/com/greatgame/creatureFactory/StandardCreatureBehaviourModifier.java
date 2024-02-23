package com.greatgame.creatureFactory;

import com.greatgame.behaviour.CreatureBehaviour;
import com.greatgame.explorationBehaviourState.IdleExplorationBehaviourState;

public class StandardCreatureBehaviourModifier implements CreatureBehaviourModifier {
    private final boolean moves;
    private final boolean hostile;
    public StandardCreatureBehaviourModifier(boolean hostile, boolean moves) {
        this.moves = moves;
        this.hostile = hostile;
    }
    @Override
    public void modify(CreatureBehaviour behaviour) {
        behaviour.setHostile(hostile);
        behaviour.setState(new IdleExplorationBehaviourState(behaviour, moves));
    }
}
