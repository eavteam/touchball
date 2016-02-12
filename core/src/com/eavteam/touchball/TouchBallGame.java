package com.eavteam.touchball;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.eavteam.touchball.screens.FileManager;
import com.eavteam.touchball.screens.OpenningScreen;
import com.eavteam.touchball.screens.PlayScreen;
import com.eavteam.touchball.tests.drag.DragAndDropTest;

public class TouchBallGame extends Game {

	public static final String TITLE = "Touch Ball";
	public static final String VERSION = "0.0001";
	public static MyProperties p;
	BitmapFont font;
	SpriteBatch batch;
	@Override
	public void create () {
		this.setScreen(new FileManager());
		p.init();
//		this.setScreen(new OpenningScreen());
		font = new BitmapFont(); batch = new SpriteBatch();
		font.setColor(Color.RED);

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

		if(p.app.getProperty("debug").equals("true")) {
			batch.begin();
			this.font.draw(this.batch, "FPS Bitch: " + Gdx.graphics.getFramesPerSecond(), 0, Gdx.graphics.getHeight()-20);
			batch.end();
		}

	}

    @Override
    public void dispose() {
        this.getScreen().dispose();
		super.dispose();
    }
}
