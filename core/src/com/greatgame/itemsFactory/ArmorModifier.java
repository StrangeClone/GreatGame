package com.greatgame.itemsFactory;

import com.greatgame.entities.ItemSlot;
import com.greatgame.items.Armor;
import com.greatgame.items.ConcreteItem;

public class ArmorModifier implements ItemPatternModifier {
    private final Armor armor;

    public ArmorModifier(ItemSlot slot, int value) {
        this.armor = new Armor(slot, value);
    }
    @Override
    public void modify(ConcreteItem item) {
        item.setEquipManager(armor);
    }
}
