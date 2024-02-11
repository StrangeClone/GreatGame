package com.greatgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.greatgame.skills.SkillInitializer;

public class GreatGame extends ApplicationAdapter {
	SpriteBatch batch;
	
	@Override
	public void create () {
		SkillInitializer.initializeSkills();
		batch = new SpriteBatch();
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
