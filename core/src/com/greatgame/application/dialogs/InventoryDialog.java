package com.greatgame.application.dialogs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.greatgame.application.Mode;
import com.greatgame.behaviour.CreatureBehaviour;
import com.greatgame.entities.Creature;
import com.greatgame.entities.Item;

public class InventoryDialog extends Dialog {
    public InventoryDialog(CreatureBehaviour player) {
        super("", Mode.skin);

        Label title = new Label("Player's inventory", getSkin());

        int columns = 4;
        getContentTable().add(title).colspan(columns).padTop(5).row();

        for(int i = 0; i < 8; i++) {
            Container<Image> imageContainer = createItemIcon(player.getCreature(), i);
            getContentTable().add(imageContainer).width(75).height(75);
            if((i + 1) % columns == 0) {
                getContentTable().row();
            }
        }

        Label coins = new Label("Coins: " + player.getCreature().getCoins(), getSkin());
        getContentTable().add(coins).colspan(columns).left();

        button("Back");
    }

    static public Container<Image> createItemIcon(Creature creature, int inventoryIndex) {
        Container<Image> imageContainer;
        try {
            Item item = creature.getInventory().get(inventoryIndex);
            Texture itemTexture = new Texture("textures/items/" + item.getType() + ".png");
            Image itemIcon = new Image(itemTexture);
            imageContainer = new Container<>(itemIcon);
            float maxDim = Math.max(itemTexture.getWidth(), itemTexture.getHeight());
            float xRatio = itemTexture.getWidth() / maxDim;
            float yRatio = itemTexture.getHeight() / maxDim;
            imageContainer.fill(0.75f * xRatio, 0.75f * yRatio);
        } catch (IndexOutOfBoundsException e) {
            imageContainer = new Container<>();
        }
        imageContainer.background(new NinePatchDrawable(Mode.skin.getPatch("cell")));
        return imageContainer;
    }
}
