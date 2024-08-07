package com.greatgame.itemsFactory;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.greatgame.behaviour.ItemBehaviour;
import com.greatgame.entities.Item;
import com.greatgame.environment.StaticDrawer;
import com.greatgame.factory.Pattern;
import com.greatgame.items.ConcreteItem;

import java.util.ArrayList;
import java.util.List;

public class ItemPattern extends Pattern<ItemBehaviour> {
    private final Texture texture;
    private final int HP;
    private final int AC;
    private final int price;
    private final boolean touchable;
    List<ItemPatternModifier> modifiers;

    public ItemPattern(String name, Texture texture, int HP, int AC, int price, boolean touchable) {
        super(name);
        this.texture = texture;
        this.HP = HP;
        this.AC = AC;
        this.price = price;
        this.touchable = touchable;
        modifiers = new ArrayList<>();
    }

    public ItemPattern modify(ItemPatternModifier modifier) {
        modifiers.add(modifier);
        return this;
    }

    protected ItemBehaviour initBehaviour(Item item) {
        return new ItemBehaviour(new StaticDrawer(texture), item);
    }

    @Override
    public ItemBehaviour build() {
        ConcreteItem item = new ConcreteItem(getName(), HP, AC, price);
        for(ItemPatternModifier modifier : modifiers) {
            modifier.modify(item);
        }
        ItemBehaviour behaviour = initBehaviour(item);
        if(!touchable) {
            behaviour.setTouchable(Touchable.disabled);
        }
        return behaviour;
    }
}
