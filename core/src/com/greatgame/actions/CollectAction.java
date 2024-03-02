package com.greatgame.actions;

import com.greatgame.behaviour.ItemBehaviour;
import com.greatgame.entities.Creature;
import com.greatgame.environment.Action;
import com.greatgame.environment.Environment;

import static com.greatgame.explorationBehaviourState.ExplorationBehaviourState.PIXELS_PER_METER;

public class CollectAction extends Action {
    ItemBehaviour behaviour;
    public CollectAction(ItemBehaviour behaviour) {
        super("Collect");
        this.behaviour = behaviour;
    }

    @Override
    public boolean validate() {
        Environment environment = behaviour.getEnvironment();
        return environment.dist(environment.getPlayer().getPosition(), behaviour.getPosition()) < PIXELS_PER_METER;
    }

    @Override
    public void start(long durationInMillis) {
        super.start(durationInMillis);
        Creature player = behaviour.getEnvironment().getPlayer().getCreature();
        behaviour.getItem().collect(player);
        behaviour.remove();
    }

    @Override
    public void update(float delta) {}
}
