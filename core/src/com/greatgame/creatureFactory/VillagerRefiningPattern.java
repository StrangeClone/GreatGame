package com.greatgame.creatureFactory;

import com.greatgame.actions.TalkAction;
import com.greatgame.behaviour.CreatureBehaviour;
import com.greatgame.environment.Behaviour;
import com.greatgame.environment.RandomMap;
import com.greatgame.explorationBehaviourState.VillagerExplorationBehaviourState;
import com.greatgame.factory.BehaviourRefiningPattern;

import static com.greatgame.behaviour.ItemBehaviour.itemsFactory;

public class VillagerRefiningPattern extends BehaviourRefiningPattern {
    public VillagerRefiningPattern(String name) {
        super(name);
    }

    @Override
    public void refine(Behaviour behaviour) {
        CreatureBehaviour creatureBehaviour = (CreatureBehaviour)behaviour;

        CreaturePatternModifier weaponSmithModifier = new ItemGiver(itemsFactory.create("short_sword").getItem(),
                itemsFactory.create("short_sword").getItem(),
                itemsFactory.create("long_sword").getItem(),
                itemsFactory.create("dagger").getItem(),
                itemsFactory.create("dagger").getItem());
        CreaturePatternModifier armorerModifier = new ItemGiver(itemsFactory.create("chain_mail").getItem(),
                itemsFactory.create("helm").getItem(),
                itemsFactory.create("helm").getItem());
        CreaturePatternModifier hunterModifier = new ItemGiver(itemsFactory.create("long_bow").getItem(),
                itemsFactory.create("long_bow").getItem(),
                itemsFactory.create("short_bow").getItem(),
                itemsFactory.create("short_bow").getItem(),
                itemsFactory.create("leather_armor").getItem());
        CreaturePatternModifier healerModifier = new ItemGiver(itemsFactory.create("healing_potion").getItem(),
                itemsFactory.create("healing_potion").getItem(),
                itemsFactory.create("healing_potion").getItem());
        RandomMap<CreaturePatternModifier> randomModifier = new RandomMap<>(behaviour.getName().hashCode());
        randomModifier.setWeight(weaponSmithModifier, 1);
        randomModifier.setWeight(armorerModifier, 1);
        randomModifier.setWeight(hunterModifier, 1);
        randomModifier.setWeight(healerModifier, 1);
        randomModifier.generate().modify(creatureBehaviour.getCreature());
        creatureBehaviour.getCreature().setCoins(randomModifier.generate().hashCode() % 100);

        behaviour.allowAction(new TalkAction(behaviour.getEnvironment().getPlayer(), creatureBehaviour));
        creatureBehaviour.setState(new VillagerExplorationBehaviourState(creatureBehaviour, null));
    }
}
