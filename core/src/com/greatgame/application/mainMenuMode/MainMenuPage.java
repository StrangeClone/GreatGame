package com.greatgame.application.mainMenuMode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MainMenuPage extends Page {
    public MainMenuPage(MainMenuMode mode) {
        super(mode);

        Table table = new Table();
        addButton(table, "New Game", new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mode.changePage(new NewGamePage(mode));
            }
        });
        addButton(table, "Load Game", new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mode.changePage(new LoadPage(mode));
            }
        });
        addButton(table, "Contribute", new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                openUrl("https://github.com/StrangeClone/GreatGame");
            }
        });
        addButton(table, "Report a bug", new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                openUrl("https://github.com/StrangeClone/GreatGame/issues/new?assignees=&labels=&projects=&template=bug_report.md&title=");
            }
        });
        addButton(table, "Share an Idea", new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                openUrl("https://github.com/StrangeClone/GreatGame/issues/new?assignees=&labels=&projects=&template=feature_request.md&title=");
            }
        });
        addButton(table, "Quit Game", new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        table.setFillParent(true);
        pageStage.addActor(table);
    }
}
