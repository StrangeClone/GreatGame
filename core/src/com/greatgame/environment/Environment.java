package com.greatgame.environment;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.greatgame.world.World;

import java.util.List;

public interface Environment {

    void addBehaviour(Behaviour behaviour);
    Behaviour getPlayer();
    void setPlayer(Behaviour behaviour);
    Stage getStage();
    void setWorld(World world);
    void update(float delta);
    void checkContents(int x, int y);
    boolean freePoint(float x, float y);
    boolean allowedPosition(Behaviour behaviour, float newX, float newY, boolean touchable);
    float dist(Vector2 v1, Vector2 v2);
    boolean freeView(Behaviour b1, Behaviour b2);
    void triggerModeChange(ModeName newMode);
    ModeName getCurrentMode();
    ModeName getNextMode();
    List<Vector2> findPath(Vector2 start, Vector2 end);
}
