package com.greatgame.itemsFactory;

import com.greatgame.items.ConcreteItem;
import com.greatgame.items.ConcreteWeapon;
import com.greatgame.items.WeaponManager;

public class WeaponModifier implements ItemPatternModifier {
    private final WeaponManager weaponManager;
    public WeaponModifier(int damage, float range, String skill) {
        weaponManager = new WeaponManager(new ConcreteWeapon(range, damage, skill));
    }

    @Override
    public void modify(ConcreteItem item) {
        item.setEquipManager(weaponManager);
    }
}
