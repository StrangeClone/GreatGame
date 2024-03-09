package com.greatgame.fightBehaviourState;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.greatgame.actions.TalkAction;
import com.greatgame.application.Mode;
import com.greatgame.application.explorationMode.ExplorationMode;
import com.greatgame.application.tripMode.TripMode;
import com.greatgame.behaviour.CreatureBehaviour;
import com.greatgame.explorationBehaviourState.VillagerExplorationBehaviourState;

public class VillagerFightBehaviourState extends FightBehaviourState {
    public VillagerFightBehaviourState(CreatureBehaviour behaviour, Stage uiStage) {
        super(behaviour);
        TalkAction action = (TalkAction) behaviour.getAllowedActions().get(0);
        action.setUiStage(uiStage);
    }

    @Override
    public void changeMode(Mode newMode) {
        if (newMode instanceof ExplorationMode) {
            behaviour.setState(new VillagerExplorationBehaviourState(behaviour, newMode.getStage()));
        }
        if (newMode instanceof TripMode) {
            behaviour.remove();
        }
    }

    @Override
    public void act(float delta) {
        actions = 0;
        super.act(delta);
    }
}
