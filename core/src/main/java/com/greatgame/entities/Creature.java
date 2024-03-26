package com.greatgame.entities;

import java.util.List;

public interface Creature {
    String getType();
    int getCharacteristic(Characteristic characteristic);
    int getCharacteristicBonus(Characteristic characteristic);
    void setCharacteristic(Characteristic characteristic, int value);
    int check(String skillName);
    void upgradeSkill(String skillName);
    int getLevel(String skillName);
    int getAC();
    void increaseAC(int value);
    int getHP();
    void setHP(int value);
    int getMaxHP();
    void updateMaxHP();
    int getSpeed();
    void setSpeed(int value);
    Item getItem(ItemSlot slot);
    void setItem(ItemSlot slot, Item item);
    List<Item> getInventory();
    int getCoins();
    void setCoins(int value);
}
