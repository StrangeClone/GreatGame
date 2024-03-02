package com.greatgame.fightBehaviourState;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.greatgame.behaviour.BehaviourState;
import com.greatgame.behaviour.CreatureBehaviour;
import com.greatgame.environment.Action;

public abstract class FightBehaviourState extends BehaviourState {
    int actions;
    Action currentAction;
    boolean active = false;

    public FightBehaviourState(CreatureBehaviour behaviour) {
        super(behaviour);
        this.behaviour = behaviour;
    }

    public void activate() {
        active = true;
        actions = 2;
    }

    @Override
    public void act(float delta) {
        if(isActive()) {
            drawSelector();
        }
        if (currentAction != null && isActive()) {
            currentAction.update(delta);
            if(currentAction.finished()) {
                actions--;
                currentAction = null;
            }
        }
    }

    private void drawSelector() {
        NinePatchDrawable selector = new NinePatchDrawable();
        selector.setPatch(new NinePatch(new Texture("textures/selector.png"), 5, 5, 5,5));
        Batch batch = getEnvironment().getStage().getBatch();
        batch.begin();
        float width = behaviour.getWidth() * 1.25f, height = behaviour.getHeight() * 1.25f;
        selector.draw(getEnvironment().getStage().getBatch(),
                behaviour.getX() - width / 2, behaviour.getY() - height / 2, width, height);
        Texture actionIcon = new Texture("textures/action.png");
        for(int i = 0; i < actions; i++) {
            batch.draw(actionIcon, behaviour.getX() - width / 2 + i * (actionIcon.getWidth() + 5),
                    behaviour.getY() - height / 2 - actionIcon.getHeight() - 10);
        }
        batch.end();
    }

    public void startAction(Action action, long durationInMillis) {
        if(isActive() && currentAction == null) {
            currentAction = action;
            action.start(durationInMillis);
        }
    }

    public void deactivate() {
        active = false;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isDone() {
        return actions == 0;
    }
}
