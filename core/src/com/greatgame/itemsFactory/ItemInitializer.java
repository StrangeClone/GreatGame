package com.greatgame.itemsFactory;

import com.badlogic.gdx.graphics.Texture;
import com.greatgame.entities.ItemSlot;

import static com.greatgame.behaviour.ItemBehaviour.itemsFactory;
import static com.greatgame.factory.BehaviourRefiner.behaviourRefiner;

public class ItemInitializer {
    public static void initializeItems() {
        itemsFactory.addPattern(new ItemPattern("tree", new Texture("textures/items/tree.png"), 50, 10, 0, true));
        behaviourRefiner.addPattern(new ItemRefiningPattern("tree", true));

        itemsFactory.addPattern(new ItemPattern("big rock", new Texture("textures/items/big rock.png"), 100, 10, 0, true));
        behaviourRefiner.addPattern(new ItemRefiningPattern("big rock", true));

        itemsFactory.addPattern(new ItemPattern("little rock", new Texture("textures/items/little rock.png"), 25, 12, 2, false).
                modify(new EnableCollectModifier()));
        behaviourRefiner.addPattern(new ItemRefiningPattern("little rock", true));

        itemsFactory.addPattern(new ItemPattern("bush", new Texture("textures/items/bush.png"), 25, 10, 0, true));
        behaviourRefiner.addPattern(new ItemRefiningPattern("bush", true));

        itemsFactory.addPattern(new ItemPattern("little bush", new Texture("textures/items/little bush.png"), 15, 12, 0, false));
        behaviourRefiner.addPattern(new ItemRefiningPattern("little bush", true));

        itemsFactory.addPattern(new ItemPattern("flower", new Texture("textures/items/flower.png"), 1, 12, 1, false).
                modify(new EnableCollectModifier()));
        behaviourRefiner.addPattern(new ItemRefiningPattern("flower", true));

        itemsFactory.addPattern(new ItemPattern("helm", new Texture("textures/items/helm.png"), 25, 12, 10, false).
                modify(new ArmorModifier(ItemSlot.Head, 2)).
                modify(new EnableCollectModifier()));
        behaviourRefiner.addPattern(new ItemRefiningPattern("helm", true));

        itemsFactory.addPattern(new ItemPattern("leather armor", new Texture("textures/items/leather armor.png"), 25, 12, 10, false).
                modify(new ArmorModifier(ItemSlot.Chest, 2)).
                modify(new EnableCollectModifier()));
        behaviourRefiner.addPattern(new ItemRefiningPattern("leather armor", true));

        itemsFactory.addPattern(new ItemPattern("chain mail", new Texture("textures/items/chain mail.png"), 40, 12, 50, false).
                modify(new ArmorModifier(ItemSlot.Chest, 4)).
                modify(new EnableCollectModifier()));
        behaviourRefiner.addPattern(new ItemRefiningPattern("chain mail", true));

        itemsFactory.addPattern(new ItemPattern("chest plate", new Texture("textures/items/chest plate.png"), 50, 12, 150, false).
                modify(new ArmorModifier(ItemSlot.Chest, 5)).
                modify(new EnableCollectModifier()));
        behaviourRefiner.addPattern(new ItemRefiningPattern("chest plate", true));

        itemsFactory.addPattern(new ItemPattern("short sword", new Texture("textures/items/short sword.png"), 40, 13, 30, false).
                modify(new WeaponModifier(6, 1.5f, "fencing")).
                modify(new EnableCollectModifier()));
        behaviourRefiner.addPattern(new ItemRefiningPattern("short sword", true));

        itemsFactory.addPattern(new ItemPattern("long sword", new Texture("textures/items/long sword.png"), 40, 13, 40, false).
                modify(new WeaponModifier(8, 1.5f, "fencing")).
                modify(new EnableCollectModifier()));
        behaviourRefiner.addPattern(new ItemRefiningPattern("long sword", true));

        itemsFactory.addPattern(new ItemPattern("dagger", new Texture("textures/items/dagger.png"), 30, 13, 25, false).
                modify(new WeaponModifier(4, 1.5f, "fencing")).
                modify(new EnableCollectModifier()));
        behaviourRefiner.addPattern(new ItemRefiningPattern("dagger", true));

        itemsFactory.addPattern(new ItemPattern("short bow", new Texture("textures/items/short bow.png"), 20, 13, 40, false).
                modify(new WeaponModifier(6, 30, "archery")).
                modify(new EnableCollectModifier()));
        behaviourRefiner.addPattern(new ItemRefiningPattern("short bow", true));

        itemsFactory.addPattern(new ItemPattern("long bow", new Texture("textures/items/long bow.png"), 20, 13, 50, false).
                modify(new WeaponModifier(8, 45, "archery")).
                modify(new EnableCollectModifier()));
        behaviourRefiner.addPattern(new ItemRefiningPattern("long bow", true));

        itemsFactory.addPattern(new ItemPattern("improvised weapon", null, 25, 10, 0, false).
                modify(new WeaponModifier(3, 1.5f, "unarmed fight")));

        itemsFactory.addPattern(new ItemPattern("house", new Texture("textures/items/house.png"), 1000, 15, 0, true));
        behaviourRefiner.addPattern(new ItemRefiningPattern("house", true));

        itemsFactory.addPattern(new ItemPattern("tent", new Texture("textures/items/tent.png"), 20, 8, 0, true));
        behaviourRefiner.addPattern(new ItemRefiningPattern("tent", true));

        itemsFactory.addPattern(new ItemPattern("fireplace", new Texture("textures/items/fireplace.png"), 20, 8, 0, true));
        behaviourRefiner.addPattern(new ItemRefiningPattern("fireplace", true));

        itemsFactory.addPattern(new ItemPattern("wooden leg", new Texture("textures/items/wooden leg.png"), 1000, 20, 170_000_000, true).
                modify(new EnableCollectModifier()));
        behaviourRefiner.addPattern(new ItemRefiningPattern("wooden leg", true));

        itemsFactory.addPattern(new ItemPattern("silver coins", new Texture("textures/items/silver coins.png"), 10, 10, 0, false).
                modify(new CoinsModifier()));
        behaviourRefiner.addPattern(new ItemRefiningPattern("silver coins", true));

        itemsFactory.addPattern(new ItemPattern("fur", new Texture("textures/items/fur.png"), 10, 10, 5, false).
                modify(new EnableCollectModifier()));
        behaviourRefiner.addPattern(new ItemRefiningPattern("fur", true));

        itemsFactory.addPattern(new ItemPattern("wolf fangs", new Texture("textures/items/fangs.png"), 10, 12, 3, false).
                modify(new WeaponModifier(4, 1.5f, "bite")).
                modify(new EnableCollectModifier()));
        behaviourRefiner.addPattern(new ItemRefiningPattern("wolf fangs", true));

        itemsFactory.addPattern(new ItemPattern("bear fangs", new Texture("textures/items/fangs.png"), 15, 12, 4, false).
                modify(new WeaponModifier(6, 1.5f, "bite")).
                modify(new EnableCollectModifier()));
        behaviourRefiner.addPattern(new ItemRefiningPattern("bear fangs", true));

        itemsFactory.addPattern(new ItemPattern("fox fangs", new Texture("textures/items/fangs.png"), 7, 12, 2, false).
                modify(new WeaponModifier(2, 1.5f, "bite")).
                modify(new EnableCollectModifier()));
        behaviourRefiner.addPattern(new ItemRefiningPattern("fox fangs", true));

        itemsFactory.addPattern(new ItemPattern("healing potion", new Texture("textures/items/healing potion.png"), 5, 10, 20, false).
                modify(new EnableCollectModifier()).
                modify(new PotionModifier()));
        behaviourRefiner.addPattern(new ItemRefiningPattern("healing fangs", true));
    }
}
