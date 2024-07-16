package com.greatgame.environment;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class TreeDrawer implements BehaviourDrawer {

    Texture log;
    Texture actualTree;

    public TreeDrawer(Texture log, Texture actualTree) {
        this.log = log;
        this.actualTree = actualTree;
    }

    @Override
    public void draw(Batch batch, Behaviour behaviour) {
        batch.draw(log,
            behaviour.getX() - behaviour.getWidth() / 2,
            behaviour.getY() - behaviour.getHeight() / 2,
            behaviour.getWidth(), behaviour.getHeight());
        float y = behaviour.getEnvironment().getPlayer().getY();
        float x = behaviour.getEnvironment().getPlayer().getX();
        if (y > behaviour.getY() && y < behaviour.getY() + actualTree.getHeight() * 2 &&
            x > behaviour.getX() - actualTree.getWidth() && x < behaviour.getX() + actualTree.getWidth()) {
            batch.setColor(1,1,1,0.5f);
        }
        batch.draw(actualTree,
            behaviour.getX() - (float)actualTree.getWidth(),
            behaviour.getY() - behaviour.getHeight() / 2,
            actualTree.getWidth() * 2, actualTree.getHeight() * 2);
        batch.setColor(1,1,1,1);
    }

    @Override
    public Texture getTexture() {
        return log;
    }
}
