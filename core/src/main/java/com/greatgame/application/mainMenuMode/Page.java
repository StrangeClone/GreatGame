package com.greatgame.application.mainMenuMode;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public abstract class Page {
    MainMenuMode mode;
    Stage pageStage;
    public Page(MainMenuMode mode) {
        this.mode = mode;
        pageStage = new Stage(new ScreenViewport());
    }

    protected void addButton(Table table, String name, ClickListener listener) {
        TextButton button = new TextButton(name, MainMenuMode.skin);
        button.addListener(listener);
        table.add(button).padBottom(10).row();
    }

    protected void openUrl(String url) {
        Desktop desktop = java.awt.Desktop.getDesktop();
        try {
            URI uri = new URI(url);
            desktop.browse(uri);
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
