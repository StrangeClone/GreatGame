package com.greatgame.items;

import com.greatgame.entities.Creature;
import com.greatgame.entities.Item;

public class ConcreteItem implements Item {
    String type;
    int HP;
    int AC;
    int prize;
    CollectManager collectManager;
    EquipManager equipManager;
    UseManager useManager = null;

    public ConcreteItem(String type, int HP, int AC, int prize, CollectManager collectManager, EquipManager equipManager) {
        this.type = type;
        this.HP = HP;
        this.AC = AC;
        this.prize = prize;
        this.collectManager = collectManager;
        this.equipManager = equipManager;
    }

    public ConcreteItem(String type, int HP, int AC, int prize,
                        CollectManager collectManager,
                        EquipManager equipManager,
                        UseManager useManager) {
        this.type = type;
        this.HP = HP;
        this.AC = AC;
        this.prize = prize;
        this.collectManager = collectManager;
        this.equipManager = equipManager;
        this.useManager = useManager;
    }

    @Override
    public String getType() {
        return type;
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
    public void setHP(int value) {
        HP = value;
    }

    @Override
    public int getHP() {
        return HP;
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
    public boolean hasBeenCollected() {
        if(canBeCollected()) {
            return collectManager.hasBeenCollected();
        }
        return false;
    }

    @Override
    public void collect(Creature creature) {
        if (canBeCollected() && !hasBeenCollected()) {
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
