package com.greatgame.application.mainMenuMode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.greatgame.application.dialogs.ErrorDialog;
import com.greatgame.world.World;

import java.io.IOException;

public class LoadPage extends Page {
    public LoadPage(MainMenuMode mode) {
        super(mode);

        Table table = new Table();
        VerticalGroup gameList = new VerticalGroup();
        Label label = new Label("Selected:", MainMenuMode.skin);
        Label gameName = new Label("", MainMenuMode.skin);

        FileHandle gamesDirectory = Gdx.files.local("assets/games/");
        for (FileHandle gameFile : gamesDirectory.list()) {
            Label item = new Label(gameFile.name(), MainMenuMode.skin);
            item.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    gameName.setText(item.getText().toString());
                }
            });
            gameList.addActor(item);
        }

        ScrollPane gamesPane = new ScrollPane(gameList, MainMenuMode.skin);
        table.add(gamesPane).width(400).height(400).colspan(2).row();

        table.add(label).width(100);
        table.add(gameName).row();
        TextButton loadButton = new TextButton("Load", MainMenuMode.skin);
        loadButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                try {
                    if (gameName.getText().notEmpty()) {
                        mode.getApp().setWorld(new World(Gdx.files.local("assets/games/" + gameName.getText())));
                        mode.getApp().setBackgroundColor(Color.GREEN);
                        mode.newGameLoaded();
                    }
                } catch (RuntimeException | IOException e) {
                    Dialog errorDialog = new ErrorDialog("File corrupted");
                    System.out.println(e.getMessage());
                    errorDialog.show(mode.getStage());
                }
            }
        });
        table.add(loadButton).width(400).padBottom(10).colspan(2).row();
        TextButton backButton = new TextButton("Back", MainMenuMode.skin);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mode.changePage(new MainMenuPage(mode));
            }
        });
        table.add(backButton).width(400).colspan(2).row();

        table.setFillParent(true);
        pageStage.addActor(table);
    }
}
