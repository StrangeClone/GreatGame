package com.greatgame.application;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.greatgame.environment.Environment;

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

    public Stage getStage() {
        return stage;
    }

    protected void addPlayerUI(Environment environment) {
        Button skillButton = new TextButton("Skills", skin);
        skillButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SkillDialog skillDialog = new SkillDialog(environment.getPlayer());
                skillDialog.show(stage);
            }
        });

        Button inventoryButton = new TextButton("Inventory", skin);
        inventoryButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                InventoryDialog inventoryDialog = new InventoryDialog(environment.getPlayer());
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
