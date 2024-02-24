package com.greatgame.tests.behaviour;

import com.badlogic.gdx.graphics.Texture;
import com.greatgame.behaviour.CreatureBehaviour;
import com.greatgame.behaviour.CreatureBehaviourInfo;
import com.greatgame.behaviour.ItemBehaviour;
import com.greatgame.creature.ConcreteCreature;
import com.greatgame.environment.Behaviour;
import com.greatgame.environment.Location;
import com.greatgame.itemsFactory.ItemInitializer;
import com.greatgame.tests.TestLauncher;

import java.util.ArrayList;
import java.util.List;

import static com.greatgame.behaviour.ItemBehaviour.itemsFactory;
import static com.greatgame.tests.TestLauncher.assertEquals;

public class CreatureBehaviourInfoTest {
    public static void main(String[] args) {
        TestLauncher.launchTest("CreatureBehaviourInfo", () -> {
            ItemInitializer.initializeItems();
            CreatureBehaviour behaviour = new CreatureBehaviour(new Texture("textures/creatures/villager.png"), new ConcreteCreature());
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
            assertEquals(6 ,behaviour.getCreature().getHP(), "wrong HP");
            assertEquals(0f, behaviour.getX(), "wrong x");
            assertEquals(0f, behaviour.getY(), "wrogn y");
            assertEquals(2, behaviour.getCreature().getInventory().size(), "wrong inventory size");
            assertEquals("helm", behaviour.getCreature().getInventory().get(0).getType(), "wrong inventory item");
            assertEquals("little rock", behaviour.getCreature().getInventory().get(1).getType(), "wrong inventory item");
        });
    }
}
