package com.greatgame.application;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.greatgame.application.explorationMode.ExplorationMode;
import com.greatgame.application.fightMode.FightMode;
import com.greatgame.application.tripMode.LocationIcon;
import com.greatgame.application.tripMode.TripMode;
import com.greatgame.behaviour.CreatureBehaviour;
import com.greatgame.creatureFactory.CreatureInitializer;
import com.greatgame.environment.ModeName;
import com.greatgame.itemsFactory.ItemInitializer;
import com.greatgame.skills.SkillInitializer;
import com.greatgame.world.ConcreteEnvironment;
import com.greatgame.world.World;

import java.util.Iterator;

import static com.greatgame.behaviour.CreatureBehaviour.creaturesFactory;

public class GreatGame extends ApplicationAdapter {
	Mode mode;
	World world;
	
	@Override
	public void create () {
		SkillInitializer.initializeSkills();
		ItemInitializer.initializeItems();
		CreatureInitializer.initializeCreatures();
		LocationIcon.initializeIcons();

		Mode.skin = new Skin(Gdx.files.internal("skin/craftacular-ui.skin"));

		world = new World(45, new ConcreteEnvironment());

		mode = new ExplorationMode(this);

		CreatureBehaviour player = creaturesFactory.create("player");
		world.getEnvironment().setPlayer(player, mode.getStage());
		player.increaseEP(1000);
		player.getCreature().setCoins(100);

		world.getEnvironment().checkContents(0,0);

		updateCreatureBehaviourStates();

		InputMultiplexer multiProcessor = new InputMultiplexer();
		multiProcessor.addProcessor(mode.stage);
		multiProcessor.addProcessor(world.getEnvironment().getStage());
		Gdx.input.setInputProcessor(multiProcessor);

		manageModeChange();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 1, 0, 1);

		mode.update(Gdx.graphics.getDeltaTime());

		manageModeChange();
	}

	@Override
	public void resize(int width, int height) {
		mode.resize(width, height);
		world.getEnvironment().getStage().getViewport().update(width, height, true);
	}

	private void manageModeChange() {
		if(world.getEnvironment().getCurrentMode() != world.getEnvironment().getNextMode()) {
			if(world.getEnvironment().getNextMode() == ModeName.explorationMode) {
				mode = new ExplorationMode(this);
			} else if(world.getEnvironment().getNextMode() == ModeName.fightMode) {
				mode = new FightMode(this);
			} else if (world.getEnvironment().getNextMode() == ModeName.tripMode) {
				mode = new TripMode(this);
			}
			InputMultiplexer multiProcessor = new InputMultiplexer();
			multiProcessor.addProcessor(mode.stage);
			multiProcessor.addProcessor(world.getEnvironment().getStage());
			Gdx.input.setInputProcessor(multiProcessor);

			updateCreatureBehaviourStates();
			world.getEnvironment().setCurrentMode(world.getEnvironment().getNextMode());
		}
	}

	private void updateCreatureBehaviourStates() {
		for(Iterator<Actor> iterator = world.getEnvironment().getStage().getActors().iterator(); iterator.hasNext();) {
			Actor actor = iterator.next();
			if(actor instanceof CreatureBehaviour) {
				((CreatureBehaviour)actor).changeMode(mode);
			} else if (world.getEnvironment().getNextMode() == ModeName.tripMode) {
				iterator.remove();
			}
		}
	}
}
