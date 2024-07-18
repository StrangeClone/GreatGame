package com.greatgame.items;

import com.greatgame.creature.ConcreteCreature;
import com.greatgame.entities.Creature;
import com.greatgame.entities.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PotionUseManagerTest {

    @Test
    public void testUseOn() {
        Creature dude = new ConcreteCreature();
        dude.setHP(0);
        Item potion = new ConcreteItem("", 10, 10, 0, null, null, PotionUseManager.get());
        potion.use(dude);
        assertEquals(5, dude.getHP());
        potion.use(dude);
        assertEquals(dude.getMaxHP(), dude.getHP());
    }
}
