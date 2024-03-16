package com.greatgame.explorationBehaviourState;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.greatgame.actions.TalkAction;
import com.greatgame.application.Mode;
import com.greatgame.application.explorationMode.ExplorationMode;
import com.greatgame.application.fightMode.FightMode;
import com.greatgame.behaviour.CreatureBehaviour;
import com.greatgame.fightBehaviourState.VillagerFightBehaviourState;

public class VillagerExplorationBehaviourState extends ExplorationBehaviourState {
    public VillagerExplorationBehaviourState(CreatureBehaviour behaviour, Stage uiStage) {
        super(behaviour);
        TalkAction action = (TalkAction) behaviour.getAllowedActions().get(0);
        action.setUiStage(uiStage);
    }

    @Override
    public void changeMode(Mode newMode) {
        if (newMode instanceof FightMode) {
            behaviour.setState(new VillagerFightBehaviourState(behaviour, newMode.getStage()));
        }
        if (newMode instanceof ExplorationMode) {
            behaviour.setState(new VillagerExplorationBehaviourState(behaviour, newMode.getStage()));
        }
    }

    @Override
    public void act(float delta) {}
}
