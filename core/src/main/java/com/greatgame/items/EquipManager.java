package com.greatgame.items;

import com.greatgame.entities.Creature;
import com.greatgame.entities.Item;
import com.greatgame.entities.ItemSlot;

public abstract class EquipManager {
    protected Creature equippedCreature = null;

    public void equip(Item item, Creature creature) {
        equippedCreature = creature;
    }
    protected void putItemInSlot(Item item, ItemSlot slot) {
        if (equippedCreature.getItem(slot) != null) {
            equippedCreature.getInventory().add(equippedCreature.getItem(slot));
        }
        equippedCreature.setItem(slot, item);
    }
    public void unEquip(Item item) {
        equippedCreature = null;
    }

    public boolean isEquipped() {
        return equippedCreature != null;
    }
}
