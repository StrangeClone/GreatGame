package com.greatgame.application.mainMenuMode;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.greatgame.behaviour.CreatureBehaviour;
import com.greatgame.entities.Characteristic;
import com.greatgame.environment.ModeName;
import com.greatgame.skills.SkillInitializer;
import com.greatgame.world.ConcreteEnvironment;
import com.greatgame.world.World;

import java.util.Random;

import static com.greatgame.behaviour.CreatureBehaviour.creaturesFactory;

public class NewGamePage extends Page {
    Label physiqueValue, agilityValue;
    TextField seedField;

    public NewGamePage(MainMenuMode mode) {
        super(mode);
        Table table = new Table();

        CreatureBehaviour player = creaturesFactory.create("player");
        player.increaseEP(1000);

        Label worldSeed = new Label("Seed:", MainMenuMode.skin);
        table.add(worldSeed).left().padLeft(10);
        seedField = new TextField("", MainMenuMode.skin);
        seedField.setTextFieldFilter((textField, c) -> Character.isDigit(c));
        table.add(seedField).colspan(3);
        table.row();

        characteristicSelection(table);
        skillSelection(table, player);
        confirmButton(table, player);

        table.setFillParent(true);
        pageStage.addActor(table);
    }

    private void characteristicSelection(Table table) {
        Random random = new Random();
        Label physiqueLabel = new Label("Physique: ", MainMenuMode.skin);
        table.add(physiqueLabel).colspan(2).padLeft(10).padTop(15).left();
        physiqueValue = new Label(Integer.toString(characteristicValue(random)), MainMenuMode.skin);
        table.add(physiqueValue).row();
        Label agilityLabel = new Label("Agility: " + characteristicValue(random), MainMenuMode.skin);
        table.add(agilityLabel).colspan(2).padLeft(10).padTop(5).left();
        agilityValue = new Label(Integer.toString(characteristicValue(random)), MainMenuMode.skin);
        table.add(agilityValue).row();
        Button reRollCharacteristics = new TextButton("Roll again characteristics", MainMenuMode.skin);
        reRollCharacteristics.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                physiqueValue.setText(characteristicValue(random));
                agilityValue.setText(characteristicValue(random));
            }
        });
        table.add(reRollCharacteristics).colspan(3).padTop(15).row();
    }

    private void skillSelection(Table table, CreatureBehaviour player) {
        Label ep = new Label("EP: " + player.getEP(), MainMenuMode.skin);
        table.add(ep).colspan(3).left().padLeft(10).padTop(15).row();

        for (String skillName : SkillInitializer.playerAvailableSkills()) {
            Label skillLabel = new Label(skillName, MainMenuMode.skin);
            Label levelLabel = new Label(Integer.toString(player.getCreature().getLevel(skillName)), MainMenuMode.skin);
            Button levelUpButton = new TextButton("+", MainMenuMode.skin);
            levelUpButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    int currentLevel = player.getCreature().getLevel(skillName);
                    if (player.getEP() >= (currentLevel + 1) * 200) {
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
    }

    private void confirmButton(Table table, CreatureBehaviour player) {
        Button createButton = new TextButton("Confirm and create", MainMenuMode.skin);
        createButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                long seed;
                try {
                    seed = Long.parseLong(seedField.getText());
                } catch (NumberFormatException e) {
                    seed = new Random().nextLong();
                }
                mode.getApp().setWorld(new World(seed, new ConcreteEnvironment()));

                player.getCreature().setCharacteristic(Characteristic.Physique, Integer.parseInt(physiqueValue.getText().toString()));
                player.getCreature().setCharacteristic(Characteristic.Agility, Integer.parseInt(agilityValue.getText().toString()));

                mode.environment().setPlayer(player, mode.environment().getStage());
                player.getCreature().setCoins(100);

                mode.environment().checkContents(0, 0);

                mode.getApp().setBackgroundColor(Color.GREEN);
                mode.environment().triggerModeChange(ModeName.explorationMode);
            }
        });
        table.add(createButton).padTop(50).colspan(3);
    }


    private int characteristicValue(Random random) {
        int t1 = random.nextInt(1, 7);
        int t2 = random.nextInt(1, 7);
        int t3 = random.nextInt(1, 7);
        int t4 = random.nextInt(1, 7);
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
}
