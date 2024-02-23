package com.greatgame.contentGenerators;

import com.greatgame.behaviour.ItemBehaviour;
import com.greatgame.environment.Behaviour;
import com.greatgame.environment.Environment;
import com.greatgame.environment.Location;

import static com.greatgame.environment.RandomMap.randomGenerator;

public class CampGenerator extends ContentGenerator {
    private static final CampGenerator instance = new CampGenerator();

    private CampGenerator() {}

    public static CampGenerator getInstance() {
        return instance;
    }

    @Override
    protected void generate(Environment environment, Location location, long seed) {
        super.generate(environment, location, seed);
        ItemBehaviour fireCamp = ItemBehaviour.itemsFactory.create("fireplace");
        setLocalPosition(fireCamp, location, PIXELS_PER_LOCATION / 2, PIXELS_PER_LOCATION / 2);
        environment.addBehaviour(fireCamp);
        for(int i = 0; i < 3; i++) {
            Behaviour tent = ItemBehaviour.itemsFactory.create("tent");
            setLocalPosition(tent, location, PIXELS_PER_LOCATION / 20 * (float) Math.sin(Math.PI * 2 / 3 * i),
                    PIXELS_PER_LOCATION / 20 * (float) Math.cos(Math.PI * 2 / 3 * i));
            environment.addBehaviour(fireCamp);
        }
        placeCreaturesAtRandomPositions(environment, location, "bandit", randomGenerator.nextInt(3,7));
        addLegEasterEgg(environment, location);
    }

    private void addLegEasterEgg(Environment environment, Location location) {
        if(legRandomWorld()) {
            Behaviour leg = ItemBehaviour.itemsFactory.create("wooden leg");
            setLocalPosition(leg, location, PIXELS_PER_LOCATION / 2 + 200, PIXELS_PER_LOCATION / 2);
            environment.addBehaviour(leg);
        }
    }

    private boolean legRandomWorld() {
        for (Character c : "garpez".toCharArray()) {
            char randomChar = (char) (randomGenerator.nextInt(0, 26) + 'a');
            if(randomChar != c) {
                return false;
            }
        }
        return true;
    }
}
