package com.greatgame.application.mainMenuMode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.greatgame.application.GreatGame;
import com.greatgame.application.Mode;
import com.greatgame.application.fightMode.FightMode;
import com.greatgame.application.tripMode.TripMode;
import com.greatgame.behaviour.CreatureBehaviour;
import com.greatgame.entities.Characteristic;
import com.greatgame.environment.ModeName;
import com.greatgame.skills.SkillInitializer;
import com.greatgame.world.ConcreteEnvironment;
import com.greatgame.world.World;

import java.util.Random;

import static com.greatgame.behaviour.CreatureBehaviour.creaturesFactory;

public class MainMenuMode extends Mode {
    Mode previousMode;
    Stage mainMenuStage;
    Stage newGameStage;
    Stage pauseStage;

    public MainMenuMode(GreatGame app, Mode previousMode, boolean pause) {
        this.previousMode = previousMode;
        this.app = app;
        initMainMenuStage();
        initNewGameStage();
        initPauseStage();
        if (pause) {
            stage = pauseStage;
        } else {
            stage = mainMenuStage;
        }
    }

    public Mode getPreviousMode() {
        return previousMode;
    }

    @Override
    public void resize(int width, int height) {
        mainMenuStage.getViewport().update(width, height, true);
        newGameStage.getViewport().update(width, height, true);
    }

    private void initMainMenuStage() {
        mainMenuStage = new Stage(new ScreenViewport());

        Table table = new Table();
        Button newGameButton = new TextButton("New Game", skin);
        newGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stage = newGameStage;
                app.updateInputMultiplexer();
            }
        });
        table.add(newGameButton).padBottom(10).row();
        Button exitButton = new TextButton("Quit Game", skin);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        table.add(exitButton);
        table.setFillParent(true);
        mainMenuStage.addActor(table);
    }

    private void initNewGameStage() {
        newGameStage = new Stage(new ScreenViewport());

        Table table = new Table();

        CreatureBehaviour player = creaturesFactory.create("player");
        player.increaseEP(1000);

        Label worldSeed = new Label("Seed:", skin);
        table.add(worldSeed).left().padLeft(10);
        TextField seedField = new TextField("", skin);
        seedField.setTextFieldFilter((textField, c) -> Character.isDigit(c));
        table.add(seedField).colspan(3);
        table.row();

        Random random = new Random();
        Label physiqueLabel = new Label("Physique: ", skin);
        table.add(physiqueLabel).colspan(2).padLeft(10).padTop(15).left();
        Label physiqueValue = new Label(Integer.toString(characteristicValue(random)), skin);
        table.add(physiqueValue).row();
        Label agilityLabel = new Label("Agility: " + characteristicValue(random), skin);
        table.add(agilityLabel).colspan(2).padLeft(10).padTop(5).left();
        Label agilityValue = new Label(Integer.toString(characteristicValue(random)), skin);
        table.add(agilityValue).row();
        Button reRollCharacteristics = new TextButton("Roll again characteristics", skin);
        reRollCharacteristics.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                physiqueValue.setText(characteristicValue(random));
                agilityValue.setText(characteristicValue(random));
            }
        });
        table.add(reRollCharacteristics).colspan(3).padTop(15).row();

        Label ep = new Label("EP: " + player.getEP(), skin);
        table.add(ep).colspan(3).left().padLeft(10).padTop(15).row();

        for(String skillName : SkillInitializer.playerAvailableSkills()) {
            Label skillLabel = new Label(skillName, skin);
            Label levelLabel = new Label(Integer.toString(player.getCreature().getLevel(skillName)), skin);
            Button levelUpButton = new TextButton("+", skin);
            levelUpButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    int currentLevel = player.getCreature().getLevel(skillName);
                    if(player.getEP() >= (currentLevel + 1) * 200) {
                        player.decreaseEP((currentLevel + 1) * 200);
                        ep.setText("EP: " + player.getEP());
                        player.getCreature().upgradeSkill(skillName);
                        levelLabel.setText(Integer.toString(currentLevel + 1));
                    }
                }
            });
            table.add(skillLabel).padTop(5).left().padLeft(10).colspan(2);
            table.add(levelLabel).padLeft(10);
            table.add(levelUpButton).width(40).height(40).padLeft(10).padRight(10);
            table.row();
        }

        Button createButton = new TextButton("Confirm and create", skin);
        createButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                long seed;
                try {
                    seed = Long.parseLong(seedField.getText());
                } catch (NumberFormatException e) {
                    seed = random.nextLong();
                }
                app.setWorld(new World(seed, new ConcreteEnvironment()));

                player.getCreature().setCharacteristic(Characteristic.Physique, Integer.parseInt(physiqueValue.getText().toString()));
                player.getCreature().setCharacteristic(Characteristic.Agility, Integer.parseInt(agilityValue.getText().toString()));

                getWorld().getEnvironment().setPlayer(player, getEnvironment().getStage());
                player.getCreature().setCoins(100);

                getEnvironment().checkContents(0,0);

                app.setBackgroundColor(Color.GREEN);
                getEnvironment().triggerModeChange(ModeName.explorationMode);
            }
        });
        table.add(createButton).padTop(50).colspan(3);
        table.setFillParent(true);
        newGameStage.addActor(table);
    }

    private int characteristicValue(Random random) {
        int t1 = random.nextInt(1,7);
        int t2 = random.nextInt(1,7);
        int t3 = random.nextInt(1,7);
        int t4 = random.nextInt(1,7);
        return t1 + t2 + t3 + t4 - min(t1, t2, t3, t4);
    }

    private int min(int... values) {
        int result = values[0];
        for (int i = 1; i < values.length; i++) {
            if (values[i] < result) {
                result = values[i];
            }
        }
        return result;
    }

    private void initPauseStage() {
        pauseStage = new Stage(new ScreenViewport());

        Table table = new Table();
        Label title = new Label("Pause", skin);
        table.add(title).padBottom(10).row();
        Button backToMenu = new TextButton("Back to main menu", skin);
        backToMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stage = mainMenuStage;
                app.updateInputMultiplexer();
            }
        });
        table.add(backToMenu).padBottom(10).row();
        Button backToGame = new TextButton("Back to game", skin);
        backToGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ModeName name;
                if (previousMode instanceof FightMode) {
                    name = ModeName.fightMode;
                } else if (previousMode instanceof TripMode) {
                    name = ModeName.tripMode;
                } else {
                    name = ModeName.explorationMode;
                }
                getEnvironment().triggerModeChange(name);
            }
        });
        table.add(backToGame).padBottom(10).row();
        Button exitButton = new TextButton("Quit Game", skin);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        table.add(exitButton);
        table.setFillParent(true);
        pauseStage.addActor(table);
    }
}