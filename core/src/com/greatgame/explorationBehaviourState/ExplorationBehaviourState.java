package com.greatgame.explorationBehaviourState;

import com.greatgame.behaviour.BehaviourState;
import com.greatgame.behaviour.CreatureBehaviour;

public abstract class ExplorationBehaviourState extends BehaviourState {

    protected static final float PIXELS_PER_METER = 100.f;

    public ExplorationBehaviourState(CreatureBehaviour behaviour) {
        super(behaviour);
    }

    protected boolean setPosition(float x, float y) {
        if(getEnvironment().allowedPosition(behaviour, x, y, true)) {
            behaviour.setPosition(x,y);
            return true;
        }
        return false;
    }
}
