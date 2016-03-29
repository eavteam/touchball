package com.eavteam.touchball.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.eavteam.touchball.TouchBallGame;
import com.eavteam.touchball.common.Settings;

public class FileManager implements Screen {

    public final TouchBallGame game;

    private Stage stage;
    private Table container, table;
    private Skin skin;
    private static String pathDirectory; // текущий каталог
    private static String musicFileName; // название текущего mp3 файла
    private String rootDirectory;
    private String sdCardDerectory;
    private Label pathLabelDirectory;
    private FileHandle[] mp3Files;
    private FileHandle[] allFiles;
    private TextButton backButton;

    public FileManager( final TouchBallGame game) {
        this.game = game;
        this.stage = new Stage();
        this.skin = new Skin(Gdx.files.internal("skins/uiskin.json"));
        Gdx.input.setInputProcessor(stage);

//         Gdx.graphics.setVSync(true);

        this.sdCardDerectory = Gdx.files.getExternalStoragePath();                   //получаем путь к SDCARD
        this.rootDirectory = "/";                                                    //получаем путь к ROOT
        this.pathDirectory = rootDirectory;                                          //по умолчанию текущий каталог выставляем в ROOT
        this.pathLabelDirectory = new Label("", this.skin);
        setPathLabelDirectory();
        this.mp3Files = Gdx.files.absolute(pathDirectory).list(".mp3");              // получаем данные mp3 файлов находящихся в текущем каталоге
        this.allFiles = Gdx.files.absolute(pathDirectory).list();                    // получаем данные всех файлов в текущем каталоге

        this.container = new Table();
        this.stage.addActor(container);
        this.container.setFillParent(true);
        this.container.row();
        this.container.add(pathLabelDirectory).left();
        this.container.row();

        this.table = new Table();
        buildTable();
        final ScrollPane scroll = new ScrollPane(this.table, this.skin);
        this.container.add(scroll).expand().fill().colspan(4);
        this.container.row().space(10).padBottom(10);

        InputListener stopTouchDown = new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                event.stop();
                return false;
            }
        };
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f,0.1f,0.1f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);
        this.stage.draw();
    }

    private void update(float delta) {
        this.stage.act(delta);
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
            ((Game)Gdx.app.getApplicationListener()).setScreen(new DebugScreen(this.game));
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

    public boolean needsGL20 () {
        return false;
    }

    private void updateDataTable(){
        this.mp3Files = Gdx.files.absolute(pathDirectory).list(".mp3"); // получаем данные mp3 файлов
        this.allFiles = Gdx.files.absolute(pathDirectory).list();// получаем данные всех файлов
        this.setPathLabelDirectory();
        this.buildTable();
    }

    private void buildTable(){

        this.table.clear();
//        table.debug();

        this.table.top().pad(10).defaults().expandX().space(4);
        this.table.row();
        this.backButton = new TextButton("...", this.skin);
        this.table.add(backButton).left();
        this.backButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                if(pathDirectory.length() != 1){
                    pathDirectory = pathDirectory.substring(0, pathDirectory.length() - 1); // отсекаем последний слэш
                    pathDirectory = pathDirectory.substring(0, pathDirectory.lastIndexOf("/") + 1); // отсекаем название текущей папки
                    updateDataTable();}
            }
        });
        for (final FileHandle file: allFiles) {
            if(file.isDirectory()){ //если папка, то добавляем в таблицу
                this.table.row();
                TextButton directoryButton = new TextButton(file.name(), this.skin);
                this.table.add(directoryButton).left();
                directoryButton.addListener(new ClickListener() {
                    public void clicked (InputEvent event, float x, float y) {
                        pathDirectory += file.name() + "/";
                        updateDataTable();
                    }
                });
            }
        }
        for (final FileHandle file: mp3Files) {
            this.table.row();
            TextButton mp3button = new TextButton(file.name(), this.skin);
            this.table.add(mp3button).left();
            mp3button.addListener(new ClickListener() {
                public void clicked (InputEvent event, float x, float y) {
                    ConfirmDialog dia = new ConfirmDialog("title",skin);
                    dia.show(stage);
                    musicFileName = new String(file.name());
                }
            });
        }
    }

    private void setPathLabelDirectory(){
        if(this.pathDirectory.length() > 40){
            this.pathLabelDirectory.setText("Directory: " + "/..." + pathDirectory.substring(pathDirectory.length() - 40));
        }else{
        this.pathLabelDirectory.setText("Directory: " + pathDirectory);}
    }

    private static class ConfirmDialog extends Dialog{

        public ConfirmDialog(String title, Skin skin) {
            super(title, skin);
        }
        {
            text("Are you sure?");
            button("Yes","Yes");
            button("No","No");
        }

        @Override
        protected void result(Object object) {
            if(((String)object).equals("Yes")) FileManager.loadMp3File();
        }
    }

    private static void loadMp3File(){
        Settings.pathMusicFile = pathDirectory + musicFileName;
    }
}
