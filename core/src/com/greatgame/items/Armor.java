package com.greatgame.items;

import com.greatgame.entities.ItemSlot;
import com.greatgame.entities.Creature;
import com.greatgame.entities.Item;

public class Armor extends EquipManager {
    private final ItemSlot slot;
    private final int value;
    public Armor(ItemSlot slot, int value) {
        this.slot = slot;
        this.value = value;
    }

    @Override
    public void equip(Item item, Creature creature) {
        super.equip(item, creature);
        if(equippedCreature.getItem(slot) == null) {
            equippedCreature.setItem(slot, item);
            equippedCreature.increaseAC(value);
        } else {
            throw new IllegalStateException("Slot " + slot + " is taken");
        }
    }

    @Override
    public void unEquip(Item item) {
        if(equippedCreature.getItem(slot) == item) {
            equippedCreature.setItem(slot, null);
            equippedCreature.increaseAC(-value);
        } else {
            throw new IllegalStateException("Unexpected: an Item equipped to a creature isn't inside the creature");
        }
        super.unEquip(item);
    }
}
