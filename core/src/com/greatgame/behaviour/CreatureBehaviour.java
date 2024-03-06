package com.greatgame.behaviour;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.greatgame.application.Mode;
import com.greatgame.entities.Creature;
import com.greatgame.environment.Behaviour;
import com.greatgame.factory.Factory;

public class CreatureBehaviour extends Behaviour {
    public static Factory<CreatureBehaviour> creaturesFactory = new Factory<>();
    private final Creature creature;
    private boolean hostile;
    BehaviourState state;
    int EP = 0;

    public CreatureBehaviour(Texture texture, Creature creature) {
        super(texture, creature.getType());
        this.creature = creature;
    }

    public int getEP() {
        return EP;
    }

    public void increaseEP(int value) {
        EP += value;
    }

    public void decreaseEP(int value) {
        EP -= value;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        state.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        showHP(batch);
    }

    private void showHP(Batch batch) {
        TextureRegion heart = Mode.skin.getRegion("heart");
        for (int i = 0; i < creature.getHP(); i++) {
            batch.draw(heart, getX() - getWidth() / 2 + (i % 5) * heart.getRegionWidth(),
                    getY() + getHeight() / 2 + 5 + (float) (i / 5) * heart.getRegionHeight());
        }
    }

    @Override
    public int getAC() {
        return creature.getAC();
    }

    public boolean isHostile() {
        return hostile;
    }

    public void setHostile(boolean hostile) {
        this.hostile = hostile;
    }

    @Override
    public void damage(int damage) {
        int currentHP = creature.getHP();
        creature.setHP(currentHP - damage);
        if (creature.getHP() <= 0) {
            remove();
        }
        saveBehaviourInfo();
    }

    @Override
    public int getSpeed() {
        return creature.getSpeed();
    }

    @Override
    public void saveBehaviourInfo() {
        if(originalLocation != null) {
            originalLocation.addInfo(new CreatureBehaviourInfo(this));
        }
    }

    public Creature getCreature() {
        return creature;
    }

    public void setState(BehaviourState state) {
        this.state = state;
        state.behaviour = this;
    }

    public BehaviourState getState() {
        return state;
    }

    public void changeMode(Mode newMode) {
        state.changeMode(newMode);
    }
}
