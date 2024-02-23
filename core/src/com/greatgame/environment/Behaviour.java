package com.greatgame.environment;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;
import java.util.List;

public abstract class Behaviour extends Actor {
    private final Texture texture;
    protected final String type;
    protected Location originalLocation;
    protected Environment environment;
    private final List<Action> allowedActions;

    public Behaviour(Texture texture, String type) {
        this.texture = texture;
        setWidth(texture.getWidth() * 2);
        setHeight(texture.getHeight() * 2);
        this.type = type;
        this.allowedActions = new ArrayList<>();
    }

    public abstract int getAC();

    public abstract void damage(int Damage);

    public abstract int getSpeed();

    public void setOriginalLocation(Location originalLocation) {
        this.originalLocation = originalLocation;
    }

    public Location getOriginalLocation() {
        return originalLocation;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public Environment getEnvironment() {
        return environment;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(), getHeight());
    }

    public List<Action> getAllowedActions() {
        return allowedActions;
    }

    public abstract void saveBehaviourInfo();
}
