package com.greatgame.environment;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class StaticDrawer implements BehaviourDrawer {
    Texture texture;

    public StaticDrawer(Texture texture) {
        this.texture = texture;
    }

    @Override
    public void draw(Batch batch, Behaviour behaviour) {
        batch.draw(texture,
            behaviour.getX() - behaviour.getWidth() / 2,
            behaviour.getY() - behaviour.getHeight() / 2,
            behaviour.getWidth(), behaviour.getHeight());
    }

    @Override
    public Texture getTexture() {
        return texture;
    }
}
