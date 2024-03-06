package com.greatgame.application.tripMode;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.greatgame.application.GreatGame;
import com.greatgame.application.Mode;
import com.greatgame.environment.Location;
import com.greatgame.environment.ModeName;
import com.greatgame.tripBehaviourState.PlayerTripBehaviourState;

import java.util.Map;

public class TripMode extends Mode {
    Stage mapStage;
    Texture playerIcon;
    public TripMode(GreatGame app) {
        this.app = app;
        getEnvironment().getPlayer().remove();
        getEnvironment().getStage().getActors().clear();
        getEnvironment().getLoadedLocations().clear();
        getEnvironment().getStage().getActors().add(getEnvironment().getPlayer());

        mapStage = new Stage(new ScreenViewport());
        for (Location l : getWorld().getGeneratedLocations().values()) {
            mapStage.addActor(new LocationIcon(l));
        }

        playerIcon = new Texture("textures/action.png");

        Button exploreButton = new TextButton("Explore", skin);
        exploreButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                prepareExplorationMode();
                getEnvironment().triggerModeChange(ModeName.explorationMode);
            }
        });
        Table buttons = new Table();
        buttons.add(exploreButton).width(200).height(40);
        buttons.bottom().right().setFillParent(true);
        buttons.padBottom(20).padRight(20);
        stage.addActor(buttons);

        addPlayerUI();
    }

    private void prepareExplorationMode() {
        PlayerTripBehaviourState playerState = (PlayerTripBehaviourState) getEnvironment().getPlayer().getState();
        getEnvironment().setOriginalLocation(playerState.getX(), playerState.getY());
        getEnvironment().checkContents(playerState.getX(), playerState.getY());
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        mapStage.getViewport().update(width, height, true);
    }

    @Override
    public void update(float delta) {
        getEnvironment().getStage().act(delta);

        PlayerTripBehaviourState playerState = (PlayerTripBehaviourState) getEnvironment().getPlayer().getState();
        int x = playerState.getX();
        int y = playerState.getY();
        updateMap(x,y);

        mapStage.getViewport().getCamera().position.set(x * 50, y * 50, 1);
        mapStage.act(delta);
        mapStage.draw();

        drawPlayerIcon(x,y);

        super.update(delta);
    }

    private void drawPlayerIcon(int x, int y) {
        mapStage.getBatch().begin();
        mapStage.getBatch().draw(playerIcon, x * 50 + 5, y * 50 + 5, 40, 40);
        mapStage.getBatch().end();
    }

    private void updateMap(int x, int y) {
        Map<Vector2, Location> locationMap = getWorld().getGeneratedLocations();
        int dim = 4;
        for (int i = - dim / 2; i <= dim / 2; i++) {
            for (int o = -dim / 2; o <= dim / 2; o++) {
                if (!locationMap.containsKey(new Vector2(x + i, y + o))) {
                    Location newLocation = getWorld().generate(x + i, y + o);
                    mapStage.addActor(new LocationIcon(newLocation));
                }
            }
        }
    }
}
