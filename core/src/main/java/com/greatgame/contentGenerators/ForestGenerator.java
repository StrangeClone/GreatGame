package com.greatgame.contentGenerators;

import com.greatgame.environment.Environment;
import com.greatgame.environment.Location;

import static com.greatgame.environment.RandomMap.randomGenerator;

public class ForestGenerator extends ContentGenerator {
    private static final ForestGenerator instance = new ForestGenerator();
    private ForestGenerator() {}

    public static ForestGenerator getInstance() {
        return instance;
    }

    @Override
    protected void generate(Environment environment, Location location, long seed) {
        super.generate(environment, location, seed);
        placeItemsAtRandomPositions(environment, location, "tree", randomGenerator.nextInt(10, 21));
        placeItemsAtRandomPositions(environment, location, "bush", randomGenerator.nextInt(10, 21));
        placeItemsAtRandomPositions(environment, location, "big_rock", randomGenerator.nextInt(3, 7));
        placeItemsAtRandomPositions(environment, location, "little_bush", randomGenerator.nextInt(15, 26));
        placeItemsAtRandomPositions(environment, location, "little_rock", randomGenerator.nextInt(10, 21));
        placeItemsAtRandomPositions(environment, location, "flower", randomGenerator.nextInt(30, 51));
        if(randomGenerator.nextFloat() < 0.05f) {
            placeCreaturesAtRandomPositions(environment, location, "fox", randomGenerator.nextInt(1,3));
        }
        if(randomGenerator.nextFloat() < 0.05f) {
            placeCreaturesAtRandomPositions(environment, location, "bear", 1);
        }
    }
}
