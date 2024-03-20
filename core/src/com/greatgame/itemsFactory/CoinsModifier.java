package com.greatgame.itemsFactory;

import com.greatgame.items.CoinsCollectManager;
import com.greatgame.items.ConcreteItem;

import java.util.Random;

public class CoinsModifier implements ItemPatternModifier {

    @Override
    public void modify(ConcreteItem item) {
        item.setCollectManager(new CoinsCollectManager(new Random().nextInt(1, 50)));
    }
}
