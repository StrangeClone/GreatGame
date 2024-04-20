package com.greatgame.behaviour;

import com.badlogic.gdx.math.Vector2;
import com.greatgame.entities.Creature;
import com.greatgame.entities.Item;
import com.greatgame.environment.Behaviour;
import com.greatgame.environment.BehaviourInfo;
import com.greatgame.fightBehaviourState.FightBehaviourState;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static com.greatgame.behaviour.ItemBehaviour.itemsFactory;

public class CreatureBehaviourInfo extends BehaviourInfo {
    Vector2 position;
    public final List<String> inventory;
    int coins;
    boolean active;

    public CreatureBehaviourInfo(CreatureBehaviour behaviour) {
        super(behaviour.getName(), behaviour.getCreature().getHP());
        this.position = new Vector2(behaviour.getX(), behaviour.getY());
        this.inventory = behaviour.getCreature().getInventory().stream().
                map(Item::getType).collect(Collectors.toList());
        this.coins = behaviour.getCreature().getCoins();
        if (behaviour.state instanceof FightBehaviourState fightBehaviourState) {
            this.active = fightBehaviourState.isActive();
        }
    }

    public CreatureBehaviourInfo(Scanner scanner) {
        super(scanner);
        position = new Vector2(scanner.nextFloat(), scanner.nextFloat());
        coins = scanner.nextInt();
        inventory = new ArrayList<>();
        String token;
        for (token = scanner.next(); !"false".equals(token) && !"true".equals(token); token = scanner.next()) {
            inventory.add(token);
        }
        active = Boolean.parseBoolean(token);
    }

    @Override
    public boolean apply(Behaviour behaviour) {
        CreatureBehaviour b = (CreatureBehaviour)behaviour;
        b.getCreature().setHP(HP);
        b.setPosition(position.x, position.y);
        b.getCreature().setCoins(coins);
        fixInventory(b.getCreature());
        if (active) {
            ((FightBehaviourState)b.state).activate();
        }
        return HP >= 0;
    }

    private void fixInventory(Creature c) {
        List<String> itemsThatAreThere = c.getInventory().stream().
            map(Item::getType).
            collect(Collectors.toCollection(ArrayList::new));
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

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Creature " + super.toString() + " " + position.x + " " + position.y + " " + coins + " ");
        for (String str : inventory) {
            result.append(str).append(" ");
        }
        result.append(active);
        return result.toString();
    }
}
