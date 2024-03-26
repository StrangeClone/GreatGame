package com.greatgame.application.tripMode;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.greatgame.environment.BiomeNames;
import com.greatgame.environment.Location;
import com.greatgame.environment.StructureNames;

import java.util.HashMap;
import java.util.Map;

public class LocationIcon extends Actor {
    private static Map<BiomeNames, Texture> biomesTextures;
    private static Map<StructureNames, Texture> structuresTextures;
    Texture texture;
    final float x, y;
    public LocationIcon(Location location) {
        if (location.getStructure() != null) {
            texture = structuresTextures.get(location.getStructure().getName());
        } else {
            texture = biomesTextures.get(location.getBiome().getName());
        }
        x = location.x;
        y = location.y;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, x * 50, y * 50, 50, 50);
    }


    static public void initializeIcons() {
        biomesTextures = new HashMap<>();
        biomesTextures.put(BiomeNames.GrassLand, new Texture("textures/biomes/grassland.png"));
        biomesTextures.put(BiomeNames.Forest, new Texture("textures/biomes/forest.png"));

        structuresTextures = new HashMap<>();
        structuresTextures.put(StructureNames.Village, new Texture("textures/structures/village.png"));
        structuresTextures.put(StructureNames.Camp, new Texture("textures/structures/camp.png"));
    }
}
