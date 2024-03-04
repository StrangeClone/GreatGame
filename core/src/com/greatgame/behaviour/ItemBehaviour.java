package com.greatgame.behaviour;

import com.badlogic.gdx.graphics.Texture;
import com.greatgame.entities.Item;
import com.greatgame.environment.Behaviour;
import com.greatgame.factory.Factory;

public class ItemBehaviour extends Behaviour {
    public static final Factory<ItemBehaviour> itemsFactory = new Factory<>();
    private final Item item;

    public ItemBehaviour(Texture texture, Item item) {
        super(texture, item.getType());
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    @Override
    public int getAC() {
        return item.getAC();
    }

    @Override
    public void damage(int damage) {
        item.breakItem(damage);
        if (item.getHP() < 0) {
            remove();
        }
    }

    @Override
    public int getSpeed() {
        return 0;
    }

    @Override
    public void saveBehaviourInfo() {
        originalLocation.addInfo(new ItemBehaviourInfo(this));
    }
}
