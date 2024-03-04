package com.greatgame.itemsFactory;

import com.greatgame.actions.AttackAction;
import com.greatgame.actions.CollectAction;
import com.greatgame.behaviour.ItemBehaviour;
import com.greatgame.environment.Behaviour;
import com.greatgame.factory.BehaviourRefiningPattern;

public class ItemRefiningPattern extends BehaviourRefiningPattern {
    boolean collectable;
    public ItemRefiningPattern(String name, boolean collectable) {
        super(name);
        this.collectable = collectable;
    }

    @Override
    public void refine(Behaviour behaviour) {
        ItemBehaviour itemBehaviour = (ItemBehaviour) behaviour;
        if (collectable) {
            itemBehaviour.allowAction(new CollectAction(itemBehaviour));
        }
        itemBehaviour.allowAction(new AttackAction(behaviour.getEnvironment().getPlayer(), itemBehaviour));
    }
}
