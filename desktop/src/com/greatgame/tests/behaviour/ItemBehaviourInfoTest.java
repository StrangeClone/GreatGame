package com.greatgame.tests.behaviour;

import com.badlogic.gdx.graphics.Texture;
import com.greatgame.behaviour.ItemBehaviour;
import com.greatgame.behaviour.ItemBehaviourInfo;
import com.greatgame.creature.ConcreteCreature;
import com.greatgame.entities.Creature;
import com.greatgame.environment.Environment;
import com.greatgame.environment.Location;
import com.greatgame.items.ConcreteItem;
import com.greatgame.items.StandardCollectManager;
import com.greatgame.tests.TestLauncher;
import com.greatgame.world.ConcreteEnvironment;

import static com.greatgame.tests.TestLauncher.assertEquals;

public class ItemBehaviourInfoTest {
    public static void main(String[] args) {
        TestLauncher.launchTest("ItemBehaviourInfo", () -> {

            Environment environment = new ConcreteEnvironment();

            ItemBehaviour behaviour = new ItemBehaviour(new Texture("textures/items/healing_potion.png"),
                    new ConcreteItem(null, 10, 10, 10,
                    new StandardCollectManager(), null));
            behaviour.setName("thing");
            behaviour.setOriginalLocation(new Location(0,0,null, null));
            environment.addBehaviour(behaviour);

            ItemBehaviourInfo info = new ItemBehaviourInfo(behaviour);

            behaviour.damage(3);

            info.apply(behaviour);
            assertEquals(10 ,behaviour.getItem().getHP(), "wrong HP");

            Creature dude = new ConcreteCreature();
            behaviour.getItem().collect(dude);

            info = new ItemBehaviourInfo(behaviour);

            info.apply(behaviour);
            assertEquals(10 ,behaviour.getItem().getHP(), "wrong HP");
        });
    }
}
