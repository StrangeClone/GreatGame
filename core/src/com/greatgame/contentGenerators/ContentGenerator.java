package com.greatgame.contentGenerators;

import com.greatgame.environment.Behaviour;
import com.greatgame.environment.Environment;
import com.greatgame.environment.Location;

import static com.greatgame.behaviour.CreatureBehaviour.creaturesFactory;
import static com.greatgame.behaviour.ItemBehaviour.itemsFactory;
import static com.greatgame.environment.RandomMap.randomGenerator;

public abstract class ContentGenerator {
    protected static final float PIXELS_PER_LOCATION = 10000f;
    protected void generate(Environment environment, Location location, long seed) {
        randomGenerator.setSeed(seed);
    }

    protected boolean setRandomPosition(Behaviour behaviour, Environment environment, Location location) {
        boolean ok = false;
        int i = 0;
        while (!ok && i < 10) {
            float x = randomGenerator.nextFloat(0, PIXELS_PER_LOCATION);
            float y = randomGenerator.nextFloat(0, PIXELS_PER_LOCATION);
            if (environment.allowedPosition(behaviour, x, y)) {
                setLocalPosition(behaviour, location, x, y);
                ok = true;
            }
            i++;
        }
        return ok;
    }

    protected void setLocalPosition(Behaviour behaviour, Location location, float localX, float localY) {
        behaviour.setPosition(location.getScreenX() * PIXELS_PER_LOCATION + localX,
                location.getScreenY() * PIXELS_PER_LOCATION + localY);
    }

    protected void placeCreaturesAtRandomPositions(Environment environment, Location location, String type, int n) {
        for(int i = 0; i < n; i++) {
            Behaviour creature = creaturesFactory.create(type);
            if(setRandomPosition(creature, environment, location)) {
                environment.addBehaviour(creature);
            }
        }
    }

    protected void placeItemsAtRandomPositions(Environment environment, Location location, String type, int n) {
        for(int i = 0; i < n; i++) {
            Behaviour creature = itemsFactory.create(type);
            if(setRandomPosition(creature, environment, location)) {
                environment.addBehaviour(creature);
            }
        }
    }
}
