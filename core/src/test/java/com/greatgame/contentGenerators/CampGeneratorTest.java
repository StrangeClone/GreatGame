package com.greatgame.contentGenerators;

import com.greatgame.creatureFactory.CreatureInitializer;
import com.greatgame.environment.Environment;
import com.greatgame.environment.Location;
import com.greatgame.itemsFactory.ItemInitializer;
import com.greatgame.skills.SkillInitializer;
import com.greatgame.world.ConcreteEnvironment;
import junit.framework.TestCase;

public class CampGeneratorTest extends TestCase {

    @Override
    protected void setUp() throws Exception {
        SkillInitializer.initializeSkills();
        ItemInitializer.initializeItems();
        CreatureInitializer.initializeCreatures();
    }
    public void testGenerate() {
        Environment environment = new ConcreteEnvironment();
        ContentGenerator camp = CampGenerator.getInstance();
        Location location = new Location(0, 0, null, null);
        camp.generate(environment, location, 23);
    }
}