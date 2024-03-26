package com.greatgame.behaviour;

import com.greatgame.application.Mode;
import com.greatgame.environment.Environment;

public abstract class BehaviourState {
    protected CreatureBehaviour behaviour;
    public BehaviourState(CreatureBehaviour behaviour) {
        this.behaviour = behaviour;
    }
    public abstract void changeMode(Mode newMode);

    protected Environment getEnvironment() {
        return behaviour.getEnvironment();
    }

    public abstract void act(float delta);
}
