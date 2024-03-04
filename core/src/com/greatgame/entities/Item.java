package com.greatgame.entities;

public interface Item {
    String getType();
    void breakItem(int damage);
    int getAC();
    void setHP(int value);
    int getHP();
    boolean canBeUsed();
    void use(Creature creature);
    boolean canBeCollected();
    boolean hasBeenCollected();
    void collect(Creature creature);
    boolean canBeEquipped();
    void equip(Creature creature);
    void unEquip();
    Weapon toWeapon();
}
