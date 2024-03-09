package com.greatgame.application;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.greatgame.application.dialogs.InventoryDialog;
import com.greatgame.application.dialogs.SkillDialog;
import com.greatgame.environment.Environment;
import com.greatgame.world.World;

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

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    protected Environment getEnvironment() {
        return app.world.getEnvironment();
    }

    protected World getWorld() {
        return app.world;
    }

    public Stage getStage() {
        return stage;
    }

    protected void addPlayerUI() {
        Button skillButton = new TextButton("Skills", skin);
        skillButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SkillDialog skillDialog = new SkillDialog(getEnvironment().getPlayer());
                skillDialog.show(stage);
            }
        });

        Button inventoryButton = new TextButton("Inventory", skin);
        inventoryButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                InventoryDialog inventoryDialog = new InventoryDialog(getEnvironment().getPlayer());
                inventoryDialog.show(stage);
            }
        });

        Table buttons = new Table();
        buttons.add(skillButton).width(200).height(40).row();
        buttons.add(inventoryButton).width(200).height(40).padTop(10);
        buttons.bottom().left();
        buttons.padBottom(20).padLeft(20);
        buttons.setFillParent(true);
        stage.addActor(buttons);
    }
}
