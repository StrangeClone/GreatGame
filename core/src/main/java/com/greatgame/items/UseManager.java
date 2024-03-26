package com.greatgame.items;

import com.greatgame.entities.Creature;
import com.greatgame.entities.Item;

public interface UseManager {
    void useOn(Item item, Creature creature);
}
