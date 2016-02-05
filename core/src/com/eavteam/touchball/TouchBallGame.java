package com.eavteam.touchball;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TouchBallGame extends Game {
	SpriteBatch batch;
	BitmapFont font;

	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		this.setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
		}
}
