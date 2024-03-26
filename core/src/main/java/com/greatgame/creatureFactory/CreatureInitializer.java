package com.greatgame.creatureFactory;

import com.badlogic.gdx.graphics.Texture;
import com.greatgame.entities.Characteristic;

import static com.greatgame.behaviour.CreatureBehaviour.creaturesFactory;
import static com.greatgame.behaviour.ItemBehaviour.itemsFactory;
import static com.greatgame.factory.BehaviourRefiner.behaviourRefiner;

public class CreatureInitializer {
    public static void initializeCreatures() {
        creaturesFactory.addPattern(new CreatureCompositePattern("bandit", new Texture("textures/creatures/bandit.png")).
                modify(new CharacteristicSetter(Characteristic.Physique, 12)).
                modify(new CharacteristicSetter(Characteristic.Agility, 12)).
                modify(new SkillGiver("fencing", 1)).
                modify(new SkillGiver("vitality", 1)).
                modify(new ItemEquipper(itemsFactory.create("short_sword").getItem())).
                modify(new ItemEquipper(itemsFactory.create("helm").getItem())).
                modify(new ItemEquipper(itemsFactory.create("leather_armor").getItem())).
                modify(new ItemGiver(itemsFactory.create("silver_coins").getItem())));
        behaviourRefiner.addPattern(new StandardCreatureRefiningPattern("bandit", true));

        creaturesFactory.addPattern(new CreatureCompositePattern("wolf", new Texture("textures/creatures/wolf.png")).
                modify(new SpeedSetter(12)).
                modify(new CharacteristicSetter(Characteristic.Physique, 14)).
                modify(new CharacteristicSetter(Characteristic.Agility, 13)).
                modify(new SkillGiver("bite", 2)).
                modify(new SkillGiver("vitality", 1)).
                modify(new ItemEquipper(itemsFactory.create("wolf_fangs").getItem())).
                modify(new ItemGiver(itemsFactory.create("fur").getItem())));
        behaviourRefiner.addPattern(new StandardCreatureRefiningPattern("wolf", true));

        creaturesFactory.addPattern(new CreatureCompositePattern("bear", new Texture("textures/creatures/bear.png")).
                modify(new SpeedSetter(12)).
                modify(new CharacteristicSetter(Characteristic.Physique, 16)).
                modify(new SkillGiver("bite", 2)).
                modify(new SkillGiver("vitality", 2)).
                modify(new ItemEquipper(itemsFactory.create("bear_fangs").getItem())).
                modify(new ItemGiver(itemsFactory.create("fur").getItem(),
                        itemsFactory.create("fur").getItem())));
        behaviourRefiner.addPattern(new StandardCreatureRefiningPattern("bear", true));

        creaturesFactory.addPattern(new CreatureCompositePattern("fox", new Texture("textures/creatures/fox.png")).
                modify(new CharacteristicSetter(Characteristic.Physique, 8)).
                modify(new CharacteristicSetter(Characteristic.Agility, 14)).
                modify(new SkillGiver("bite", 1)).
                modify(new ItemEquipper(itemsFactory.create("fox_fangs").getItem())).
                modify(new ItemGiver(itemsFactory.create("fur").getItem())));
        behaviourRefiner.addPattern(new StandardCreatureRefiningPattern("fox", false));

        creaturesFactory.addPattern(new CreatureCompositePattern("villager", new Texture("textures/creatures/villager.png")));
        behaviourRefiner.addPattern(new VillagerRefiningPattern("villager"));

        creaturesFactory.addPattern(new CreatureCompositePattern("player", new Texture("textures/creatures/villager.png")));
        behaviourRefiner.addPattern(new PlayerRefiningPattern("player"));
    }
}
