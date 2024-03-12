package com.greatgame.application.dialogs;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.greatgame.application.Mode;
import com.greatgame.behaviour.CreatureBehaviour;
import com.greatgame.entities.Characteristic;
import com.greatgame.skills.SkillInitializer;

public class SkillDialog extends Dialog {

    public SkillDialog(CreatureBehaviour player) {
        super("", Mode.skin);

        Label title = new Label("Player's skills", getSkin());
        getContentTable().add(title).colspan(3).padTop(5).row();

        Label physiqueLabel = new Label("Physique: " + player.getCreature().getCharacteristic(Characteristic.Physique), getSkin());
        getContentTable().add(physiqueLabel).colspan(3).padLeft(10).padTop(5).left().row();
        Label agilityLabel = new Label("Agility: " + player.getCreature().getCharacteristic(Characteristic.Agility), getSkin());
        getContentTable().add(agilityLabel).colspan(3).padLeft(10).padTop(5).left().row();

        Label ep = new Label("EP: " + player.getEP(), getSkin());
        getContentTable().add(ep).colspan(3).left().padLeft(10).padTop(5).row();

        for(String skillName : SkillInitializer.playerAvailableSkills()) {
            Label skillLabel = new Label(skillName, getSkin());
            Label levelLabel = new Label(Integer.toString(player.getCreature().getLevel(skillName)), getSkin());
            Button levelUpButton = new TextButton("+", getSkin());
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
            getContentTable().add(skillLabel).padTop(5).left().padLeft(10);
            getContentTable().add(levelLabel).padLeft(10);
            getContentTable().add(levelUpButton).width(40).height(40).padLeft(10).padRight(10);
            getContentTable().row();
        }

        getContentTable().padBottom(10);

        button("OK");
    }

}
