package com.greatgame.itemsFactory;

import com.greatgame.items.ConcreteItem;
import com.greatgame.items.Weapon;

public class WeaponModifier implements ItemPatternModifier {
    private final Weapon weapon;
    public WeaponModifier(int damage, float range, String skill) {
        weapon = new Weapon(damage, range, skill);
    }

    @Override
    public void modify(ConcreteItem item) {
        item.setEquipManager(weapon);
    }
}
