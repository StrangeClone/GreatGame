package com.greatgame.application.mainMenuMode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.greatgame.behaviour.CreatureBehaviour;
import com.greatgame.environment.Location;
import com.greatgame.world.World;

public class GameSaver {

    static public void save(String name, World world) {
        if (!name.endsWith(".gg")) {
            name += ".gg";
        }
        FileHandle file = Gdx.files.local("assets/games/" + name);
        file.writeString(world.getSeed() + "\n", false);
        writePlayer(file, world.getEnvironment().getPlayer());
        file.writeString(world.getEnvironment().getCurrentMode() + "\n", true);
        for (Location l : world.getGeneratedLocations().values()) {
            writeLocation(file, l);
        }
    }

    static private void writePlayer(FileHandle file, CreatureBehaviour creatureBehaviour) {
        file.writeString(creatureBehaviour + "\n", true);
    }

    static private void writeLocation(FileHandle file, Location location) {
        file.writeString(location + "\n", true);
    }

}
