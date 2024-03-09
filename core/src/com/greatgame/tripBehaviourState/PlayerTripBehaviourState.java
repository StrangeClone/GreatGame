package com.greatgame.tripBehaviourState;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.greatgame.application.Mode;
import com.greatgame.application.explorationMode.ExplorationMode;
import com.greatgame.application.fightMode.FightMode;
import com.greatgame.behaviour.BehaviourState;
import com.greatgame.behaviour.CreatureBehaviour;
import com.greatgame.explorationBehaviourState.PlayerExplorationBehaviourState;
import com.greatgame.fightBehaviourState.PlayerFightBehaviourState;

import static com.greatgame.explorationBehaviourState.PlayerExplorationBehaviourState.locationCoordinate;

public class PlayerTripBehaviourState extends BehaviourState {
    InputListener listener;
    int xMap,yMap;
    public PlayerTripBehaviourState(CreatureBehaviour behaviour) {
        super(behaviour);

        xMap = locationCoordinate(behaviour.getX(), getEnvironment().getOriginalLocationX());
        yMap = locationCoordinate(behaviour.getY(), getEnvironment().getOriginalLocationY());

        listener = new InputListener() {
            long nextMovement = 0;
            final long delta = 500;
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                long currentTime = System.currentTimeMillis();
                if (nextMovement < currentTime) {
                    if (keycode == Input.Keys.A) {
                        xMap--;
                        nextMovement = currentTime + delta;
                    } if (keycode == Input.Keys.D) {
                        xMap++;
                        nextMovement = currentTime + delta;
                    } if (keycode == Input.Keys.S) {
                        yMap--;
                        nextMovement = currentTime + delta;
                    } if (keycode == Input.Keys.W) {
                        yMap++;
                        nextMovement = currentTime + delta;
                    }
                }
                return false;
            }
        };
        getEnvironment().getStage().addListener(listener);
    }

    public int getX() {
        return xMap;
    }

    public int getY() {
        return yMap;
    }

    @Override
    public void changeMode(Mode newMode) {
        getEnvironment().getStage().removeListener(listener);
        if (newMode instanceof ExplorationMode) {
            behaviour.setState(new PlayerExplorationBehaviourState(behaviour, newMode.getStage()));
        }
        if (newMode instanceof FightMode) {
            behaviour.setState(new PlayerFightBehaviourState(behaviour, newMode.getStage()));
        }
    }

    @Override
    public void act(float delta) {

    }
}
