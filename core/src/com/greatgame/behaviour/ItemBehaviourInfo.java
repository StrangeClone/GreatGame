package com.greatgame.behaviour;

import com.greatgame.environment.Behaviour;
import com.greatgame.environment.BehaviourInfo;

public class ItemBehaviourInfo extends BehaviourInfo {

    boolean collected;

    public ItemBehaviourInfo(ItemBehaviour itemBehaviour) {
        super(itemBehaviour.getName(), itemBehaviour.getItem().getHP());
        this.collected = itemBehaviour.getItem().hasBeenCollected();
    }

    @Override
    public boolean apply(Behaviour behaviour) {
        if(collected) {
            return false;
        } else {
            ((ItemBehaviour)behaviour).getItem().setHP(HP);
            return HP > 0;
        }
    }
}
