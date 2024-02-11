package com.greatgame.items;

import com.greatgame.entities.Creature;
import com.greatgame.entities.Item;

public class PotionUseManager implements UseManager {
    private static final  PotionUseManager instance = new PotionUseManager();
    private PotionUseManager() {}

    public static PotionUseManager get() {
        return instance;
    }

    @Override
    public void useOn(Item item, Creature creature) {
        int current = creature.getHP();
        creature.setHP(current + 5);
    }
}
