package com.eavteam.touchball.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.eavteam.touchball.TouchBallGame;
import com.eavteam.touchball.actors.BackgroundActor;
import com.eavteam.touchball.actors.BallActor;
import com.eavteam.touchball.actors.BallRoundActor;

public class PlayScreen implements Screen, GestureDetector.GestureListener {

    public final TouchBallGame game;
    private World world;
    private Stage stage;
    private Group group;
    private BackgroundActor background;
    private BallActor ball;
    private BallRoundActor round;
//------------------------------------
    private Box2DDebugRenderer debugRenderer;
    private final float TIMESTEP = 1/60f;
    private final int VELOSITYITERATIONS = 8, POSITIONITERATIONS = 3;
    private float velocityX, velocityY;
//-------------------------------------
    public PlayScreen(final TouchBallGame game){
        this.game = game;
        FillViewport viewport = new FillViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
//                (Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        group = new Group();

//        -----------------------------------------------
        world = new World(new Vector2(0, 0), true);
        debugRenderer = new Box2DDebugRenderer();
//        GestureDetector gd = new GestureDetector(this);
//        Gdx.input.setInputProcessor(gd);
//        -----------------------------------------------------

        background = new BackgroundActor();
        ball = new BallActor();
        ball.makeBody(world);

        round = new BallRoundActor();


        group.addActor(background);
        group.addActor(round);
        group.addActor(ball);

        stage.addActor(group);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f,0.1f,0.1f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);
        stage.draw();

//-----------------------------------------------------
        world.step(TIMESTEP, VELOSITYITERATIONS, POSITIONITERATIONS);
        debugRenderer.render(world,stage.getViewport().getCamera().combined);
//-------------------------------------------------------
    }

    public void update(float delta){
        this.stage.act(delta);
        this.ball.updateVelocity(this.velocityX, this.velocityY);

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


    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        this.velocityX = velocityX; this.velocityY = velocityY;
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }
}
