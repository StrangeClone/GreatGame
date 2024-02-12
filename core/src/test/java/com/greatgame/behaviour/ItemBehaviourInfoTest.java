package com.greatgame.behaviour;

import com.greatgame.creature.ConcreteCreature;
import com.greatgame.entities.Creature;
import com.greatgame.environment.Behaviour;
import com.greatgame.environment.Location;
import com.greatgame.items.ConcreteItem;
import com.greatgame.items.StandardCollectManager;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class ItemBehaviourInfoTest extends TestCase {

    public void testApply() {
        ItemBehaviour behaviour = new ItemBehaviour(null, new ConcreteItem(null, 10, 10, 10,
                new StandardCollectManager(), null));
        behaviour.setName("thing");
        behaviour.setOriginalLocation(new Location(0,0,null, null));

        ItemBehaviourInfo info = new ItemBehaviourInfo(behaviour);

        behaviour.damage(3);

        List<Behaviour> fakeLocationContents = new ArrayList<>();
        fakeLocationContents.add(behaviour);

        info.apply(fakeLocationContents);
        assertEquals(10 ,behaviour.getItem().getHP());

        Creature dude = new ConcreteCreature();
        behaviour.getItem().collect(dude);

        info = new ItemBehaviourInfo(behaviour);

        info.apply(fakeLocationContents);
        assertEquals(10 ,behaviour.getItem().getHP());

        assertEquals(0, fakeLocationContents.size());
    }
}