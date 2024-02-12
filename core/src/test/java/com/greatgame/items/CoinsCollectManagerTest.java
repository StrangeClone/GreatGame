package com.greatgame.items;

import com.greatgame.creature.ConcreteCreature;
import com.greatgame.entities.Creature;
import com.greatgame.entities.Item;
import junit.framework.TestCase;

public class CoinsCollectManagerTest extends TestCase {

    public void testCollect() {
        Creature creature = new ConcreteCreature();
        Item coins = new ConcreteItem("", 10, 10, 0, new CoinsCollectManager(30), null);
        coins.collect(creature);
        assertEquals(30, creature.getCoins());
        coins = new ConcreteItem("", 10, 10, 0, new CoinsCollectManager(45), null);
        coins.collect(creature);
        assertEquals(75, creature.getCoins());
    }
}