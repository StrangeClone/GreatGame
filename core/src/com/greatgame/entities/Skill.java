package com.greatgame.entities;

public interface Skill {
    String getName();
    int getLevel();
    int getBonus(Creature creature);
    void levelUp(Creature creature);
}
