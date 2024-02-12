package com.greatgame.items;

import com.greatgame.entities.Creature;
import com.greatgame.entities.Item;

import java.util.List;

public class StandardCollectManager implements CollectManager {
    private boolean collected;
    public StandardCollectManager() {
        collected = false;
    }
    @Override
    public void collect(Item item, Creature creature) {
        List<Item> inventory = creature.getInventory();
        if(inventory.size() < 8) {
            inventory.add(item);
            collected = true;
        }
    }

    @Override
    public boolean hasBeenCollected() {
        return collected;
    }
}
