package com.greatgame.tests.behaviour;

import com.badlogic.gdx.graphics.Texture;
import com.greatgame.behaviour.CreatureBehaviour;
import com.greatgame.creature.ConcreteCreature;
import com.greatgame.environment.Location;
import com.greatgame.tests.TestLauncher;

import static com.greatgame.tests.TestLauncher.assertEquals;

public class CreatureBehaviourTest {
    public static void main(String[] args) {
        TestLauncher.launchTest("CreatureBehaviour", () -> {
            CreatureBehaviour behaviour = new CreatureBehaviour(new Texture("textures/creatures/villager.png"), new ConcreteCreature());
            behaviour.setName("dude");
            behaviour.setOriginalLocation(new Location(0, 0, null, null));
            behaviour.damage(3);
            assertEquals(3, behaviour.getCreature().getHP(), "wrong HP");
        });
    }
}
