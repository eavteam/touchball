package com.eavteam.touchball.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.eavteam.touchball.TouchBallGame;
import com.eavteam.touchball.actors.LogoActor;

public class OpeningScreen implements Screen {

    public final TouchBallGame game;
    private Stage stage;
    private LogoActor logo;

    public OpeningScreen(final TouchBallGame game) {
        this.game = game;
        ScreenViewport viewport = new ScreenViewport();
        this.stage = new Stage(viewport);
        Gdx.input.setInputProcessor(this.stage);
        this.logo = new LogoActor();
        this.stage.addActor(logo);
    }

    @Override
    public void show() {
    }

    public void update(float delta){
        this.stage.act(delta);
        if(Gdx.input.justTouched()){
            ((Game)Gdx.app.getApplicationListener()).setScreen(new MenuScreen(game));
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        this.stage.draw();
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
        this.stage.dispose();
    }
}
