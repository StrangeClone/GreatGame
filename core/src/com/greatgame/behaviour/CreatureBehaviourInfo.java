package com.greatgame.behaviour;

import com.badlogic.gdx.math.Vector2;
import com.greatgame.entities.Creature;
import com.greatgame.entities.Item;
import com.greatgame.environment.Behaviour;
import com.greatgame.environment.BehaviourInfo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.greatgame.behaviour.ItemBehaviour.itemsFactory;

public class CreatureBehaviourInfo extends BehaviourInfo {
    Vector2 position;
    public final List<String> inventory;

    public CreatureBehaviourInfo(CreatureBehaviour behaviour) {
        super(behaviour.getName(), behaviour.getCreature().getHP());
        this.position = new Vector2(behaviour.getX(), behaviour.getY());
        this.inventory = behaviour.getCreature().getInventory().stream().
                map(Item::getType).collect(Collectors.toList());
    }

    @Override
    public void apply(List<Behaviour> locationContents) {
        Optional<Behaviour> b = getCorrespondentBehaviour(locationContents);
        b.ifPresent((behaviour) -> {
            CreatureBehaviour creatureBehaviour = (CreatureBehaviour) behaviour;
            creatureBehaviour.getCreature().setHP(HP);
            creatureBehaviour.setPosition(position.x, position.y);
            fixInventory(creatureBehaviour.getCreature());
        });
    }

    private void fixInventory(Creature c) {
        List<String> itemsThatAreThere = c.getInventory().stream().
                map(Item::getType).
                collect(Collectors.toList());
        List<String> itemsThatShouldBeThere = new ArrayList<>(inventory);
        for(Iterator<String> typeIterator = itemsThatAreThere.iterator(); typeIterator.hasNext();) {
            String current = typeIterator.next();
            if(itemsThatShouldBeThere.contains(current)) {
                itemsThatShouldBeThere.remove(current);
                typeIterator.remove();
            }
        }
        for(String itemNotPresentType : itemsThatShouldBeThere) {
            c.getInventory().add(itemsFactory.create(itemNotPresentType).getItem());
        }
        for(String itemThatShouldNotBeThere : itemsThatAreThere) {
            for(Item item : c.getInventory()) {
                if(item.getType().equals(itemThatShouldNotBeThere)) {
                    c.getInventory().remove(item);
                    break;
                }
            }
        }
    }
}