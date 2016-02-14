package com.eavteam.touchball.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.eavteam.touchball.TouchBallGame;
import com.eavteam.touchball.tests.loading.screens.*;
import com.eavteam.touchball.tests.loading.screens.LoadingScreen;

public class DebugScreen implements Screen {

    public final TouchBallGame game;

    private Stage stage;
    private Table container, table;
    private Skin skin;

    public DebugScreen( final TouchBallGame game){
        this.game = game;
        ScreenViewport viewport = new ScreenViewport();
        this.stage = new Stage(viewport);
        this.skin = new Skin(Gdx.files.internal("skins/uiskin.json"));
        Gdx.input.setInputProcessor(this.stage);

        this.container = new Table();
        this.stage.addActor(container);
        this.container.setFillParent(true);
        this.container.row();

        this.table = new Table();
        buildTable();

        final ScrollPane scroll = new ScrollPane(this.table, this.skin);
        this.container.add(scroll).expand().fill().colspan(4);
        this.container.row().space(10).padBottom(10);
    }

    private void buildTable() {
        this.table.clear();
        this.table.top().pad(10).defaults().expandX().space(4);

        this.table.row();
        TextButton menuScreen = new TextButton("MenuScreen", this.skin);
        this.table.add(menuScreen).left();
        menuScreen.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MenuScreen(game));
            }
        });

        this.table.row();
        TextButton openingScreen = new TextButton("OpeningScreen", this.skin);
        this.table.add(openingScreen).left();
        openingScreen.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new OpeningScreen(game));
            }
        });

        this.table.row();
        TextButton fileManagerScreen = new TextButton("FileManagerScreen", this.skin);
        this.table.add(fileManagerScreen).left();
        fileManagerScreen.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new FileManager(game));
            }
        });

        this.table.row();
        TextButton playScreen = new TextButton("PlayScreen", this.skin);
        this.table.add(playScreen).left();
        playScreen.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new PlayScreen(game));
            }
        });

        this.table.row();
        TextButton loadingScreen = new TextButton("LoadingScreen", this.skin);
        this.table.add(loadingScreen).left();
        loadingScreen.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new LoadingScreen(game));
            }
        });
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);
        this. stage.draw();
    }

    public void update(float delta){
        this.stage.act(delta);
    }

    @Override
    public void resize(int width, int height) {
        this.stage.getViewport().update(width, height, true);
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
