package com.eavteam.touchball;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.eavteam.touchball.screens.MenuScreen;
import com.eavteam.touchball.screens.OpenningScreen;

public class TouchBallGame extends Game {

	public static final String TITLE = "Touch Ball";
	public static final String VERSION = "0.0001";

	@Override
	public void create () {
		this.setScreen(new OpenningScreen());
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
		}

    @Override
    public void dispose() {
        super.dispose();
    }
}
