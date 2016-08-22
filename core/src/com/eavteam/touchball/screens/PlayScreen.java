package com.eavteam.touchball.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.eavteam.touchball.TouchBallGame;
import com.eavteam.touchball.actors.*;
import com.eavteam.touchball.common.Assets;
import com.eavteam.touchball.common.Settings;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;


public class PlayScreen implements Screen {

    public final TouchBallGame game;
    private Music backGroundMusic;
    private World world;
    private Stage stage;
    private Group group;
    private BackgroundActor background;
    private BallActor ball;
    private BlenderActor blender;
    private TargetActor target;
    private BallRoundActor round;
    private HardBox hardBox;

    private boolean inArea;
    private boolean trigger2;

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

        inArea = true;
        trigger2 = true;

        // World initialization
        world = new World(new Vector2(0f, 0f), true);
        world.setContinuousPhysics(true);
        debugRenderer = new Box2DDebugRenderer();

        // Actors initialization
        background = new BackgroundActor();
        ball = new BallActor(world);
        round = new BallRoundActor();
        hardBox = new HardBox(world);
        blender = new BlenderActor(world);
        target = new TargetActor();

        // Group form
//        group.addActor(background);
        group.addActor(target);
        group.addActor(round);
        group.addActor(blender);
        group.addActor(ball);
        group.addActor(hardBox);

        stage.addActor(group);

        //Music initialization
        backGroundMusic = Gdx.audio.newMusic(Gdx.files.internal(Settings.pathMusicFile));
        backGroundMusic.setVolume(Settings.volumeMusic);
    }

    @Override
    public void show() {
        backGroundMusic.play();
        round.addAction(Actions.fadeIn(2f, Interpolation.linear));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        // World update
        world.step(TIMESTEP, VELOSITYITERATIONS, POSITIONITERATIONS);
//        debugRenderer.render(world,stage.getViewport().getCamera().combined);
    }

    public void update() {
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
            ((Game)Gdx.app.getApplicationListener()).setScreen(new DebugScreen(game));

//TODO переделать нах это дерьмо
        if(!inArea) {
            if ((ball.getBody().getLinearVelocity().x == 0) && (ball.getBody().getLinearVelocity().y == 0)) {
                this.refresh();
            }
        }
        if(inArea) {
            if (!ball.getCircle().overlaps(round.getCircle())) {
                ball.stopListener();
                inArea = false;
            }
        }
        if(trigger2){
            if(ball.getCircle().overlaps(target.getCircle())){
                ball.targetHit(target.getCircle().x, target.getCircle().y);
                trigger2 = false;
                blender.disable();
            }
        }
    }

    private void refresh() {
        ball.refresh();
        target.refresh();
        blender.refresh();
//        round.refresh();
        inArea = true;
        trigger2 = true;
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        backGroundMusic.pause();
    }

    @Override
    public void resume() {
        backGroundMusic.play();
    }

    @Override
    public void hide() {
        pause();
    }

    @Override
    public void dispose() {
        stage.dispose();
        world.dispose();
    }
}
