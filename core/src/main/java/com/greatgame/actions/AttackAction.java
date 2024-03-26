package com.greatgame.actions;

import com.greatgame.behaviour.CreatureBehaviour;
import com.greatgame.entities.Weapon;
import com.greatgame.environment.Action;
import com.greatgame.environment.Behaviour;

public class AttackAction extends Action {
    private final CreatureBehaviour attacker;
    private final Behaviour target;
    private final AttackValidator validator;
    private long endTime;
    public AttackAction(CreatureBehaviour attacker, Behaviour target) {
        super("Attack");
        this.attacker = attacker;
        this.target = target;
        this.validator = new AttackValidator(attacker, target);
    }

    @Override
    public boolean finished() {
        if (System.currentTimeMillis() > endTime) {
            Weapon weapon = validator.getAttackWeapon();
            int check = attacker.getCreature().check(weapon.getSkill());
            if(check >= target.getAC()) {
                target.damage(weapon.damage());
                target.showText("Hit");
            } else {
                target.showText("Missed");
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean validate() {
        return validator.validate(attacker.getEnvironment());
    }

    @Override
    public void start() {
        endTime = System.currentTimeMillis() + 1000;
    }

    @Override
    public void update(float delta) {

    }
}
