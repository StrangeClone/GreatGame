package com.greatgame.behaviour;

import com.greatgame.environment.Behaviour;
import com.greatgame.environment.BehaviourInfo;

import java.util.Scanner;

public class ItemBehaviourInfo extends BehaviourInfo {

    boolean collected;

    public ItemBehaviourInfo(ItemBehaviour itemBehaviour) {
        super(itemBehaviour.getName(), itemBehaviour.getItem().getHP());
        this.collected = itemBehaviour.getItem().hasBeenCollected();
    }

    public ItemBehaviourInfo(Scanner scanner) {
        super(scanner);
        collected = scanner.nextBoolean();
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

    @Override
    public String toString() {
        return "Item " + super.toString() + " " + collected;
    }
}
