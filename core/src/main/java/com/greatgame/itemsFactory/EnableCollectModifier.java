package com.greatgame.itemsFactory;

import com.greatgame.items.ConcreteItem;
import com.greatgame.items.StandardCollectManager;

public class EnableCollectModifier implements ItemPatternModifier {
    @Override
    public void modify(ConcreteItem item) {
        item.setCollectManager(new StandardCollectManager());
    }
}
