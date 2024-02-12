package com.greatgame.itemsFactory;

import com.greatgame.entities.ItemSlot;

import static com.greatgame.behaviour.ItemBehaviour.itemsFactory;

public class ItemInitializer {
    public static void initializeItems() {
        //TODO: add textures
        itemsFactory.addPattern(new ItemPattern("tree", null, 50, 10, 0, true));
        itemsFactory.addPattern(new ItemPattern("big rock", null, 100, 10, 0, true));
        itemsFactory.addPattern(new ItemPattern("little rock", null, 25, 12, 2, false).
                modify(new EnableCollectModifier()));
        itemsFactory.addPattern(new ItemPattern("bush", null, 25, 10, 0, true));
        itemsFactory.addPattern(new ItemPattern("little bush", null, 15, 12, 0, false));
        itemsFactory.addPattern(new ItemPattern("flower", null, 1, 12, 1, false).
                modify(new EnableCollectModifier()));
        itemsFactory.addPattern(new ItemPattern("helm", null, 25, 12, 10, false).
                modify(new ArmorModifier(ItemSlot.Head, 2)).
                modify(new EnableCollectModifier()));
        itemsFactory.addPattern(new ItemPattern("leather armor", null, 25, 12, 10, false).
                modify(new ArmorModifier(ItemSlot.Chest, 2)).
                modify(new EnableCollectModifier()));
        itemsFactory.addPattern(new ItemPattern("chain mail", null, 40, 12, 50, false).
                modify(new ArmorModifier(ItemSlot.Chest, 4)).
                modify(new EnableCollectModifier()));
        itemsFactory.addPattern(new ItemPattern("chest plate", null, 50, 12, 150, false).
                modify(new ArmorModifier(ItemSlot.Chest, 5)).
                modify(new EnableCollectModifier()));
        itemsFactory.addPattern(new ItemPattern("short sword", null, 40, 13, 30, false).
                modify(new WeaponModifier(6, 1f, "fencing")).
                modify(new EnableCollectModifier()));
        itemsFactory.addPattern(new ItemPattern("long sword", null, 40, 13, 40, false).
                modify(new WeaponModifier(8, 1f, "fencing")).
                modify(new EnableCollectModifier()));
        itemsFactory.addPattern(new ItemPattern("dagger", null, 30, 13, 25, false).
                modify(new WeaponModifier(4, 1, "fencing")).
                modify(new EnableCollectModifier()));
        itemsFactory.addPattern(new ItemPattern("short bow", null, 20, 13, 40, false).
                modify(new WeaponModifier(6, 30, "archery")).
                modify(new EnableCollectModifier()));
        itemsFactory.addPattern(new ItemPattern("long bow", null, 20, 13, 50, false).
                modify(new WeaponModifier(8, 45, "archery")).
                modify(new EnableCollectModifier()));
        itemsFactory.addPattern(new ItemPattern("improvised weapon", null, 25, 10, 0, false).
                modify(new WeaponModifier(3, 1, "unarmed fight")));
        itemsFactory.addPattern(new ItemPattern("house", null, 1000, 15, 0, true));
        itemsFactory.addPattern(new ItemPattern("tent", null, 20, 8, 0, true));
        itemsFactory.addPattern(new ItemPattern("fireplace", null, 20, 8, 0, true));
        itemsFactory.addPattern(new ItemPattern("garpez's leg", null, 1000, 20, 170_000_000, true).
                modify(new EnableCollectModifier()));
        itemsFactory.addPattern(new ItemPattern("silver coins", null, 10, 10, 0, false).
                modify(new CoinsModifier()));
        itemsFactory.addPattern(new ItemPattern("fur", null, 10, 10, 5, false).
                modify(new EnableCollectModifier()));
        itemsFactory.addPattern(new ItemPattern("wolf fangs", null, 10, 12, 3, false).
                modify(new WeaponModifier(4, 1, "bite")).
                modify(new EnableCollectModifier()));
        itemsFactory.addPattern(new ItemPattern("bear fangs", null, 15, 12, 4, false).
                modify(new WeaponModifier(6, 1, "bite")).
                modify(new EnableCollectModifier()));
        itemsFactory.addPattern(new ItemPattern("fox fangs", null, 7, 12, 2, false).
                modify(new WeaponModifier(2, 1, "bite")).
                modify(new EnableCollectModifier()));
        itemsFactory.addPattern(new ItemPattern("healing potion", null, 5, 10, 20, false).
                modify(new EnableCollectModifier()).
                modify(new PotionModifier()));
    }
}
