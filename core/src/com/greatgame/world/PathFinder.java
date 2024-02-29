package com.greatgame.world;

import com.badlogic.gdx.math.Vector2;
import com.greatgame.behaviour.CreatureBehaviour;

import java.util.List;

public interface PathFinder {
    List<Vector2> findPath(CreatureBehaviour creature, Vector2 start, Vector2 end);
}
