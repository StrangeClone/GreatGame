package com.greatgame.environment;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.greatgame.behaviour.CreatureBehaviour;
import com.greatgame.world.World;

import java.util.List;

public interface Environment {

    void addBehaviour(Behaviour behaviour);
    CreatureBehaviour getPlayer();
    void setPlayer(CreatureBehaviour behaviour);
    Stage getStage();
    List<Location> getLoadedLocations();
    void setWorld(World world);
    void update(float delta);
    void checkContents(int x, int y);
    boolean freePoint(float x, float y);
    Behaviour behaviourInPosition(float x, float y);
    boolean allowedPosition(Behaviour behaviour, float newX, float newY, boolean touchable);
    float dist(Vector2 v1, Vector2 v2);
    boolean freeView(Behaviour b1, Behaviour b2);
    boolean freeView(Vector2 pos1, Vector2 pos2);
    void triggerModeChange(ModeName newMode);
    ModeName getCurrentMode();
    ModeName getNextMode();
    List<Vector2> findPath(CreatureBehaviour creature, Vector2 start, Vector2 end);
}
