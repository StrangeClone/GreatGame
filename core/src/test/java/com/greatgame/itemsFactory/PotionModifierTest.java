package com.greatgame.itemsFactory;

import com.greatgame.creature.ConcreteCreature;
import com.greatgame.entities.Creature;
import com.greatgame.entities.Item;
import junit.framework.TestCase;

import static com.greatgame.behaviour.ItemBehaviour.itemsFactory;

public class PotionModifierTest extends TestCase {

    @Override
    protected void setUp() throws Exception {
        ItemInitializer.initializeItems();
    }

    public void testModify() {
        Creature dude = new ConcreteCreature();
        dude.setHP(3);
        Item potion = itemsFactory.create("healing potion").getItem();
        potion.use(dude);
        assertEquals(dude.getHP(), dude.getMaxHP());
    }
}