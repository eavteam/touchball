package com.eavteam.touchball.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.eavteam.touchball.TouchBallGame;

public class OpenningScreen implements Screen {

    final TouchBallGame game;
    OrthographicCamera camera;

    public OpenningScreen(TouchBallGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 480, 800);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
