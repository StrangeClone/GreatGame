package com.greatgame.actions;

import com.greatgame.behaviour.CreatureBehaviour;
import com.greatgame.entities.Item;
import com.greatgame.entities.ItemSlot;
import com.greatgame.entities.Weapon;
import com.greatgame.environment.Behaviour;
import com.greatgame.environment.Environment;

import static com.greatgame.behaviour.ItemBehaviour.itemsFactory;
import static com.greatgame.explorationBehaviourState.ExplorationBehaviourState.PIXELS_PER_METER;

public class AttackValidator implements ActionValidator {
    private final CreatureBehaviour attacker;
    private final Behaviour target;
    private final Weapon attackWeapon;

    public AttackValidator(CreatureBehaviour attacker, Behaviour target) {
        this.attacker = attacker;
        this.target = target;
        Item primaryItem = attacker.getCreature().getItem(ItemSlot.Primary);
        if(primaryItem == null || primaryItem.toWeapon() == null
                || attacker.getCreature().getLevel(primaryItem.toWeapon().getSkill()) == 0) {
            attackWeapon = itemsFactory.create("improvised weapon").getItem().toWeapon();
        } else {
            attackWeapon = primaryItem.toWeapon();
        }
    }

    public Weapon getAttackWeapon() {
        return attackWeapon;
    }

    @Override
    public boolean validate(Environment environment) {
        return environment.freeView(attacker, target) &&
                environment.dist(attacker.getPosition(), target.getPosition()) <
                        attackWeapon.getRange() * PIXELS_PER_METER;
    }
}
