package com.greatgame.entities;

import java.util.List;

public interface Creature {
    int getCharacteristicBonus(Creature creature);
    int check(String skillName);
    void upgradeSkill(String skillName);
    int getHP();
    int setHP(int value);
    int getMaxHP();
    void setMaxHP(int value);
    int getSpeed();
    void setSpeed(int value);
    Item getPrimaryItem();
    void setPrimaryItem(Item item);
    Item getHeadItem();
    void setHeadItem(Item item);
    Item getChestItem();
    void setChestItem(Item item);
    List<Item> getInventory();
    int getCoins();
    void setCoins(int value);
}
