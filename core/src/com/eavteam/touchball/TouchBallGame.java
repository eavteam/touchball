package com.eavteam.touchball;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.eavteam.touchball.common.Assets;
import com.eavteam.touchball.common.MyProperties;
import com.eavteam.touchball.screens.DebugScreen;
import com.eavteam.touchball.screens.OpeningScreen;

public class TouchBallGame extends Game {

	public static final String TITLE = "Touch Ball";
	public static final String VERSION = "0.0001";
	public static MyProperties p;
	private SpriteBatch batch;
	private BitmapFont font;
	@Override
	public void create () {
		p.init();
		Assets.load();
		batch = new SpriteBatch();
		font = Assets.font_24;
		font.setColor(Color.RED);
		if(p.app.getProperty("debug").equals("true")){
			this.setScreen(new DebugScreen(this));
		} else
			this.setScreen(new OpeningScreen(this));
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void render () {
		super.render();

		if(Assets.manager.update()) {
			if(p.app.getProperty("debug").equals("true")) {
				batch.begin();
				this.font.draw(this.batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), Gdx.graphics.getWidth()-100, Gdx.graphics.getHeight()-20);
				batch.end();
			}
		}
	}

    @Override
    public void dispose() {
		Assets.dispose();
		super.dispose();
    }
}
