package com.eavteam.touchball.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.eavteam.touchball.TouchBallGame;
import com.eavteam.touchball.actors.*;
import com.eavteam.touchball.common.Assets;


public class PlayScreen implements Screen {

    public final TouchBallGame game;
    private World world;
    private Stage stage;
    private Group group;
    private BackgroundActor background;
    private BallActor ball;
    private BallRoundActor round;
    private HardBox hardBox;
    private BlenderActor blenderActor;

    // World settings
    private Box2DDebugRenderer debugRenderer;
    private final float TIMESTEP = 1/60f;
    private final int VELOSITYITERATIONS = 8, POSITIONITERATIONS = 3;

    public PlayScreen(final TouchBallGame game){
        this.game = game;
        FillViewport viewport = new FillViewport(Gdx.graphics.getWidth()/Assets.PPM, Gdx.graphics.getHeight()/Assets.PPM);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        group = new Group();

        // World initialization
        world = new World(new Vector2(0f, 0f), true);
        world.setContinuousPhysics(true);
        debugRenderer = new Box2DDebugRenderer();

        // Actors initialization
        background = new BackgroundActor();
        ball = new BallActor();
        ball.makeBody(world);
        round = new BallRoundActor();
        hardBox = new HardBox();
        hardBox.makeBody(world);
        blenderActor = new BlenderActor();
        blenderActor.makeBody(world);

        // Group form
//        group.addActor(background);
        group.addActor(round);
        group.addActor(blenderActor);
        group.addActor(ball);
        group.addActor(hardBox);

        stage.addActor(group);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
//        Gdx.gl.glClearColor(Color.valueOf("07c15a").r, Color.valueOf("07c15a").g,Color.valueOf("07c15a").b, 1.0f);
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);
        stage.draw();

        // World update
        world.step(TIMESTEP, VELOSITYITERATIONS, POSITIONITERATIONS);
        debugRenderer.render(world,stage.getViewport().getCamera().combined);
    }

    public void update(float delta){
        this.stage.act(delta);
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
            ((Game)Gdx.app.getApplicationListener()).setScreen(new DebugScreen(game));
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
