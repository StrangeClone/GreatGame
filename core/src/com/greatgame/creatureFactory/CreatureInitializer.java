package com.greatgame.creatureFactory;

import com.greatgame.entities.Characteristic;

import static com.greatgame.behaviour.CreatureBehaviour.creaturesFactory;
import static com.greatgame.behaviour.ItemBehaviour.itemsFactory;

public class CreatureInitializer {
    public static void initializeCreatures() {
        creaturesFactory.addPattern(new CreatureCompositePattern("bandit", null).
                modify(new CharacteristicSetter(Characteristic.Physique, 12)).
                modify(new CharacteristicSetter(Characteristic.Agility, 12)).
                modify(new SkillGiver("fencing", 1)).
                modify(new SkillGiver("vitality", 1)).
                modify(new ItemEquipper(itemsFactory.create("short sword").getItem())).
                modify(new ItemEquipper(itemsFactory.create("helm").getItem())).
                modify(new ItemEquipper(itemsFactory.create("leather armor").getItem())).
                modify(new ItemGiver(itemsFactory.create("silver coins").getItem())));
        creaturesFactory.addPattern(new CreatureCompositePattern("wolf", null).
                modify(new SpeedSetter(12)).
                modify(new CharacteristicSetter(Characteristic.Physique, 14)).
                modify(new CharacteristicSetter(Characteristic.Agility, 13)).
                modify(new SkillGiver("bite", 2)).
                modify(new SkillGiver("vitality", 1)).
                modify(new ItemEquipper(itemsFactory.create("wolf fangs").getItem())).
                modify(new ItemGiver(itemsFactory.create("fur").getItem())));
        creaturesFactory.addPattern(new CreatureCompositePattern("bear", null).
                modify(new SpeedSetter(12)).
                modify(new CharacteristicSetter(Characteristic.Physique, 16)).
                modify(new SkillGiver("bite", 2)).
                modify(new SkillGiver("vitality", 2)).
                modify(new ItemEquipper(itemsFactory.create("bear fangs").getItem())).
                modify(new ItemGiver(itemsFactory.create("fur").getItem(),
                        itemsFactory.create("fur").getItem())));
        creaturesFactory.addPattern(new CreatureCompositePattern("fox", null).
                modify(new CharacteristicSetter(Characteristic.Physique, 8)).
                modify(new CharacteristicSetter(Characteristic.Agility, 14)).
                modify(new SkillGiver("bite", 1)).
                modify(new SkillGiver("vitality", 1)).
                modify(new ItemEquipper(itemsFactory.create("fox fangs").getItem())).
                modify(new ItemGiver(itemsFactory.create("fur").getItem())));
        CreaturePatternModifier weaponSmithModifier = new ItemGiver(itemsFactory.create("short sword").getItem(),
                itemsFactory.create("short sword").getItem(),
                itemsFactory.create("long sword").getItem(),
                itemsFactory.create("dagger").getItem(),
                itemsFactory.create("dagger").getItem());
        CreaturePatternModifier armorerModifier = new ItemGiver(itemsFactory.create("chain mail").getItem(),
                itemsFactory.create("helm").getItem(),
                itemsFactory.create("helm").getItem());
        CreaturePatternModifier hunterModifier = new ItemGiver(itemsFactory.create("long bow").getItem(),
                itemsFactory.create("long bow").getItem(),
                itemsFactory.create("short bow").getItem(),
                itemsFactory.create("short bow").getItem(),
                itemsFactory.create("leather armor").getItem());
        CreaturePatternModifier healerModifier = new ItemGiver(itemsFactory.create("healing potion").getItem(),
                itemsFactory.create("healing potion").getItem(),
                itemsFactory.create("healing potion").getItem());
        creaturesFactory.addPattern(new CreatureCompositePattern("villager", null).
                modify(new RandomModifier().addModifier(weaponSmithModifier, 1).
                        addModifier(armorerModifier, 1).
                        addModifier(hunterModifier, 1).
                        addModifier(healerModifier, 1)));
    }
}