package com.greatgame.tests.behaviour;

import com.badlogic.gdx.graphics.Texture;
import com.greatgame.behaviour.ItemBehaviour;
import com.greatgame.behaviour.ItemBehaviourInfo;
import com.greatgame.creature.ConcreteCreature;
import com.greatgame.entities.Creature;
import com.greatgame.environment.Behaviour;
import com.greatgame.environment.Location;
import com.greatgame.items.ConcreteItem;
import com.greatgame.items.StandardCollectManager;
import com.greatgame.tests.TestLauncher;

import java.util.ArrayList;
import java.util.List;

import static com.greatgame.tests.TestLauncher.assertEquals;

public class ItemBehaviourInfoTest {
    public static void main(String[] args) {
        TestLauncher.launchTest("ItemBehaviourInfo", () -> {
            ItemBehaviour behaviour = new ItemBehaviour(new Texture("textures/items/healing potion.png"),
                    new ConcreteItem(null, 10, 10, 10,
                    new StandardCollectManager(), null));
            behaviour.setName("thing");
            behaviour.setOriginalLocation(new Location(0,0,null, null));

            ItemBehaviourInfo info = new ItemBehaviourInfo(behaviour);

            behaviour.damage(3);

            List<Behaviour> fakeLocationContents = new ArrayList<>();
            fakeLocationContents.add(behaviour);

            info.apply(fakeLocationContents);
            assertEquals(10 ,behaviour.getItem().getHP(), "wrong HP");

            Creature dude = new ConcreteCreature();
            behaviour.getItem().collect(dude);

            info = new ItemBehaviourInfo(behaviour);

            info.apply(fakeLocationContents);
            assertEquals(10 ,behaviour.getItem().getHP(), "wrong HP");

            assertEquals(0, fakeLocationContents.size(), "wrong location size");
        });
    }
}
