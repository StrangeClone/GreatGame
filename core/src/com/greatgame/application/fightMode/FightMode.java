package com.greatgame.application.fightMode;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.greatgame.application.GreatGame;
import com.greatgame.application.Mode;
import com.greatgame.behaviour.CreatureBehaviour;
import com.greatgame.entities.Characteristic;
import com.greatgame.environment.ModeName;
import com.greatgame.fightBehaviourState.FightBehaviourState;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FightMode extends Mode {

    List<CreatureBehaviour> fighters;

    public FightMode(GreatGame application) {
        super();
        this.app = application;
        fighters = new ArrayList<>();

        handleFighters();

        addPlayerUI();
    }

    private void handleFighters() {
        for (Actor a : getEnvironment().getStage().getActors()) {
            if (a instanceof CreatureBehaviour) {
                fighters.add((CreatureBehaviour) a);
            }
        }
        fighters.sort(Comparator.comparingInt(o -> -o.getCreature().getCharacteristicBonus(Characteristic.Agility)));
    }

    @Override
    public void update(float delta) {
        getEnvironment().update(delta);
        super.update(delta);
        updateActiveFighter();
        checkDead();
    }

    private void updateActiveFighter() {
        boolean someoneIsActive = false;
        for (int i = 0; i < fighters.size(); i++) {
            CreatureBehaviour fighter = fighters.get(i);
            FightBehaviourState fighterState = (FightBehaviourState) fighter.getState();
            if (fighterState.isActive()) {
                someoneIsActive = true;
                if (fighterState.isDone()) {
                    fighterState.deactivate();
                    int nextIndex = (i + 1) % fighters.size();
                    FightBehaviourState nextFighterState = (FightBehaviourState) fighters.get(nextIndex).getState();
                    nextFighterState.activate();
                }
                break;
            }
        }

        if(!someoneIsActive) {
            FightBehaviourState firstFighterState = (FightBehaviourState) fighters.get(0).getState();
            firstFighterState.activate();
        }
    }

    private void checkDead() {
        fighters.removeIf(creatureBehaviour -> creatureBehaviour.getCreature().getHP() <= 0);
        if (fighters.size() == 1) {
            getEnvironment().triggerModeChange(ModeName.explorationMode);
        }
    }
}
