package com.greatgame.application.explorationMode;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.greatgame.application.GreatGame;
import com.greatgame.application.Mode;
import com.greatgame.environment.Environment;
import com.greatgame.environment.ModeName;

public class ExplorationMode extends Mode {
    private final Environment environment;
    public ExplorationMode(GreatGame application, Environment environment) {
        super();
        this.app = application;
        this.environment = environment;

        Button tripButton = new TextButton("Trip", skin);
        tripButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                environment.triggerModeChange(ModeName.tripMode);
            }
        });

        Table table = new Table();
        table.add(tripButton).width(200).height(40);
        table.bottom().right();
        table.padBottom(20).padRight(20);
        table.setFillParent(true);
        stage.addActor(table);

        addPlayerUI(environment);
    }

    @Override
    public void update(float delta) {
        environment.update(delta);
        super.update(delta);
    }
}
