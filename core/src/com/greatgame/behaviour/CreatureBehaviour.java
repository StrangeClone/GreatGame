package com.greatgame.behaviour;

import com.badlogic.gdx.graphics.Texture;
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

    @Override
    public void act(float delta) {
        super.act(delta);
        state.act(delta);
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
        saveBehaviourInfo();
    }

    @Override
    public int getSpeed() {
        return creature.getSpeed();
    }

    @Override
    public void saveBehaviourInfo() {
        originalLocation.addInfo(new CreatureBehaviourInfo(this));
    }

    public Creature getCreature() {
        return creature;
    }

    public void setState(BehaviourState state) {
        this.state = state;
        state.behaviour = this;
    }
}
