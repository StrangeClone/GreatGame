package com.greatgame.environment;

import com.badlogic.gdx.math.Vector2;

import java.util.List;

public interface Environment {
    Behaviour getPlayer();
    void draw(int delta);
    void checkContents(int x, int y);
    boolean allowedMovement(float x, float y);
    float dist(Vector2 v1, Vector2 v2);
    boolean freeView(Vector2 v1, Vector2 v2);
    void triggerModeChange(ModeName newMode);
    List<Vector2> findPath(Vector2 start, Vector2 end);
}
