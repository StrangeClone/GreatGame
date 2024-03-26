package com.greatgame.itemsFactory;

import com.greatgame.items.ConcreteItem;
import com.greatgame.items.PotionUseManager;

public class PotionModifier implements ItemPatternModifier {
    @Override
    public void modify(ConcreteItem item) {
        item.setUseManager(PotionUseManager.get());
    }
}
