package com.greatgame.application;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.greatgame.application.explorationMode.ExplorationMode;
import com.greatgame.application.fightMode.FightMode;
import com.greatgame.application.mainMenuMode.MainMenuMode;
import com.greatgame.application.tripMode.LocationIcon;
import com.greatgame.application.tripMode.TripMode;
import com.greatgame.behaviour.CreatureBehaviour;
import com.greatgame.creatureFactory.CreatureInitializer;
import com.greatgame.environment.ModeName;
import com.greatgame.itemsFactory.ItemInitializer;
import com.greatgame.skills.SkillInitializer;
import com.greatgame.world.World;

import java.util.Iterator;

public class GreatGame extends ApplicationAdapter {
    Mode mode;
    World world;
    private Color backgroundColor;

    @Override
    public void create() {
        SkillInitializer.initializeSkills();
        ItemInitializer.initializeItems();
        CreatureInitializer.initializeCreatures();
        LocationIcon.initializeIcons();

        Mode.skin = new Skin(Gdx.files.internal("skin/craftacular-ui.skin"));

        mode = new MainMenuMode(this, null, false);

        setBackgroundColor(Color.DARK_GRAY);

        updateInputMultiplexer();
    }

    @Override
    public void render() {
        ScreenUtils.clear(backgroundColor.r, backgroundColor.g, backgroundColor.b, backgroundColor.a);

        mode.update(Gdx.graphics.getDeltaTime());

        manageModeChange();
    }

    @Override
    public void resize(int width, int height) {
        mode.resize(width, height);
        if (world != null) {
            world.getEnvironment().getStage().getViewport().update(width, height, true);
        }
    }

    private void manageModeChange() {
        if (world != null && world.getEnvironment().getCurrentMode() != world.getEnvironment().getNextMode()) {
            setBackgroundColor(Color.GREEN);
            if (mode instanceof MainMenuMode && ((MainMenuMode) mode).getPreviousMode() != null) {
                mode = ((MainMenuMode) mode).getPreviousMode();
            } else if (world.getEnvironment().getNextMode() == ModeName.explorationMode) {
                mode = new ExplorationMode(this);
                updateCreatureBehaviourStates();
            } else if (world.getEnvironment().getNextMode() == ModeName.fightMode) {
                mode = new FightMode(this);
                updateCreatureBehaviourStates();
            } else if (world.getEnvironment().getNextMode() == ModeName.tripMode) {
                mode = new TripMode(this);
                updateCreatureBehaviourStates();
            } else if (world.getEnvironment().getNextMode() == ModeName.mainMenuMode) {
                setBackgroundColor(Color.DARK_GRAY);
                mode = new MainMenuMode(this, mode, true);
            }
            updateInputMultiplexer();

            world.getEnvironment().setCurrentMode(world.getEnvironment().getNextMode());
        }
    }

    private void updateCreatureBehaviourStates() {
        for (Iterator<Actor> iterator = world.getEnvironment().getStage().getActors().iterator(); iterator.hasNext(); ) {
            Actor actor = iterator.next();
            if (actor instanceof CreatureBehaviour) {
                ((CreatureBehaviour) actor).changeMode(mode);
            }
            if (world.getEnvironment().getNextMode() == ModeName.tripMode && actor != world.getEnvironment().getPlayer()) {
                iterator.remove();
            }
        }
    }

    public void updateInputMultiplexer() {
        InputMultiplexer multiProcessor = new InputMultiplexer();
        multiProcessor.addProcessor(mode.stage);
        if (world != null) {
            multiProcessor.addProcessor(world.getEnvironment().getStage());
        }
        Gdx.input.setInputProcessor(multiProcessor);
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
