package com.greatgame.creature;

import com.greatgame.entities.*;

import java.util.*;

import static com.greatgame.skills.ConcreteSkill.skillFactory;

public class ConcreteCreature implements Creature {
    private final String type;
    private final Map<Characteristic, Integer> characteristicValues;
    private final Map<String, Skill> skillMap;
    private int speed = 9;
    private int AC = 8;
    private int maxHealthPoints = 6;
    private int healthPoints = 6;
    private int coins = 0;
    private final Map<ItemSlot, Item> equippedItemsMap;
    List<Item> inventory;

    public ConcreteCreature() {
        characteristicValues = new HashMap<>();
        skillMap = new HashMap<>();
        setCharacteristic(Characteristic.Physique, 10);
        setCharacteristic(Characteristic.Agility, 10);
        equippedItemsMap = new HashMap<>();
        inventory = new ArrayList<>();
        type = "";
    }

    public ConcreteCreature(String type) {
        characteristicValues = new HashMap<>();
        skillMap = new HashMap<>();
        setCharacteristic(Characteristic.Physique, 10);
        setCharacteristic(Characteristic.Agility, 10);
        equippedItemsMap = new HashMap<>();
        inventory = new ArrayList<>();
        this.type = type;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public int getCharacteristic(Characteristic characteristic) {
        return characteristicValues.get(characteristic);
    }

    @Override
    public int getCharacteristicBonus(Characteristic characteristic) {
        int value = characteristicValues.get(characteristic);
        return (int) Math.floor((value - 10d) / 2);
    }

    @Override
    public void setCharacteristic(Characteristic characteristic, int value) {
        characteristicValues.put(characteristic, Math.max(value, 0));
        if(characteristic == Characteristic.Physique) {
            updateMaxHP();
        }
    }

    @Override
    public int check(String skillName) {
        Skill skill = skillMap.get(skillName);
        int bonus = skill == null ? 0 : skill.getBonus(this);
        return new Random().nextInt(1,21) + bonus;
    }

    @Override
    public void upgradeSkill(String skillName) {
        Skill skill = skillMap.get(skillName);
        if(skill == null) {
            skillMap.put(skillName, skillFactory.create(skillName));
            skill = skillMap.get(skillName);
        }
        skill.levelUp(this);
    }

    @Override
    public int getLevel(String skillName) {
        Skill skill = skillMap.get(skillName);
        if(skill == null) {
            return 0;
        }
        return skill.getLevel();
    }

    @Override
    public int getAC() {
        return AC + getCharacteristicBonus(Characteristic.Agility);
    }

    @Override
    public void increaseAC(int value) {
        AC += value;
    }

    @Override
    public int getHP() {
        return healthPoints;
    }

    @Override
    public void setHP(int value) {
        healthPoints = Math.min(value, maxHealthPoints);
    }

    @Override
    public int getMaxHP() {
        return maxHealthPoints;
    }

    @Override
    public void updateMaxHP() {
        int level = getLevel("vitality");
        int bonus = getCharacteristicBonus(Characteristic.Physique);
        int oldValue = maxHealthPoints;
        maxHealthPoints = (6 + bonus) * (level + 1);
        setHP((int)((healthPoints / (float)oldValue) * maxHealthPoints));
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(int value) {
        speed = value;
    }

    @Override
    public Item getItem(ItemSlot slot) {
        return equippedItemsMap.get(slot);
    }

    @Override
    public void setItem(ItemSlot slot, Item item) {
        equippedItemsMap.put(slot, item);
    }

    @Override
    public List<Item> getInventory() {
        return inventory;
    }

    @Override
    public int getCoins() {
        return coins;
    }

    @Override
    public void setCoins(int value) {
        coins = value;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Characteristic, Integer> e : characteristicValues.entrySet()) {
            result.append(e.getKey()).append(" ").append(e.getValue()).append(" ");
        }
        for (Map.Entry<String, Skill> e : skillMap.entrySet()) {
            result.append(e.getKey()).append(" ").append(e.getValue().getLevel()).append(" ");
        }
        result.append("HP: ").append(healthPoints).append(" ");
        result.append(coins).append(" ");
        for (Map.Entry<ItemSlot, Item> e : equippedItemsMap.entrySet()) {
            result.append(e.getKey()).append(" ").append(e.getValue().getType()).append(" ");
        }
        for (Item item : getInventory()) {
            result.append(item.getType()).append(" ");
        }
        return result.toString();
    }
}
