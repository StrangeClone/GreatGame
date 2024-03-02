package com.greatgame.application;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.greatgame.behaviour.CreatureBehaviour;
import com.greatgame.entities.Item;

public class InventoryDialog extends Dialog {
    public InventoryDialog(CreatureBehaviour player) {
        super("", Mode.skin);

        Label title = new Label("Player's inventory", getSkin());
        getContentTable().add(title).colspan(4).padTop(5).row();

        for(int i = 0; i < 8; i++) {
            Container<Image> imageContainer;
            try {
                Item item = player.getCreature().getInventory().get(i);
                Image itemIcon = new Image(new Texture("textures/items/" + item.getType() + ".png"));
                imageContainer = new Container<>(itemIcon);
                imageContainer.fill(0.75f, 0.75f);
            } catch (IndexOutOfBoundsException e) {
                imageContainer = new Container<>();
            }
            imageContainer.background(new NinePatchDrawable(getSkin().getPatch("cell")));
            getContentTable().add(imageContainer).width(75).height(75);
            if((i + 1) % 4 == 0) {
                getContentTable().row();
            }
        }

        button("Back");
    }
}
