package com.greatgame.actions;

import com.greatgame.behaviour.ItemBehaviour;
import com.greatgame.entities.Creature;
import com.greatgame.environment.Action;

import static com.greatgame.explorationBehaviourState.ExplorationBehaviourState.PIXELS_PER_METER;

public class CollectAction extends Action {
    ItemBehaviour behaviour;
    ActionValidator validator;
    long endTime;
    public CollectAction(ItemBehaviour behaviour) {
        super("Collect");
        this.behaviour = behaviour;
        this.validator = new NearToValidator(behaviour.getEnvironment().getPlayer(), behaviour, PIXELS_PER_METER);
    }

    @Override
    public boolean finished() {
        return System.currentTimeMillis() > endTime;
    }

    @Override
    public boolean validate() {
        return validator.validate(behaviour.getEnvironment());
    }

    @Override
    public void start() {
        Creature player = behaviour.getEnvironment().getPlayer().getCreature();
        int previousInventorySize = player.getInventory().size();
        behaviour.getItem().collect(player);
        behaviour.saveBehaviourInfo();
        if(player.getInventory().size() != previousInventorySize) {
            behaviour.remove();
        }
        endTime = System.currentTimeMillis() + 1000;
    }

    @Override
    public void update(float delta) {}
}
