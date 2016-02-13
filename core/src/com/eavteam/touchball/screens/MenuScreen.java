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
import com.eavteam.touchball.common.Assets;

public class MenuScreen implements Screen{

    public final TouchBallGame game;
//    private TextButton buttonStart, buttonExit, buttonSettings;
//    private TextureAtlas atlas;
//    private Skin skin;
    private Stage stage;
    private Table table;

    public MenuScreen(final TouchBallGame game){
        this.game = game;
        ScreenViewport viewport = new ScreenViewport();
        stage = new Stage(viewport);

        table = new Table();
        table.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        Label.LabelStyle labelStyle = new Label.LabelStyle(Assets.font_48, Color.ORANGE);

        table.add(new Label(TouchBallGame.TITLE,labelStyle));
//        table.debug();
        stage.addActor(table);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
        stage.act();

        if(Gdx.input.justTouched()){
            ((Game)Gdx.app.getApplicationListener()).setScreen(new PlayScreen(game));
        }
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
