package com.eavteam.touchball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayScreen implements Screen{

    final TouchBallGame game;
    SpriteBatch batch;
    BackgroundActor background;
    BallActor ball;
    float rot = 0;

    public PlayScreen(final TouchBallGame gam){
        game = gam;
        batch = new SpriteBatch();
        background = new BackgroundActor();
        background.setPosition(0, 0);
        ball = new BallActor();
        ball.setPosition(240-64, 400 - 64);
        ball.setScale(0.5f, 0.5f);
    }

    @Override
    public void show() {

    }


    @Override
    public void render(float delta) {
        rot++;
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch, 1);
        background.rotationFuck(rot);
        ball.draw(batch, 1);
        batch.end();
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
        batch.dispose();
        ball.dispose();
        background.dispose();
    }
}
