package com.greatgame.items;

import com.greatgame.entities.Creature;
import com.greatgame.entities.Item;

public class ConcreteItem implements Item {
    int HP;
    int AC;
    int prize;
    CollectManager collectManager;
    EquipManager equipManager;
    UseManager useManager = null;

    public ConcreteItem(int HP, int AC, int prize, CollectManager collectManager, EquipManager equipManager) {
        this.HP = HP;
        this.AC = AC;
        this.prize = prize;
        this.collectManager = collectManager;
        this.equipManager = equipManager;
    }

    public ConcreteItem(int HP, int AC, int prize,
                        CollectManager collectManager,
                        EquipManager equipManager,
                        UseManager useManager) {
        this.HP = HP;
        this.AC = AC;
        this.prize = prize;
        this.collectManager = collectManager;
        this.equipManager = equipManager;
        this.useManager = useManager;
    }
    @Override
    public void breakItem(int damage) {
        HP -= damage;
    }

    @Override
    public int getAC() {
        return AC;
    }

    @Override
    public boolean canBeUsed() {
        return useManager != null;
    }

    @Override
    public void use(Creature creature) {
        if(canBeUsed()) {
            useManager.useOn(this, creature);
        }
    }

    @Override
    public boolean canBeCollected() {
        return collectManager != null;
    }

    @Override
    public void collect(Creature creature) {
        if (canBeCollected()) {
            collectManager.collect(this, creature);
        }
    }

    @Override
    public boolean canBeEquipped() {
        return equipManager != null;
    }

    @Override
    public void equip(Creature creature) {
        if(canBeEquipped()) {
            equipManager.equip(this, creature);
        }
    }

    @Override
    public void unEquip() {
        if(canBeEquipped()) {
            equipManager.unEquip(this);
        }
    }
}
