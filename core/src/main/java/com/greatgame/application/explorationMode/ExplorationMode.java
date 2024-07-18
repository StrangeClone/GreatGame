package com.greatgame.application.explorationMode;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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
                getEnvironment().triggerModeChange(ModeName.tripMode);
            }
        });

        Table table = new Table();
        table.add(tripButton).width(200).height(40);
        table.bottom().right();
        table.padBottom(20).padRight(20);
        table.setFillParent(true);
        stage.addActor(table);

        Label hint1 = new Label("You're in exploration mode. You can move around freely. Press \"trip\" button to start a trip.", skin);
        Label hint2 = new Label("If you move too close to hostile creatures you will start fight mode.", skin);

        Table hintTable = new Table();
        hintTable.add(hint1).row();
        hintTable.add(hint2);
        hintTable.top();
        hintTable.padTop(20);
        hintTable.setFillParent(true);
        stage.addActor(hintTable);

        addPlayerUI();
    }

    @Override
    public void update(float delta) {
        getEnvironment().update(delta);
        super.update(delta);
    }
}
