package com.greatgame.items;

import com.greatgame.entities.Creature;
import com.greatgame.entities.Item;

public interface CollectManager {
    void collect(Item item, Creature creature);

    boolean hasBeenCollected();
}
