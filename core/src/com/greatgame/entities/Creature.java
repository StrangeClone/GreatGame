package com.greatgame.entities;

import java.util.List;

public interface Creature {
    int getCharacteristicBonus(Characteristic characteristic);
    void setCharacteristic(Characteristic characteristic, int value);
    int check(String skillName);
    void upgradeSkill(String skillName);
    int getLevel(String skillName);
    int getHP();
    void setHP(int value);
    int getMaxHP();
    void updateMaxHP();
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
