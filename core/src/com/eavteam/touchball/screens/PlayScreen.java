package com.eavteam.touchball.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.eavteam.touchball.actors.BackgroundActor;
import com.eavteam.touchball.actors.BallActor;
import com.eavteam.touchball.actors.SwipeActor;
import com.eavteam.touchball.actors.BallRoundActor;

public class PlayScreen implements Screen{

    private Stage stage;

    BackgroundActor background;
    BallActor ball;
//    SwipeActor swiper;
    BallRoundActor round;

//    OrthographicCamera camera;

    public PlayScreen(){

        ScreenViewport viewport = new ScreenViewport();
        stage = new Stage(viewport);

        background = new BackgroundActor();
        ball = new BallActor();
        round = new BallRoundActor();
//        camera = new OrthographicCamera();
//        camera.setToOrtho(false,480,800);
//        swiper = new SwipeActor();

        stage.addActor(ball);
        stage.addActor(round);

//        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {

    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.5f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

//        camera.update();

        stage.act(delta);
        stage.draw();

//        swiper.update(camera);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
        stage.dispose();
    }
}
