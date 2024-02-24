package com.greatgame.tests.creatureFactory;

import com.greatgame.creatureFactory.CreatureInitializer;
import com.greatgame.entities.Creature;
import com.greatgame.itemsFactory.ItemInitializer;
import com.greatgame.skills.SkillInitializer;
import com.greatgame.tests.TestLauncher;

import static com.greatgame.behaviour.CreatureBehaviour.creaturesFactory;
import static com.greatgame.tests.TestLauncher.assertEquals;

public class SpeedSetterTest {
    public static void main(String[] args) {
        TestLauncher.launchTest("SpeedSetter", () -> {
            SkillInitializer.initializeSkills();
            ItemInitializer.initializeItems();
            CreatureInitializer.initializeCreatures();
            Creature creature = creaturesFactory.create("wolf").getCreature();
            assertEquals(12, creature.getSpeed(), "Speed not set");
        });
    }
}
