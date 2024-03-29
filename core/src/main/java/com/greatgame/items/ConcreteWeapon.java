package com.greatgame.items;

import com.greatgame.entities.Weapon;

import java.util.Random;

public class ConcreteWeapon implements Weapon {

    float range;
    int maxDamage;
    String skill;

    public ConcreteWeapon(float range, int maxDamage, String skill) {
        this.range = range;
        this.maxDamage = maxDamage;
        this.skill = skill;
    }

    @Override
    public float getRange() {
        return range;
    }

    @Override
    public int damage() {
        return new Random().nextInt(1,maxDamage + 1);
    }

    @Override
    public String getSkill() {
        return skill;
    }
}
