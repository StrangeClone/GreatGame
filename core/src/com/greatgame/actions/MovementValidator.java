package com.greatgame.actions;

import com.badlogic.gdx.math.Vector2;
import com.greatgame.behaviour.CreatureBehaviour;
import com.greatgame.environment.Environment;

import java.util.List;

public class MovementValidator implements ActionValidator {
    List<Vector2> path;
    float dist;
    CreatureBehaviour behaviour;
    Vector2 start, end;

    public MovementValidator(CreatureBehaviour behaviour, Vector2 end) {
        this.behaviour = behaviour;
        this.start = new Vector2(behaviour.getX(), behaviour.getY());
        this.end = end;
    }

    @Override
    public boolean validate(Environment environment) {
        path = environment.findPath(behaviour, start, end);
        dist = 0;
        for (int i = 1; i < path.size(); i++) {
            dist += environment.dist(path.get(i - 1), path.get(i));
        }
        return !path.isEmpty();
    }

    public List<Vector2> getPath() {
        return path;
    }

    public float getDist() {
        return dist;
    }
}
