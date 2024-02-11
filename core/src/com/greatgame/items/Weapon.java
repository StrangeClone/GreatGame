package com.greatgame.items;

import com.greatgame.entities.Creature;
import com.greatgame.entities.Item;
import com.greatgame.entities.ItemSlot;

public class Weapon extends EquipManager {
    int damage;
    float range;
    String skill;
    public Weapon(int damage, float range, String skill) {
        this.damage = damage;
        this.range = range;
        this.skill = skill;
    }

    @Override
    public void equip(Item item, Creature creature) {
        super.equip(item, creature);
        if(equippedCreature.getItem(ItemSlot.Primary) == null) {
            equippedCreature.setItem(ItemSlot.Primary, item);
        } else {
            throw new IllegalStateException("Primary slot taken");
        }
    }

    @Override
    public void unEquip(Item item) {
        if(equippedCreature.getItem(ItemSlot.Primary) == item) {
            equippedCreature.setItem(ItemSlot.Primary, null);
        } else {
            throw new IllegalStateException("Unexpected: an Item equipped to a creature isn't inside the creature");
        }
        super.unEquip(item);
    }
}
