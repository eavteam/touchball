package com.eavteam.touchball.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.eavteam.touchball.TouchBallGame;

public class MenuScreen implements Screen{

    final TouchBallGame game;
    OrthographicCamera camera;

    public MenuScreen(final TouchBallGame gam){
        game = gam;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 480, 800);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.font.draw(game.batch, "TouchBall", ((Gdx.graphics.getWidth() / 2) - 2*game.font.getXHeight() ), Gdx.graphics.getHeight() - game.font.getLineHeight() );
        game.font.draw(game.batch, "version for little jenechkas", (Gdx.graphics.getWidth() / 8), Gdx.graphics.getHeight() - 2 * game.font.getLineHeight());
        game.batch.end();

        if(Gdx.input.isTouched()){
            game.setScreen(new PlayScreen(game));
            dispose();
        }
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
