package com.eavteam.touchball;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TouchBallGame extends Game {
	SpriteBatch batch;
	BackgroundActor background;
	BallActor ball;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		background = new BackgroundActor();
		background.setPosition(0, 0);

		ball = new BallActor();
		ball.setPosition(350, 200);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		background.draw(batch, 1);
		ball.draw(batch, 1);
		batch.end();
	}
}
