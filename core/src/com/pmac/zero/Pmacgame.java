package com.pmac.zero;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Pmacgame extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	public boolean backpressed = false;

	@Override
	public void create () {
        batch = new SpriteBatch();
        Assets.load();
        Assets.manager.finishLoading();
        font = Assets.manager.get(Assets.font, BitmapFont.class);
		this.setScreen(new Zero(this));


	}


	@Override
	public void render () {
		super.render();

	}

	@Override
	public void dispose() {
		Assets.dispose();
		batch.dispose();
	}


}
