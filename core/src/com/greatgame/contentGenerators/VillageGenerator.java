package com.greatgame.contentGenerators;

import com.badlogic.gdx.math.Vector2;
import com.greatgame.environment.Behaviour;
import com.greatgame.environment.Environment;
import com.greatgame.environment.Location;

import static com.greatgame.behaviour.CreatureBehaviour.creaturesFactory;
import static com.greatgame.behaviour.ItemBehaviour.itemsFactory;
import static com.greatgame.environment.RandomMap.randomGenerator;

public class VillageGenerator extends ContentGenerator {

    private static final VillageGenerator instance = new VillageGenerator();

    private VillageGenerator() {}

    public static VillageGenerator getInstance() {
        return instance;
    }

    @Override
    protected void generate(Environment environment, Location location, long seed) {
        super.generate(environment, location, seed);
        int nHouses = randomGenerator.nextInt(4,9);
        for(int i = 0; i < nHouses; i++) {
            Behaviour house = itemsFactory.create("house");
            if(setRandomPosition(house, environment, location)) {
                environment.addBehaviour(house);
                Behaviour villager = creaturesFactory.create("villager");
                villager.setOriginalLocation(location);
                if(setVillagerPositionAroundHouse(environment, villager, house)) {
                    environment.addBehaviour(villager);
                }
            }
        }
    }

    private boolean setVillagerPositionAroundHouse(Environment environment, Behaviour villager, Behaviour house) {
        Vector2 position = new Vector2(house.getX(),
                house.getY() + house.getHeight() / 2 + villager.getHeight() / 2 + 20);
        if(environment.allowedPosition(villager,
                position.x,
                position.y, false)) {
            villager.setPosition(position.x, position.y);
        } else if (environment.allowedPosition(villager,
                position.x = house.getX(),
                position.y = house.getY() - house.getHeight() / 2 - villager.getHeight() / 2 - 20, false)) {
            villager.setPosition(position.x, position.y);
        } else if (environment.allowedPosition(villager,
                position.x = house.getX() + house.getWidth() / 2 + villager.getWidth() / 2 + 20,
                position.y = house.getY(), false)) {
            villager.setPosition(position.x, position.y);
        } else if (environment.allowedPosition(villager,
                position.x = house.getX() - house.getWidth() / 2 - villager.getWidth() / 2 - 20,
                position.y = house.getY(), false)) {
            villager.setPosition(position.x, position.y);
        } else {
            return false;
        }
        return true;
    }
}
