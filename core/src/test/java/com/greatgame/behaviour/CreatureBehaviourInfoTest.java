package com.greatgame.behaviour;

import com.greatgame.creature.ConcreteCreature;
import com.greatgame.environment.Behaviour;
import com.greatgame.environment.Location;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class CreatureBehaviourInfoTest extends TestCase {

    public void testApply() {
        CreatureBehaviour behaviour = new CreatureBehaviour(null, new ConcreteCreature());
        behaviour.setName("dude");
        behaviour.setOriginalLocation(new Location(0,0,null, null));

        CreatureBehaviourInfo info = new CreatureBehaviourInfo(behaviour);

        behaviour.damage(3);
        behaviour.setX(10);
        behaviour.setY(10);

        //TO DO: add test for the inventory when items factory is ready

        List<Behaviour> fakeLocationContents = new ArrayList<>();
        fakeLocationContents.add(behaviour);

        info.apply(fakeLocationContents);
        assertEquals(6 ,behaviour.getCreature().getHP());
        assertEquals(0f, behaviour.getX());
        assertEquals(0f, behaviour.getY());
    }
}