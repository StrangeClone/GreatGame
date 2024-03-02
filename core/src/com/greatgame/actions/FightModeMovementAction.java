package com.greatgame.actions;

import com.badlogic.gdx.math.Vector2;
import com.greatgame.behaviour.CreatureBehaviour;
import com.greatgame.environment.Action;
import com.greatgame.environment.Environment;

import java.util.List;

public class FightModeMovementAction extends Action {
    MovementValidator validator;
    Environment environment;
    CreatureBehaviour creature;
    Vector2 end;
    int currentIndex = 0;

    public FightModeMovementAction(Environment environment, CreatureBehaviour creature, Vector2 end) {
        super("Movement");
        validator = new MovementValidator(creature, end);
        this.environment = environment;
        this.creature = creature;
        this.end = end;
    }

    @Override
    public boolean validate() {
        boolean result = validator.validate(environment);
        currentIndex = validator.getPath().size() - 1;
        return result;
    }

    @Override
    public boolean finished() {
        return currentIndex == 0;
    }

    @Override
    public void update(float delta) {
        List<Vector2> path = validator.getPath();
        Vector2 nextPosition = path.get(currentIndex - 1);
        Vector2 previousPosition = path.get(currentIndex);
        Vector2 direction = new Vector2(nextPosition.x - previousPosition.x, nextPosition.y - previousPosition.y).nor();
        Vector2 currentPos = new Vector2(creature.getX(), creature.getY());
        float speed = validator.getDist() / 3;
        Vector2 newPosition = new Vector2(currentPos.x + delta * direction.x * speed,
                currentPos.y + delta * direction.y * speed);
        float sectionLength = environment.dist(nextPosition, previousPosition);
        float currentDist = environment.dist(previousPosition, newPosition);
        if (sectionLength <= currentDist) {
            creature.setPosition(nextPosition.x, nextPosition.y);
            currentIndex--;
        } else {
            creature.setPosition(newPosition.x, newPosition.y);
        }
    }
}
