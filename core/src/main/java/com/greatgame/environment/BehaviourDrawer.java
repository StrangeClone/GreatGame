package com.greatgame.environment;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public interface BehaviourDrawer {
    void draw(Batch batch, Behaviour behaviour);
    Texture getTexture();
}
