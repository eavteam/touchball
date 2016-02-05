package com.eavteam.touchball;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TouchBall extends Game {
	SpriteBatch batch;
	Texture img;
	BackgroundActor background;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("Ball.png");
		background = new BackgroundActor();
		background.setPosition(0, 0);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 350, 200);
		batch.end();
	}
}
