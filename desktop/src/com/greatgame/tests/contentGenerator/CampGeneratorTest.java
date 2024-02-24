package com.greatgame.tests.contentGenerator;


import com.badlogic.gdx.scenes.scene2d.Actor;
import com.greatgame.environment.*;
import com.greatgame.tests.TestLauncher;
import com.greatgame.world.World;

import static com.greatgame.tests.TestLauncher.assertTrue;

public class CampGeneratorTest {

    public static void main(String[] args) {
        TestLauncher.launchTest("CampGenerator", () -> {
            World world = VillageGeneratorTest.initEnvironment(StructureNames.Camp);
            int tents = 0, fireplace = 0, bandit = 0;
            for(Actor a : world.getEnvironment().getStage().getActors()) {
                Behaviour b = (Behaviour) a;
                if("tent".equals(b.getType())) {
                    tents++;
                } else if("fireplace".equals(b.getType())) {
                    fireplace++;
                } else if("bandit".equals(b.getType())) {
                    bandit++;
                }
            }
            assertTrue(tents == 3, "Error: wrong number of tents");
            assertTrue(fireplace == 1, "Error: wrong number of fireplace");
            assertTrue(bandit >= 3 && bandit <= 6, "Error: wrong number of bandits");
        });
    }
}
