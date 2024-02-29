package com.greatgame.fightBehaviourState;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.greatgame.actions.FightModeMovementAction;
import com.greatgame.application.Mode;
import com.greatgame.application.explorationMode.ExplorationMode;
import com.greatgame.behaviour.CreatureBehaviour;
import com.greatgame.environment.Action;
import com.greatgame.explorationBehaviourState.PlayerExplorationBehaviourState;

public class PlayerFightBehaviourState extends FightBehaviourState {
    public PlayerFightBehaviourState(CreatureBehaviour behaviour) {
        super(behaviour);

        InputListener inputListener = new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Actor touched = getEnvironment().getStage().hit(x, y, false);
                if(touched == null && currentAction == null) {
                    Action movement = new FightModeMovementAction("Movement", getEnvironment(), behaviour, new Vector2(x,y));
                    if(movement.validate()) {
                        currentAction = movement;
                        movement.start(3000);
                    }
                }
                return true;
            }
        };
        getEnvironment().getStage().addListener(inputListener);
    }

    @Override
    public void changeMode(Mode newMode) {
        if(newMode instanceof ExplorationMode) {
            behaviour.setState(new PlayerExplorationBehaviourState(behaviour));
        }
    }
}
