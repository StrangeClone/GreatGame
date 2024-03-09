package com.greatgame.application.dialogs;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.greatgame.application.Mode;
import com.greatgame.entities.Creature;
import com.greatgame.entities.Item;

public class TalkDialog extends Dialog {
    Container<Image> selectedItemImage1;
    Item selectedItem1;
    Label priceLabel1;
    Container<Image> selectedItemImage2;
    Item selectedItem2;
    Label priceLabel2;
    Table inventoryTable;
    public TalkDialog(Creature first, Creature second) {
        super("", Mode.skin);

        selectedItemImage1 = InventoryDialog.createItemIcon(first, 9);
        getContentTable().add(selectedItemImage1).width(75).height(75).left();
        priceLabel1 = new Label("Price: 0", getSkin());
        getContentTable().add(priceLabel1).colspan(3).width(255).left();

        selectedItemImage2 = InventoryDialog.createItemIcon(second, 9);
        getContentTable().add(selectedItemImage2).width(75).height(75).left();
        priceLabel2 = new Label("Price: 0", getSkin());
        getContentTable().add(priceLabel2).colspan(3).width(225).left();
        getContentTable().row();

        Button sellButton = new TextButton("Sell", getSkin());
        sellButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (selectedItem1 != null && second.getCoins() > selectedItem1.getPrice()) {
                    second.setCoins(second.getCoins() - selectedItem1.getPrice());
                    first.setCoins(first.getCoins() + selectedItem1.getPrice());
                    first.getInventory().remove(selectedItem1);
                    second.getInventory().add(selectedItem1);
                    selectedItem1 = null;
                    selectedItemImage1.setActor(null);
                    setUpInventory(first, second);
                }
            }
        });
        getContentTable().add(sellButton).width(150).height(75).colspan(4);
        Button buyButton = new TextButton("Buy", getSkin());
        buyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (selectedItem2 != null && first.getCoins() > selectedItem2.getPrice()) {
                    first.setCoins(first.getCoins() - selectedItem2.getPrice());
                    second.setCoins(second.getCoins() + selectedItem2.getPrice());
                    second.getInventory().remove(selectedItem2);
                    first.getInventory().add(selectedItem2);
                    selectedItem2 = null;
                    selectedItemImage2.setActor(null);
                    setUpInventory(first, second);
                }
            }
        });
        getContentTable().add(buyButton).width(150).height(75).colspan(4);
        getContentTable().row();

        inventoryTable = new Table();
        setUpInventory(first, second);
        getContentTable().add(inventoryTable).colspan(8);

        button("back");
    }

    private enum Role {
        seller,
        buyer
    }

    private void setUpInventory(Creature first, Creature second) {

        inventoryTable.clear();
        createInventoryRow(Role.seller, first, 0, 4);
        createInventoryRow(Role.buyer, second, 0, 4);
        inventoryTable.row();
        createInventoryRow(Role.seller, first, 4, 8);
        createInventoryRow(Role.buyer, second, 4, 8);
        inventoryTable.row();

        Label coins1 = new Label("Coins: " + first.getCoins(), getSkin());
        inventoryTable.add(coins1).colspan(4).left();
        Label coins2 = new Label("Coins: " + second.getCoins(), getSkin());
        inventoryTable.add(coins2).colspan(4).left();
    }

    private void createInventoryRow(Role role, Creature creature, int first, int last) {
        int cols = last - first;
        for (int i = 0; i < cols; i++) {
            Container<Image> imageContainer = InventoryDialog.createItemIcon(creature, i + first);
            final int inventoryIndex = i + first;
            imageContainer.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Image newImage = new Image(imageContainer.getActor().getDrawable());
                    float fillX = imageContainer.getFillX(), fillY = imageContainer.getFillY();
                    if (role == Role.seller) {
                        selectedItemImage1.setActor(newImage);
                        selectedItemImage1.fill(fillX, fillY);
                        selectedItem1 = creature.getInventory().get(inventoryIndex);
                        priceLabel1.setText("Price: " + selectedItem1.getPrice());
                    } else if (role == Role.buyer) {
                        selectedItemImage2.setActor(newImage);
                        selectedItemImage2.fill(fillX, fillY);
                        selectedItem2 = creature.getInventory().get(inventoryIndex);
                        priceLabel2.setText("Price: " + selectedItem2.getPrice());
                    }
                }
            });
            inventoryTable.add(imageContainer).width(75).height(75);
        }
    }
}
