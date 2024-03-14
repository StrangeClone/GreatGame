package com.greatgame.application.dialogs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.greatgame.application.Mode;
import com.greatgame.behaviour.CreatureBehaviour;
import com.greatgame.entities.Creature;
import com.greatgame.entities.Item;
import com.greatgame.entities.ItemSlot;

public class InventoryDialog extends Dialog {
    Table equipment;
    Table inventory;
    public InventoryDialog(CreatureBehaviour player) {
        super("", Mode.skin);

        Label title = new Label("Player's inventory", getSkin());

        getContentTable().add(title).padTop(5).row();

        equipment = new Table();
        displayEquipment(player.getCreature());
        getContentTable().add(equipment).row();

        inventory = new Table();
        displayInventory(player.getCreature());
        getContentTable().add(inventory).row();

        Label coins = new Label("Coins: " + player.getCreature().getCoins(), getSkin());
        getContentTable().add(coins).left();

        button("Back");
    }

    private void displayEquipment(Creature player) {
        equipment.clear();

        Label headLabel = new Label("head:", getSkin());
        equipment.add(headLabel).left();
        equipment.add(createItemIcon(player, ItemSlot.Head)).width(75).height(75).row();

        Label chestLabel = new Label("chest:", getSkin());
        equipment.add(chestLabel).left();
        equipment.add(createItemIcon(player, ItemSlot.Chest)).width(75).height(75).row();

        Label firstHand = new Label("first hand:", getSkin());
        equipment.add(firstHand).left();
        equipment.add(createItemIcon(player, ItemSlot.Primary)).width(75).height(75).row();
    }

    private void displayInventory(Creature player) {
        inventory.clear();

        for (int i = 0; i < 8; i++) {
            final int index = i;
            Container<Image> imageContainer = createItemIcon(player, i);
            imageContainer.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Item item = player.getInventory().get(index);
                    if (item != null) {
                        player.getInventory().remove(index);
                        if (item.canBeEquipped()) {
                            item.equip(player);
                            displayEquipment(player);
                            displayInventory(player);
                        } else if (item.canBeUsed()) {
                            item.use(player);
                            displayInventory(player);
                        }
                    }
                }
            });
            inventory.add(imageContainer).width(75).height(75);
            if ((i + 1) % 4 == 0) {
                inventory.row();
            }
        }
    }

    static private Container<Image> createItemIcon(Creature creature, ItemSlot itemSlot) {
        Container<Image> imageContainer;
        try {
            imageContainer = itemIcon(creature.getItem(itemSlot));
        } catch (NullPointerException e) {
            imageContainer = new Container<>();
        }
        imageContainer.background(new NinePatchDrawable(Mode.skin.getPatch("cell")));
        return imageContainer;
    }

    static public Container<Image> createItemIcon(Creature creature, int inventoryIndex) {
        Container<Image> imageContainer;
        try {
            imageContainer = itemIcon(creature.getInventory().get(inventoryIndex));
        } catch (IndexOutOfBoundsException e) {
            imageContainer = new Container<>();
        }
        imageContainer.background(new NinePatchDrawable(Mode.skin.getPatch("cell")));
        return imageContainer;
    }

    static private Container<Image> itemIcon(Item item) {
        Container<Image> imageContainer;
        Texture itemTexture = new Texture("textures/items/" + item.getType() + ".png");
        Image itemIcon = new Image(itemTexture);
        imageContainer = new Container<>(itemIcon);
        float maxDim = Math.max(itemTexture.getWidth(), itemTexture.getHeight());
        float xRatio = itemTexture.getWidth() / maxDim;
        float yRatio = itemTexture.getHeight() / maxDim;
        imageContainer.fill(0.75f * xRatio, 0.75f * yRatio);
        return imageContainer;
    }
}
