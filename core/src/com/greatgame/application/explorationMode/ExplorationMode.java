package com.greatgame.application.explorationMode;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.greatgame.application.GreatGame;
import com.greatgame.application.Mode;
import com.greatgame.environment.ModeName;

public class ExplorationMode extends Mode {
    public ExplorationMode(GreatGame application) {
        super();
        this.app = application;

        Button tripButton = new TextButton("Trip", skin);
        tripButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                getEnvironment().triggerModeChange(ModeName.tripMode);
            }
        });

        Table table = new Table();
        table.add(tripButton).width(200).height(40);
        table.bottom().right();
        table.padBottom(20).padRight(20);
        table.setFillParent(true);
        stage.addActor(table);

        addPlayerUI();
    }

    @Override
    public void update(float delta) {
        getEnvironment().update(delta);
        super.update(delta);
    }
}
