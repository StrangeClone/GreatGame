package com.greatgame.creatureFactory;

import com.greatgame.entities.Characteristic;
import com.greatgame.entities.Creature;

public class CharacteristicSetter implements CreaturePatternModifier {
    Characteristic characteristic;
    int value;
    public CharacteristicSetter(Characteristic characteristic, int value) {
        this.characteristic = characteristic;
        this.value = value;
    }
    @Override
    public void modify(Creature creature) {
        creature.setCharacteristic(characteristic, value);
    }
}
