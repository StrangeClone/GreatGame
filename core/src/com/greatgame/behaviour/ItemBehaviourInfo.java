package com.greatgame.behaviour;

import com.greatgame.environment.Behaviour;
import com.greatgame.environment.BehaviourInfo;

import java.util.List;
import java.util.Optional;

public class ItemBehaviourInfo extends BehaviourInfo {

    boolean collected;

    public ItemBehaviourInfo(ItemBehaviour itemBehaviour) {
        super(itemBehaviour.getName(), itemBehaviour.getItem().getHP());
        this.collected = itemBehaviour.getItem().hasBeenCollected();
    }

    @Override
    public void apply(List<Behaviour> locationContents) {
        Optional<Behaviour> b = getCorrespondentBehaviour(locationContents);
        b.ifPresent((behaviour) -> {
            ItemBehaviour itemBehaviour = (ItemBehaviour) behaviour;
            if(collected) {
                locationContents.remove(behaviour);
            } else {
                itemBehaviour.getItem().setHP(HP);
            }
        });
    }
}
