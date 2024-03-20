package com.greatgame.contentGenerators;

import com.greatgame.environment.Behaviour;
import com.greatgame.environment.Environment;
import com.greatgame.environment.Location;

import static com.greatgame.behaviour.CreatureBehaviour.creaturesFactory;
import static com.greatgame.behaviour.ItemBehaviour.itemsFactory;
import static com.greatgame.environment.RandomMap.randomGenerator;

public abstract class ContentGenerator {
    public static final float PIXELS_PER_LOCATION = 2500f;

    protected void generate(Environment environment, Location location, long seed) {
        randomGenerator.setSeed(seed);
    }

    protected boolean setRandomPosition(Behaviour behaviour, Environment environment, Location location) {
        for (int i = 0; i < 10; i++) {
            float x = randomGenerator.nextFloat(behaviour.getWidth() / 2, PIXELS_PER_LOCATION - behaviour.getWidth() / 2);
            float y = randomGenerator.nextFloat(behaviour.getHeight() / 2, PIXELS_PER_LOCATION - behaviour.getHeight() / 2);
            if (environment.allowedPosition(behaviour, x + location.getScreenX() * PIXELS_PER_LOCATION,
                    y + location.getScreenY() * PIXELS_PER_LOCATION, false)) {
                setLocalPosition(behaviour, location, x, y);
                behaviour.setOriginalLocation(location);
                return true;
            }
        }
        return false;
    }

    protected void setLocalPosition(Behaviour behaviour, Location location, float localX, float localY) {
        behaviour.setPosition(location.getScreenX() * PIXELS_PER_LOCATION + localX,
                location.getScreenY() * PIXELS_PER_LOCATION + localY);
        behaviour.setOriginalLocation(location);
    }

    protected void placeCreaturesAtRandomPositions(Environment environment, Location location, String type, int n) {
        for (int i = 0; i < n; i++) {
            Behaviour creature = creaturesFactory.create(type);
            if (setRandomPosition(creature, environment, location)) {
                environment.addBehaviour(creature);
            }
        }
    }

    protected void placeItemsAtRandomPositions(Environment environment, Location location, String type, int n) {
        for (int i = 0; i < n; i++) {
            Behaviour creature = itemsFactory.create(type);
            if (setRandomPosition(creature, environment, location)) {
                environment.addBehaviour(creature);
            }
        }
    }
}
