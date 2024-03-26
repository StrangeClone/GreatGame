package com.greatgame.creatureFactory;

import com.greatgame.entities.Creature;
import com.greatgame.entities.Item;

public class ItemEquipper implements CreaturePatternModifier {
    Item item;

    public ItemEquipper(Item item) {
        this.item = item;
    }

    @Override
    public void modify(Creature creature) {
        item.equip(creature);
    }
}
