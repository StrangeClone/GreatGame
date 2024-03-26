package com.greatgame.environment;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import com.greatgame.application.Mode;

import java.util.ArrayList;
import java.util.List;

public abstract class Behaviour extends Actor {
    private final Texture texture;
    protected final String type;
    protected Location originalLocation;
    protected Environment environment;
    private final List<Action> allowedActions;
    private String text = "";
    private long textDisappearTime = 0;

    public Behaviour(Texture texture, String type) {
        this.texture = texture;
        if (texture != null) {
            setWidth(texture.getWidth() * 2);
            setHeight(texture.getHeight() * 2);
        }
        this.type = type;
        this.allowedActions = new ArrayList<>();
    }

    public String getType() {
        return type;
    }

    public abstract int getAC();

    public abstract void damage(int Damage);

    public abstract int getSpeed();

    public Vector2 getPosition() {
        return new Vector2(getX(), getY());
    }

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
        if (!text.isEmpty()) {
            float diff = textDisappearTime - System.currentTimeMillis();
            if (diff > 0) {
                Mode.skin.getFont("font").draw(batch, text, getX() - 50, getY(), 100, Align.center, false);
            } else {
                text = "";
            }
        }
    }

    public List<Action> getAllowedActions() {
        return allowedActions;
    }

    public void allowAction(Action action) {
        allowedActions.add(action);
    }

    public abstract void saveBehaviourInfo();

    public void showText(String text) {
        this.text = text;
        textDisappearTime = System.currentTimeMillis() + 1000;
    }
}
