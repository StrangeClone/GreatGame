package com.greatgame.tests.behaviour;

import com.badlogic.gdx.graphics.Texture;
import com.greatgame.behaviour.ItemBehaviour;
import com.greatgame.environment.Location;
import com.greatgame.items.ConcreteItem;
import com.greatgame.tests.TestLauncher;

import static com.greatgame.tests.TestLauncher.assertEquals;

public class ItemBehaviourTest {
    public static void main(String[] args) {
        TestLauncher.launchTest("ItemBehaviour", () -> {
            ItemBehaviour behaviour = new ItemBehaviour(new Texture("textures/items/healing potion.png"),
                    new ConcreteItem("", 10, 10, 0, null, null));
            behaviour.setName("thing");
            behaviour.setOriginalLocation(new Location(0, 0, null, null));
            behaviour.damage(3);
            assertEquals(7, behaviour.getItem().getHP(), "wrong HP");
        });
    }
}
