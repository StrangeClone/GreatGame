package com.greatgame.application;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public abstract class Mode {
    protected GreatGame app;
    protected Stage stage;
    public static Skin skin;

    public Mode() {
        this.stage = new Stage(new ScreenViewport());
    }

    public void update(float delta) {
        stage.act(delta);
        stage.draw();
    }
}
