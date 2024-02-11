package com.greatgame.creature;

import com.greatgame.entities.Characteristic;
import com.greatgame.entities.Creature;
import com.greatgame.entities.Item;
import com.greatgame.entities.Skill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.greatgame.environment.RandomMap.randomGenerator;
import static com.greatgame.skills.ConcreteSkill.skillFactory;

public class ConcreteCreature implements Creature {
    private final Map<Characteristic, Integer> characteristicValues;
    private final Map<String, Skill> skillMap;
    private int speed = 9;
    private int maxHealthPoints;
    private int healthPoints;
    private int coins = 0;
    private Item primaryItem = null;
    private Item headItem = null;
    private Item chestItem = null;
    List<Item> inventory;

    public ConcreteCreature() {
        characteristicValues = new HashMap<>();
        skillMap = new HashMap<>();
        setCharacteristic(Characteristic.Physique, 10);
        setCharacteristic(Characteristic.Agility, 10);
        inventory = new ArrayList<>();
    }

    @Override
    public int getCharacteristicBonus(Characteristic characteristic) {
        int value = characteristicValues.get(characteristic);
        return (int) Math.floor((value - 10d) / 2);
    }

    @Override
    public void setCharacteristic(Characteristic characteristic, int value) {
        characteristicValues.put(characteristic, value);
        if(characteristic == Characteristic.Physique) {
            updateMaxHP();
        }
    }

    @Override
    public int check(String skillName) {
        Skill skill = skillMap.get(skillName);
        int bonus = skill == null ? 0 : skill.getBonus(this);
        return randomGenerator.nextInt(1,21) + bonus;
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
        if(oldValue == 0) {
            healthPoints = maxHealthPoints;
        } else {
            healthPoints = healthPoints / oldValue * maxHealthPoints;
        }
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
    public Item getPrimaryItem() {
        return primaryItem;
    }

    @Override
    public void setPrimaryItem(Item item) {
        primaryItem = item;
    }

    @Override
    public Item getHeadItem() {
        return headItem;
    }

    @Override
    public void setHeadItem(Item item) {
        headItem = item;
    }

    @Override
    public Item getChestItem() {
        return chestItem;
    }

    @Override
    public void setChestItem(Item item) {
        chestItem = item;
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
}
