package com.greatgame.items;

import com.greatgame.entities.Creature;
import com.greatgame.entities.Item;

public class CoinsCollectManager implements CollectManager {

    private final int coins;

    public CoinsCollectManager(int coins) {
        this.coins = coins;
    }

    @Override
    public void collect(Item item, Creature creature) {
        int currentCoins = creature.getCoins();
        creature.setCoins(currentCoins + coins);
    }

    @Override
    public boolean hasBeenCollected() {
        return false;
    }
}
