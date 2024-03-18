package com.greatgame.application.mainMenuMode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class SavePage extends Page {
    public SavePage(MainMenuMode mode) {
        super(mode);

        Table table = new Table();
        VerticalGroup gameList = new VerticalGroup();
        TextField newGameName = new TextField("", MainMenuMode.skin);

        FileHandle gamesDirectory = Gdx.files.local("assets/games/");
        for (FileHandle gameFile : gamesDirectory.list()) {
            Label item = new Label(gameFile.name(), MainMenuMode.skin);
            item.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    newGameName.setText(item.getText().toString());
               }
            });
            gameList.addActor(item);
        }

        ScrollPane gamesPane = new ScrollPane(gameList, MainMenuMode.skin);
        table.add(gamesPane).width(400).height(400).colspan(2).row();

        table.add(newGameName).width(200);
        TextButton saveButton = new TextButton("Save", MainMenuMode.skin);
        saveButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameSaver.save(newGameName.getText(), mode.world());
                mode.changePage(new PausePage(mode));
            }
        });
        table.add(saveButton).width(200).row();

        table.setFillParent(true);
        pageStage.addActor(table);
    }
}
