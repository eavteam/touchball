package com.eavteam.touchball.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.eavteam.touchball.TouchBallGame;
import com.eavteam.touchball.actors.BackgroundActor;
import com.eavteam.touchball.actors.BallActor;
import com.eavteam.touchball.actors.BallRoundActor;

public class PlayScreen implements Screen {

    public final TouchBallGame game;
    private World world;
    private Stage stage;
    private Group group;
    private BackgroundActor background;
    private BallActor ball;
    private BallRoundActor round;
//------------------------------------
    private final float TIMESTEP = 1/60f;
    private final int VELOSITYITERATIONS = 8, POSITIONITERATIONS = 3;
//-------------------------------------
    public PlayScreen(final TouchBallGame game){
        this.game = game;
        FillViewport viewport = new FillViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
//                (Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        group = new Group();

        background = new BackgroundActor();
        ball = new BallActor();
        round = new BallRoundActor();

        group.addActor(background);
        group.addActor(round);
        group.addActor(ball);

        stage.addActor(group);
    }

    @Override
    public void show() {
//        -----------------------------------------------
        world = new World(new Vector2(0, -9.8f), true);
        world.createBody(ball.getBodyDef());//.createFixture(ball.getFixtureDef());
//        -----------------------------------------------------
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f,0.1f,0.1f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//-----------------------------------------------------
        world.step(TIMESTEP, VELOSITYITERATIONS, POSITIONITERATIONS);
//-------------------------------------------------------
        update(delta);
        stage.draw();
    }

    public void update(float delta){
        this.stage.act(delta);

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
        world.dispose();
    }


}
