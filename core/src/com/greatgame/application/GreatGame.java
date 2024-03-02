package com.greatgame.application;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.greatgame.application.explorationMode.ExplorationMode;
import com.greatgame.application.fightMode.FightMode;
import com.greatgame.behaviour.CreatureBehaviour;
import com.greatgame.creatureFactory.CreatureBehaviourModifier;
import com.greatgame.creatureFactory.CreatureInitializer;
import com.greatgame.creatureFactory.PlayerBehaviourModifier;
import com.greatgame.environment.ModeName;
import com.greatgame.itemsFactory.ItemInitializer;
import com.greatgame.skills.SkillInitializer;
import com.greatgame.world.ConcreteEnvironment;
import com.greatgame.world.World;

import static com.greatgame.behaviour.CreatureBehaviour.creaturesFactory;

public class GreatGame extends ApplicationAdapter {
	Mode mode;
	World world;
	InputMultiplexer multiProcessor;
	
	@Override
	public void create () {
		SkillInitializer.initializeSkills();
		ItemInitializer.initializeItems();
		CreatureInitializer.initializeCreatures();

		Mode.skin = new Skin(Gdx.files.internal("skin/craftacular-ui.skin"));


		world = new World(23, new ConcreteEnvironment());
		CreatureBehaviour player = creaturesFactory.create("player");
		world.getEnvironment().addBehaviour(player);
		CreatureBehaviourModifier playerModifier = PlayerBehaviourModifier.getInstance();
		playerModifier.modify(player);
		player.increaseEP(1000);

		world.getEnvironment().checkContents(0,0);

		CreatureBehaviour fox = creaturesFactory.create("fox");
		fox.setPosition(100, 100);
		world.getEnvironment().addBehaviour(fox);

		world.getEnvironment().triggerModeChange(ModeName.fightMode);
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
		mode.stage.getViewport().update(width, height, true);
		world.getEnvironment().getStage().getViewport().update(width, height, true);
	}

	private void manageModeChange() {
		if(world.getEnvironment().getCurrentMode() != world.getEnvironment().getNextMode()) {
			if(world.getEnvironment().getNextMode() == ModeName.explorationMode) {
				mode = new ExplorationMode(this, world.getEnvironment());
			} else if(world.getEnvironment().getNextMode() == ModeName.fightMode) {
				mode = new FightMode(this, world.getEnvironment());
			}
			multiProcessor = new InputMultiplexer();
			multiProcessor.addProcessor(mode.stage);
			multiProcessor.addProcessor(world.getEnvironment().getStage());
			Gdx.input.setInputProcessor(multiProcessor);

			for(Actor actor : world.getEnvironment().getStage().getActors()) {
				if(actor instanceof CreatureBehaviour) {
					((CreatureBehaviour)actor).changeMode(mode);
				}
			}
		}
	}
}
