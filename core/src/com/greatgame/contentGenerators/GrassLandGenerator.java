package com.greatgame.contentGenerators;

import com.greatgame.environment.Environment;
import com.greatgame.environment.Location;

import static com.greatgame.environment.RandomMap.randomGenerator;

public class GrassLandGenerator extends ContentGenerator {
    private static final GrassLandGenerator instance = new GrassLandGenerator();

    private GrassLandGenerator() {}

    public static GrassLandGenerator getInstance() {
        return instance;
    }

    @Override
    protected void generate(Environment environment, Location location, long seed) {
        super.generate(environment, location, seed);
        placeItemsAtRandomPositions(environment, location, "tree", randomGenerator.nextInt(1,6));
        placeItemsAtRandomPositions(environment, location, "bush", randomGenerator.nextInt(3,11));
        placeItemsAtRandomPositions(environment, location, "big_rock", randomGenerator.nextInt(3,7));
        placeItemsAtRandomPositions(environment, location, "little_bush", randomGenerator.nextInt(10, 21));
        placeItemsAtRandomPositions(environment, location, "little_rock", randomGenerator.nextInt(10, 21));
        placeItemsAtRandomPositions(environment, location, "flower", randomGenerator.nextInt(30, 51));
        if(randomGenerator.nextFloat(0,1) < 0.05f) {
            placeCreaturesAtRandomPositions(environment, location, "wolf", randomGenerator.nextInt(1,7));
        }
    }
}
