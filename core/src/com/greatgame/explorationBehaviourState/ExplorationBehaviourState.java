package com.greatgame.explorationBehaviourState;

import com.greatgame.behaviour.BehaviourState;
import com.greatgame.behaviour.CreatureBehaviour;
import com.greatgame.environment.Action;

public abstract class ExplorationBehaviourState extends BehaviourState {

    public static final float PIXELS_PER_METER = 100.f;
    private Action action;

    public ExplorationBehaviourState(CreatureBehaviour behaviour) {
        super(behaviour);
    }

    protected boolean setPosition(float x, float y) {
        if(getEnvironment().allowedPosition(behaviour, x, y, true)) {
            behaviour.setPosition(x,y);
            behaviour.saveBehaviourInfo();
            return true;
        }
        return false;
    }

    @Override
    public void act(float delta) {
        if (action != null) {
            action.update(delta);
            if (action.finished()) {
                action = null;
            }
        }
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
