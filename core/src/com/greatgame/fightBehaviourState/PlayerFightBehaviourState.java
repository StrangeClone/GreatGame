package com.greatgame.fightBehaviourState;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.greatgame.actions.AllowedActionDialog;
import com.greatgame.actions.FightModeMovementAction;
import com.greatgame.application.Mode;
import com.greatgame.application.explorationMode.ExplorationMode;
import com.greatgame.behaviour.CreatureBehaviour;
import com.greatgame.environment.Action;
import com.greatgame.environment.Behaviour;
import com.greatgame.explorationBehaviourState.PlayerExplorationBehaviourState;

public class PlayerFightBehaviourState extends FightBehaviourState {
    public PlayerFightBehaviourState(CreatureBehaviour behaviour, Stage uiStage) {
        super(behaviour);

        InputListener inputListener = new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Behaviour touched = getEnvironment().behaviourInPosition(x,y);
                if ((touched == null || !touched.isTouchable())
                        && currentAction == null
                        && button == Input.Buttons.LEFT) {
                    Action movement = new FightModeMovementAction(getEnvironment(), behaviour, new Vector2(x, y));
                    if (movement.validate()) {
                        currentAction = movement;
                        movement.start(3000);
                    }
                } else if (touched != null
                        && touched != behaviour
                        && isActive()
                        && currentAction == null
                        && button == Input.Buttons.RIGHT) {
                    AllowedActionDialog dialog = new AllowedActionDialog(touched, getEnvironment().getPlayer());
                    dialog.show(uiStage);
                }
                return false;
            }
        };
        getEnvironment().getStage().addListener(inputListener);
    }

    @Override
    public void changeMode(Mode newMode) {
        if (newMode instanceof ExplorationMode) {
            behaviour.setState(new PlayerExplorationBehaviourState(behaviour));
        }
    }
}
