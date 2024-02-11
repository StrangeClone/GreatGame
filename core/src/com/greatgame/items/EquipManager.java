package com.greatgame.items;

import com.greatgame.entities.Creature;
import com.greatgame.entities.Item;
import com.greatgame.entities.ItemSlot;

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
}
