package com.eavteam.touchball.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.eavteam.touchball.actors.BackgroundActor;
import com.eavteam.touchball.actors.BallActor;
import com.eavteam.touchball.TouchBallGame;
import com.eavteam.touchball.actors.SwipeActor;
import com.eavteam.touchball.actors.BallRoundActor;
import com.eavteam.touchball.swipe.SwipeHandler;
import com.eavteam.touchball.swipe.mesh.SwipeTriStrip;

public class PlayScreen implements Screen{

    final TouchBallGame game;
    SpriteBatch batch;
    BackgroundActor background;
    BallActor ball;
    SwipeActor swiper;
    BallRoundActor round;

    OrthographicCamera camera;

    public PlayScreen(final TouchBallGame gam){
        game = gam;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 480, 800);

        batch = new SpriteBatch();
        background = new BackgroundActor();
        ball = new BallActor();
        ball.setSize(4);
        round = new BallRoundActor();
        round.setSize(20);
        swiper = new SwipeActor();
    }

    @Override
    public void show() {

    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.5f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        batch.begin();
//        background.draw(batch, 1);
        ball.draw(batch, 1);
        round.draw(batch, 1);
        batch.end();

        swiper.update(camera);
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
        round.dispose();
        background.dispose();
        swiper.dispose();
    }
}