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

public class DebugScreen implements Screen {

    private Stage stage;
    private Table container, table;
    private Skin skin;

    public DebugScreen(){
        ScreenViewport viewport = new ScreenViewport();
        stage = new Stage(viewport);
        skin = new Skin(Gdx.files.internal("skins/uiskin.json"));
        Gdx.input.setInputProcessor(stage);

        container = new Table();
        stage.addActor(container);
        container.setFillParent(true);
        container.row();

        table = new Table();
        buildTable();

        final ScrollPane scroll = new ScrollPane(table, skin);
        container.add(scroll).expand().fill().colspan(4);
        container.row().space(10).padBottom(10);


    }

    private void buildTable() {
        table.clear();
        table.top().pad(10).defaults().expandX().space(4);

        table.row();
        TextButton menuScreen = new TextButton("MenuScreen", skin);
        table.add(menuScreen).left();
        menuScreen.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MenuScreen());
            }
        });

        table.row();
        TextButton openningScreen = new TextButton("OpenningScreen", skin);
        table.add(openningScreen).left();
        openningScreen.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new OpenningScreen());
            }
        });

        table.row();
        TextButton fileManagerScreen = new TextButton("FileManagerScreen", skin);
        table.add(fileManagerScreen).left();
        fileManagerScreen.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new FileManager());
            }
        });

        table.row();
        TextButton playScreen = new TextButton("PlayScreen", skin);
        table.add(playScreen).left();
        playScreen.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new PlayScreen());
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
        stage.act(Gdx.graphics.getDeltaTime());
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

    @Override
    public void dispose() {
        stage.dispose();
    }
}
