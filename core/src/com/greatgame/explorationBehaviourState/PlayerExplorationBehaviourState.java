package com.greatgame.explorationBehaviourState;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.greatgame.actions.ExplorationModeAllowedActionDialog;
import com.greatgame.actions.FightModeAllowedActionDialog;
import com.greatgame.application.Mode;
import com.greatgame.application.explorationMode.ExplorationMode;
import com.greatgame.application.fightMode.FightMode;
import com.greatgame.behaviour.CreatureBehaviour;
import com.greatgame.environment.Behaviour;
import com.greatgame.fightBehaviourState.PlayerFightBehaviourState;

import static com.greatgame.contentGenerators.ContentGenerator.PIXELS_PER_LOCATION;

public class PlayerExplorationBehaviourState extends ExplorationBehaviourState {
    private float directionX = 0, directionY = 0;
    private final InputListener listener;

    public PlayerExplorationBehaviourState(CreatureBehaviour behaviour, Stage uiStage) {
        super(behaviour);
        listener = new InputListener() {
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

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Behaviour touched = getEnvironment().behaviourInPosition(x,y);
                if (touched != null
                    && touched != behaviour
                    && button == Input.Buttons.RIGHT) {
                ExplorationModeAllowedActionDialog dialog = new ExplorationModeAllowedActionDialog(touched, getEnvironment().getPlayer());
                dialog.show(uiStage);
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
        getEnvironment().getStage().removeListener(listener);
        if (newMode instanceof ExplorationMode) {
            behaviour.setState(new PlayerExplorationBehaviourState(behaviour, newMode.getStage()));
        }
        if(newMode instanceof FightMode) {
            behaviour.setState(new PlayerFightBehaviourState(behaviour, newMode.getStage()));
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
