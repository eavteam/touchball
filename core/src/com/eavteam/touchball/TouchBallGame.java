package com.eavteam.touchball;

import com.badlogic.gdx.Game;
import com.eavteam.touchball.screens.OpenFileManager;
import com.eavteam.touchball.screens.OpenningScreen;
import com.eavteam.touchball.tests.DragAndDropTest;

public class TouchBallGame extends Game {

	public static final String TITLE = "Touch Ball";
	public static final String VERSION = "0.0001";

	@Override
	public void create () {
		this.setScreen(new DragAndDropTest());
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
