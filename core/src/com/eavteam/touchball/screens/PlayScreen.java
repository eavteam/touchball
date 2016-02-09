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
import com.eavteam.touchball.swipe.SwipeHandler;
import com.eavteam.touchball.swipe.mesh.SwipeTriStrip;

public class PlayScreen implements Screen{

    final TouchBallGame game;
    SpriteBatch batch;
    BackgroundActor background;
    BallActor ball;

    OrthographicCamera cam;
    SwipeHandler swipe;
    Texture tex;
    ShapeRenderer shapes;
    SwipeTriStrip tris;

    public PlayScreen(final TouchBallGame gam){
        game = gam;
        batch = new SpriteBatch();
        background = new BackgroundActor();
        background.setPosition(0, 0);

        ball = new BallActor();
        ball.setPosition((Gdx.graphics.getWidth() / 2) - (ball.getWidth() / 2), (Gdx.graphics.getHeight() / 2) - (ball.getHeight() / 2));
        ball.setAnimationColor(0.8f);

        tris = new SwipeTriStrip();
        swipe = new SwipeHandler(10);
        swipe.minDistance = 10;
        swipe.initialDistance = 10;
        tex = new Texture("images/gradient.png");
        tex.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        shapes = new ShapeRenderer();
        Gdx.input.setInputProcessor(swipe);
        cam = new OrthographicCamera();
        cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void show() {

    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.5f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cam.update();
        batch.setProjectionMatrix(cam.combined);

//        ball.animationColorUpdate(delta);

//        if(Gdx.input.setInputProcessor());
        batch.begin();
  //      background.draw(batch, 1);
        ball.draw(batch, 1);
        batch.end();

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        tex.bind();
        tris.thickness = 30f;
        tris.update(swipe.path(), swipe.getDissolve());
        tris.color = Color.RED;
        tris.draw(cam);
    }

    @Override
    public void resize(int width, int height) {
        cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
        shapes.dispose();
        tex.dispose();
    }
}
