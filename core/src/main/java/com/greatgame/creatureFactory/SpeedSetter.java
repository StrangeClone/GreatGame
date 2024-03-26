package com.greatgame.creatureFactory;

import com.greatgame.entities.Creature;

public class SpeedSetter implements CreaturePatternModifier {
    int speed;
    public SpeedSetter(int speed) {
        this.speed = speed;
    }
    @Override
    public void modify(Creature creature) {
        creature.setSpeed(speed);
    }
}
