package com.greatgame.fightBehaviourState;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.greatgame.actions.FightModeAllowedActionDialog;
import com.greatgame.actions.FightModeMovementAction;
import com.greatgame.application.Mode;
import com.greatgame.application.explorationMode.ExplorationMode;
import com.greatgame.application.tripMode.TripMode;
import com.greatgame.behaviour.CreatureBehaviour;
import com.greatgame.environment.Action;
import com.greatgame.environment.Behaviour;
import com.greatgame.explorationBehaviourState.PlayerExplorationBehaviourState;
import com.greatgame.tripBehaviourState.PlayerTripBehaviourState;

public class PlayerFightBehaviourState extends FightBehaviourState {
    InputListener listener;

    public PlayerFightBehaviourState(CreatureBehaviour behaviour, Stage uiStage) {
        super(behaviour);

        listener = new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Behaviour touched = getEnvironment().behaviourInPosition(x, y);
                if ((touched == null || !touched.isTouchable())
                        && currentAction == null
                        && button == Input.Buttons.LEFT) {
                    Action movement = new FightModeMovementAction(getEnvironment(), behaviour, new Vector2(x, y));
                    Thread thread = new Thread(() -> {
                        if (movement.validate()) {
                            currentAction = movement;
                            movement.start();
                        }
                    });
                    thread.start();
                } else if (touched != null
                        && touched != behaviour
                        && isActive()
                        && currentAction == null
                        && button == Input.Buttons.RIGHT) {
                    FightModeAllowedActionDialog dialog = new FightModeAllowedActionDialog(touched);
                    dialog.show(uiStage);
                }
                return false;
            }
        };
        getEnvironment().getStage().addListener(listener);
    }

    @Override
    public void changeMode(Mode newMode) {
        getEnvironment().getStage().removeListener(listener);
        if (newMode instanceof ExplorationMode) {
            behaviour.setState(new PlayerExplorationBehaviourState(behaviour, newMode.getStage()));
        }
        if (newMode instanceof TripMode) {
            behaviour.setState(new PlayerTripBehaviourState(behaviour));
        }
    }
}
