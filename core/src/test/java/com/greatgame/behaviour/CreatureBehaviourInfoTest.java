package com.greatgame.behaviour;

import com.greatgame.creature.ConcreteCreature;
import com.greatgame.environment.Behaviour;
import com.greatgame.environment.Location;
import com.greatgame.itemsFactory.ItemInitializer;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

import static com.greatgame.behaviour.ItemBehaviour.itemsFactory;

public class CreatureBehaviourInfoTest extends TestCase {

    @Override
    protected void setUp() {
        ItemInitializer.initializeItems();
    }

    public void testApply() {
        CreatureBehaviour behaviour = new CreatureBehaviour(null, new ConcreteCreature());
        behaviour.setName("dude");
        behaviour.setOriginalLocation(new Location(0,0,null, null));
        behaviour.getCreature().getInventory().add(itemsFactory.create("helm").getItem());

        CreatureBehaviourInfo info = new CreatureBehaviourInfo(behaviour);
        info.inventory.add("little rock");

        behaviour.damage(3);
        behaviour.setX(10);
        behaviour.setY(10);

        ItemBehaviour item = itemsFactory.create("flower");
        item.getItem().collect(behaviour.getCreature());

        List<Behaviour> fakeLocationContents = new ArrayList<>();
        fakeLocationContents.add(behaviour);

        info.apply(fakeLocationContents);
        assertEquals(6 ,behaviour.getCreature().getHP());
        assertEquals(0f, behaviour.getX());
        assertEquals(0f, behaviour.getY());
        assertEquals(2, behaviour.getCreature().getInventory().size());
        assertEquals("helm", behaviour.getCreature().getInventory().get(0).getType());
        assertEquals("little rock", behaviour.getCreature().getInventory().get(1).getType());
    }
}