package com.eavteam.touchball.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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
        generateMenu();
        stage.addActor(table);
    }

    public void update(float delta){
        this.stage.act(delta);
//        if(Gdx.input.justTouched()){
//            ((Game)Gdx.app.getApplicationListener()).setScreen(new PlayScreen(game));
//        }
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.25f, 0.25f, 0.25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);
        stage.draw();

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

    public void generateMenu(){
        table.debug();

        table.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        Label.LabelStyle labelStyleTitle = new Label.LabelStyle(Assets.font48, Color.ORANGE);
        Label.LabelStyle labelStyleButtons = new Label.LabelStyle(Assets.font32, Color.ORANGE);

        Label title = new Label(TouchBallGame.TITLE,labelStyleTitle);
        table.add(title);

        table.padTop(100);
        table.row();
        Label start = new Label("start",labelStyleButtons);
        table.add(start);

        table.row();
        Label settings = new Label("settings",labelStyleButtons);
        table.add(settings);

        table.row();
        Label exit = new Label("exit",labelStyleButtons);
        table.add(exit);
        exit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        stage.addActor(table);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
