package com.greatgame.application.mainMenuMode;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.greatgame.application.fightMode.FightMode;
import com.greatgame.application.tripMode.TripMode;
import com.greatgame.environment.ModeName;

public class PausePage extends Page {
    public PausePage(MainMenuMode mode) {
        super(mode);

        Table table = new Table();
        Label title = new Label("Pause", MainMenuMode.skin);
        table.add(title).padBottom(10).row();
        addButton(table, "Back to Main Menu", new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mode.changePage(new MainMenuPage(mode));
            }
        });
        addButton(table, "Back to Game", new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ModeName name;
                if (mode.getPreviousMode() instanceof FightMode) {
                    name = ModeName.fightMode;
                } else if (mode.getPreviousMode() instanceof TripMode) {
                    name = ModeName.tripMode;
                } else {
                    name = ModeName.explorationMode;
                }
                mode.environment().triggerModeChange(name);
            }
        });
        addButton(table, "Save Game", new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mode.changePage(new SavePage(mode));
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
    }
}
