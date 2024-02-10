package com.greatgame.entities;

public interface Item {
    void breakItem(int damage);
    int getAC();
    boolean canBeUsed();
    void use();
    boolean canBeCollected();
    void collect();
    boolean canBeEquipped();
    void equip();
    void unEquip();
}
