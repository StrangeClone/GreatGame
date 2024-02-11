package com.greatgame.entities;

public interface Item {
    void breakItem(int damage);
    int getAC();
    boolean canBeUsed();
    void use(Creature creature);
    boolean canBeCollected();
    void collect(Creature creature);
    boolean canBeEquipped();
    void equip(Creature creature);
    void unEquip();
}
