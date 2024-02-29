package com.greatgame.explorationBehaviourState;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.greatgame.application.Mode;
import com.greatgame.application.fightMode.FightMode;
import com.greatgame.behaviour.CreatureBehaviour;
import com.greatgame.fightBehaviourState.PlayerFightBehaviourState;

import static com.greatgame.contentGenerators.ContentGenerator.PIXELS_PER_LOCATION;

public class PlayerExplorationBehaviourState extends ExplorationBehaviourState {
    private float directionX = 0, directionY = 0;

    public PlayerExplorationBehaviourState(CreatureBehaviour behaviour) {
        super(behaviour);
        InputListener listener = new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode == Input.Keys.A) {
                    directionX = -1;
                    directionY = 0;
                } else if (keycode == Input.Keys.D) {
                    directionX = 1;
                    directionY = 0;
                } else if (keycode == Input.Keys.W) {
                    directionY = 1;
                    directionX = 0;
                } else if(keycode == Input.Keys.S) {
                    directionY = -1;
                    directionX = 0;
                }
                return true;
            }

            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                if ((keycode == Input.Keys.A && directionX == -1) || (keycode == Input.Keys.D && directionX == 1)) {
                    directionX = 0;
                }
                if((keycode == Input.Keys.W && directionY == 1) || (keycode == Input.Keys.S && directionY == -1)) {
                    directionY = 0;
                }
                return true;
            }
        };

        getEnvironment().getStage().addListener(listener);
        if (getEnvironment().getPlayer() == null) {
            getEnvironment().setPlayer(behaviour);
        }

    }

    @Override
    public void changeMode(Mode newMode) {
        if(newMode instanceof FightMode) {
            behaviour.setState(new PlayerFightBehaviourState(behaviour));
        }
    }

    @Override
    public void act(float delta) {
        if(directionX != 0 || directionY != 0) {
            float deltaX = directionX * delta * behaviour.getSpeed() / 3 * PIXELS_PER_METER;
            float deltaY = directionY * delta * behaviour.getSpeed() / 3 * PIXELS_PER_METER;
            float previousX = behaviour.getX(), previousY = behaviour.getY();
            setPosition(previousX + deltaX, previousY + deltaY);
            int currentLocationX = locationCoordinate(behaviour.getX());
            int currentLocationY = locationCoordinate(behaviour.getY());
            if(locationCoordinate(previousX) != currentLocationX || locationCoordinate(previousY) != currentLocationY) {
                getEnvironment().checkContents(currentLocationX, currentLocationY);
            }
        }
    }

    private int locationCoordinate(float value) {
        float pos = value / PIXELS_PER_LOCATION;
        if(pos > 0) {
            return (int) pos;
        } else {
            return (int) (pos - 1);
        }
    }
}
