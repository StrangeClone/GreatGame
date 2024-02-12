package com.greatgame.itemsFactory;

import com.greatgame.items.CoinsCollectManager;
import com.greatgame.items.ConcreteItem;

import static com.greatgame.environment.RandomMap.randomGenerator;

public class CoinsModifier implements ItemPatternModifier {

    @Override
    public void modify(ConcreteItem item) {
        item.setCollectManager(new CoinsCollectManager(randomGenerator.nextInt(1, 50)));
    }
}
