package com.greatgame.application;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.greatgame.application.explorationMode.ExplorationMode;
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

		mode = new ExplorationMode(this, world.getEnvironment());

		InputMultiplexer multiProcessor = new InputMultiplexer(mode.stage, world.getEnvironment().getStage());
		Gdx.input.setInputProcessor(multiProcessor);
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
			}
		}
	}
}
