package com.greatgame.creature;

import com.greatgame.entities.Characteristic;
import com.greatgame.entities.Creature;
import com.greatgame.skills.SkillInitializer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConcreteCreatureTest {

    @Test
    public void testGetCharacteristicBonus() {
        Creature creature = new ConcreteCreature();
        SkillInitializer.initializeSkills();
        creature.setCharacteristic(Characteristic.Physique, 3);
        assertEquals(-4, creature.getCharacteristicBonus(Characteristic.Physique));
        creature.setCharacteristic(Characteristic.Physique, 4);
        assertEquals(-3, creature.getCharacteristicBonus(Characteristic.Physique));
        creature.setCharacteristic(Characteristic.Physique, 5);
        assertEquals(-3, creature.getCharacteristicBonus(Characteristic.Physique));
        creature.setCharacteristic(Characteristic.Physique, 6);
        assertEquals(-2, creature.getCharacteristicBonus(Characteristic.Physique));
        creature.setCharacteristic(Characteristic.Physique, 7);
        assertEquals(-2, creature.getCharacteristicBonus(Characteristic.Physique));
        creature.setCharacteristic(Characteristic.Physique, 8);
        assertEquals(-1, creature.getCharacteristicBonus(Characteristic.Physique));
        creature.setCharacteristic(Characteristic.Physique, 9);
        assertEquals(-1, creature.getCharacteristicBonus(Characteristic.Physique));
        creature.setCharacteristic(Characteristic.Physique, 10);
        assertEquals(0, creature.getCharacteristicBonus(Characteristic.Physique));
        creature.setCharacteristic(Characteristic.Physique, 11);
        assertEquals(0, creature.getCharacteristicBonus(Characteristic.Physique));
        creature.setCharacteristic(Characteristic.Physique, 12);
        assertEquals(1, creature.getCharacteristicBonus(Characteristic.Physique));
        creature.setCharacteristic(Characteristic.Physique, 13);
        assertEquals(1, creature.getCharacteristicBonus(Characteristic.Physique));
        creature.setCharacteristic(Characteristic.Physique, 14);
        assertEquals(2, creature.getCharacteristicBonus(Characteristic.Physique));
        creature.setCharacteristic(Characteristic.Physique, 15);
        assertEquals(2, creature.getCharacteristicBonus(Characteristic.Physique));
        creature.setCharacteristic(Characteristic.Physique, 16);
        assertEquals(3, creature.getCharacteristicBonus(Characteristic.Physique));
        creature.setCharacteristic(Characteristic.Physique, 17);
        assertEquals(3, creature.getCharacteristicBonus(Characteristic.Physique));
        creature.setCharacteristic(Characteristic.Physique, 18);
        assertEquals(4, creature.getCharacteristicBonus(Characteristic.Physique));
    }

    @Test
    public void testSetCharacteristic() {
        Creature creature = new ConcreteCreature();
        SkillInitializer.initializeSkills();
        creature.setCharacteristic(Characteristic.Physique, 12);
        assertEquals(7, creature.getMaxHP());
        assertEquals(7, creature.getHP());
        creature.setCharacteristic(Characteristic.Physique, 18);
        assertEquals(10, creature.getMaxHP());
        assertEquals(10, creature.getHP());
    }

    @Test
    public void testCheck() {
        Creature creature = new ConcreteCreature();
        SkillInitializer.initializeSkills();
        creature.upgradeSkill("archery");
        creature.upgradeSkill("archery");
        creature.upgradeSkill("archery");
        creature.setCharacteristic(Characteristic.Agility, 15);
        for(int i = 0; i < 1000; i++) {
            int check = creature.check("archery");
            assertTrue(check >= 6 && check <= 25);
        }
    }

    @Test
    public void testUpgradeSkill() {
        Creature creature = new ConcreteCreature();
        SkillInitializer.initializeSkills();
        assertEquals(0, creature.getLevel("archery"));
        creature.upgradeSkill("archery");
        assertEquals(1, creature.getLevel("archery"));
        creature.upgradeSkill("archery");
        assertEquals(2, creature.getLevel("archery"));
        creature.upgradeSkill("archery");
        assertEquals(3, creature.getLevel("archery"));
    }
}
