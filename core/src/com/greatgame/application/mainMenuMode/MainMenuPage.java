package com.greatgame.application.mainMenuMode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MainMenuPage extends Page {
    public MainMenuPage(MainMenuMode mode) {
        super(mode);

        Table table = new Table();
        Image titleImage = new Image(new Texture(Gdx.files.internal("textures/Title.png")));
        table.add(titleImage).width(721).height(210).padBottom(50).row();

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
        addButton(table, "To the repository", new ClickListener() {
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

        Table betaTable = new Table();
        Label betaLabel = new Label("This is a beta. Please report bugs if you find any and share your ideas with us", MainMenuMode.skin);
        betaTable.add(betaLabel);
        betaTable.setFillParent(true);
        betaTable.bottom();
        pageStage.addActor(betaTable);
    }
}
