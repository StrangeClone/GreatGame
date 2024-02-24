package com.greatgame.tests.creatureFactory;

import com.greatgame.creatureFactory.CreatureInitializer;
import com.greatgame.entities.Characteristic;
import com.greatgame.entities.Creature;
import com.greatgame.itemsFactory.ItemInitializer;
import com.greatgame.skills.SkillInitializer;
import com.greatgame.tests.TestLauncher;

import static com.greatgame.behaviour.CreatureBehaviour.creaturesFactory;
import static com.greatgame.tests.TestLauncher.assertEquals;

public class CharacteristicSetterTest {
    public static void main(String[] args) {
        TestLauncher.launchTest("CharacteristicSetter", () -> {
            SkillInitializer.initializeSkills();
            ItemInitializer.initializeItems();
            CreatureInitializer.initializeCreatures();
            Creature creature = creaturesFactory.create("bandit").getCreature();
            assertEquals(1, creature.getCharacteristicBonus(Characteristic.Physique), "error in Physique");
            assertEquals(1, creature.getCharacteristicBonus(Characteristic.Agility), "error in Agility");
        });
    }
}
