package com.greatgame.items;

import com.greatgame.entities.Creature;
import com.greatgame.entities.Item;

public abstract class EquipManager {
    protected Creature equippedCreature = null;

    public void equip(Item item, Creature creature) {
        if(equippedCreature != null) {
            throw new IllegalStateException("Item is already equipped");
        }
        equippedCreature = creature;
    }
    public void unEquip(Item item) {
        equippedCreature = null;
    }

    public boolean isEquipped() {
        return equippedCreature != null;
    }
}
