package com.eavteam.touchball.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.eavteam.touchball.actors.BackgroundActor;
import com.eavteam.touchball.actors.BallActor;
import com.eavteam.touchball.TouchBallGame;

public class PlayScreen implements Screen{

    final TouchBallGame game;
    SpriteBatch batch;
    BackgroundActor background;
    BallActor ball;

    public PlayScreen(final TouchBallGame gam){
        game = gam;
        batch = new SpriteBatch();
        background = new BackgroundActor();
        background.setPosition(0, 0);
        ball = new BallActor();
        ball.setPosition((Gdx.graphics.getWidth() / 2) - (ball.getWidth() / 2), (Gdx.graphics.getHeight() / 2) - (ball.getHeight() / 2));
        ball.setAnimationColor(0.8f);
    }

    @Override
    public void show() {

    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.7f, 0.5f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        ball.animationColorUpdate(delta);
        batch.begin();
  //      background.draw(batch, 1);
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
