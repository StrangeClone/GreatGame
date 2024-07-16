package com.greatgame.itemsFactory;

import com.greatgame.behaviour.ItemBehaviour;
import com.greatgame.entities.Item;
import com.greatgame.environment.BehaviourDrawer;

public class CompositeTexturePattern extends ItemPattern {

    BehaviourDrawer drawer;

    public CompositeTexturePattern(String name, BehaviourDrawer drawer, int HP, int AC, int price, boolean touchable) {
        super(name, drawer.getTexture(), HP, AC, price, touchable);
        this.drawer = drawer;
    }

    @Override
    protected ItemBehaviour initBehaviour(Item item) {
        return new ItemBehaviour(drawer, item);
    }
}
