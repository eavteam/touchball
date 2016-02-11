package com.eavteam.touchball.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.eavteam.touchball.TouchBallGame;
import com.eavteam.touchball.actors.FontActor;

public class MenuScreen implements Screen{

//    private TextButton buttonStart, buttonExit, buttonSettings;
//    private TextureAtlas atlas;
//    private Skin skin;
    private Stage stage;
    private Table table;
    private FontActor fontActor;

    public MenuScreen(){

        ScreenViewport viewport = new ScreenViewport();
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        fontActor = new FontActor(Gdx.graphics.getHeight() * 10/100);

        table = new Table();
        table.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        Label.LabelStyle labelStyle = new Label.LabelStyle(fontActor.white, Color.ORANGE);

        table.add(new Label(TouchBallGame.TITLE,labelStyle));
        table.debug();
        stage.addActor(table);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
        stage.act();

        if(Gdx.input.isTouched()){
            ((Game) Gdx.app.getApplicationListener()).setScreen(new PlayScreen());
        }
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
        stage.dispose();
    }
}
