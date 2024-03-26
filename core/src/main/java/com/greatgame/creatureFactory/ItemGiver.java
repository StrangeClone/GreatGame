package com.greatgame.creatureFactory;

import com.greatgame.entities.Creature;
import com.greatgame.entities.Item;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ItemGiver implements CreaturePatternModifier {
    List<Item> items;
    public ItemGiver(Item... items) {
        this.items = Arrays.stream(items).collect(Collectors.toList());
    }
    @Override
    public void modify(Creature creature) {
        List<Item> inventory = creature.getInventory();
        for(Item item : items) {
            if(inventory.size() < 8) {
                inventory.add(item);
            }
        }
    }
}
