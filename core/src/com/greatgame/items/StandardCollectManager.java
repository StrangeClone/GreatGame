package com.greatgame.items;

import com.greatgame.entities.Creature;
import com.greatgame.entities.Item;

import java.util.List;

public class StandardCollectManager implements CollectManager {
    private static final StandardCollectManager instance = new StandardCollectManager();
    private StandardCollectManager() {}
    public static StandardCollectManager getInstance() {
        return instance;
    }
    @Override
    public void collect(Item item, Creature creature) {
        List<Item> inventory = creature.getInventory();
        if(inventory.size() < 8) {
            inventory.add(item);
        }
    }
}
