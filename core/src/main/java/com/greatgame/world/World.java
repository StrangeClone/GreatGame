package com.greatgame.world;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.greatgame.behaviour.CreatureBehaviour;
import com.greatgame.behaviour.ItemBehaviour;
import com.greatgame.entities.Characteristic;
import com.greatgame.entities.Creature;
import com.greatgame.entities.Item;
import com.greatgame.environment.Environment;
import com.greatgame.environment.Location;
import com.greatgame.environment.ModeName;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import static com.greatgame.explorationBehaviourState.PlayerExplorationBehaviourState.locationCoordinate;

public class World {
    private final long seed;
    private final WorldGenerator generator;

    private final Environment environment;

    public World(long seed, Environment environment) {
        this.seed = seed;
        generator = new StandardWorldGenerator(seed);
        this.environment = environment;
        environment.setWorld(this);
    }

    public World(FileHandle handle) throws IOException {
        Scanner scanner = new Scanner(handle.readString());

        this.seed = Long.parseLong(scanner.next());
        CreatureBehaviour player = loadPlayer(scanner);

        ModeName mode = parseMode(scanner);
        int originalX = scanner.nextInt();
        int originalY = scanner.nextInt();

        generator = new StandardWorldGenerator(seed, scanner);
        this.environment = new ConcreteEnvironment();
        environment.setWorld(this);

        environment.setPlayer(player, null);
        environment.setOriginalLocation(originalX, originalY);

        environment.checkContents(locationCoordinate(player.getX(), originalX),
                locationCoordinate(player.getY(), originalY));
        environment.triggerModeChange(mode);

        scanner.close();
    }

    private CreatureBehaviour loadPlayer(Scanner reader) throws IOException {
        CreatureBehaviour result = CreatureBehaviour.creaturesFactory.create("player");

        loadCharacteristics(reader, result.getCreature());
        loadSkills(reader, result.getCreature());
        result.getCreature().setHP(reader.nextInt());
        result.getCreature().setCoins(reader.nextInt());
        loadInventory(reader, result.getCreature());
        result.setPosition(reader.nextFloat(), reader.nextFloat());
        result.increaseEP(reader.nextInt());

        return result;
    }

    private void loadCharacteristics(Scanner reader, Creature player) throws IOException {
        for (int i = 0; i < 2; i++) {
            player.setCharacteristic(parseCharacteristic(reader.next()),
                    reader.nextInt());
        }
    }

    private Characteristic parseCharacteristic(String token) throws IOException {
        if ("Agility".equals(token)) {
            return Characteristic.Agility;
        } else if ("Physique".equals(token)) {
            return Characteristic.Physique;
        } else {
            throw new IOException("Unexpected characteristic");
        }
    }

    private void loadSkills(Scanner reader, Creature player) {
        for (String token = reader.next(); !"HP:".equals(token); token = reader.next()) {
            int level = reader.nextInt();
            for (int i = 0; i < level; i++) {
                player.upgradeSkill(token);
            }
        }
    }

    private void loadInventory(Scanner reader, Creature player) {
        for (String token = reader.next(); !"pos:".equals(token); token = reader.next()) {
            Item item = ItemBehaviour.itemsFactory.create(token).getItem();
            player.getInventory().add(item);
        }
    }

    private ModeName parseMode(Scanner reader) throws IOException {
        String token = reader.next();
        if ("explorationMode".equals(token)) {
            return ModeName.explorationMode;
        } else if ("fightMode".equals(token)) {
            return ModeName.fightMode;
        } else if ("tripMode".equals(token)) {
            return ModeName.tripMode;
        } else if ("mainMenuMode".equals(token)) {
            return ModeName.mainMenuMode;
        } else {
            throw new IOException("Unexpected mode");
        }
    }

    public long getSeed() {
        return seed;
    }

    public Location generate(int x, int y) {
        return generator.generate(x,y);
    }

    public Map<Vector2, Location> getGeneratedLocations() {
        return generator.getGeneratedLocations();
    }

    public void generateContents(int x, int y) {
        generator.generateContents(seed, environment, x, y);
    }

    public Environment getEnvironment() {
        return environment;
    }
}
