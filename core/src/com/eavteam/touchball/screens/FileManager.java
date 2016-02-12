package com.eavteam.touchball.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by nepeyvoda-va on 11.02.2016.
 */
public class FileManager implements Screen {

    private Stage stage;
    private Table container, table;
    private Skin skin;
    private String pathDirectory;
    private String rootDirectory;
    private String sdCardDerectory;
    private Label pathLabelDirectory;
    private FileHandle[] mp3Files;
    private FileHandle[] allFiles;
    private TextButton backButton;

    public FileManager() {

        stage = new Stage();
        skin = new Skin(Gdx.files.internal("skins/uiskin.json"));
        Gdx.input.setInputProcessor(stage);

        // Gdx.graphics.setVSync(false);

        sdCardDerectory = Gdx.files.getExternalStoragePath();                   //получаем путь к SDCARD
        rootDirectory = "/";                                                    //получаем путь к ROOT
        pathDirectory = rootDirectory;                                          //по умолчанию текущий каталог выставляем в ROOT
        pathLabelDirectory = new Label("Directory: " + pathDirectory, skin);
        mp3Files = Gdx.files.absolute(pathDirectory).list(".mp3");              // получаем данные mp3 файлов находящихся в текущем каталоге
        allFiles = Gdx.files.absolute(pathDirectory).list();                    // получаем данные всех файлов в текущем каталоге

        container = new Table();
        stage.addActor(container);
        container.setFillParent(true);
        container.row();
        container.add(pathLabelDirectory).left();
        container.row();

        table = new Table();
        buildTable();
        final ScrollPane scroll = new ScrollPane(table, skin);
        container.add(scroll).expand().fill().colspan(4);
        container.row().space(10).padBottom(10);

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
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
//
//         Gdx.gl.glViewport(100, 100, width - 200, height - 200);
////         stage.setViewport(800, 600, false, 100, 100, width - 200, height - 200);
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

    public boolean needsGL20 () {
        return false;
    }

    private void updateDataTable(){
        mp3Files = Gdx.files.absolute(pathDirectory).list(".mp3"); // получаем данные mp3 файлов
        allFiles = Gdx.files.absolute(pathDirectory).list();// получаем данные всех файлов
        pathLabelDirectory.setText("Directory: " + pathDirectory);
        buildTable();
    }

    private void buildTable(){

        table.clear();
        table.debug();

        table.top().pad(10).defaults().expandX().space(4);
        table.row();
        backButton = new TextButton("...", skin);
        table.add(backButton).left();
        backButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                if(pathDirectory != rootDirectory){
                    int index = pathDirectory.lastIndexOf("/");
                    pathDirectory = pathDirectory.substring(0, index);
                    index = pathDirectory.lastIndexOf("/");
                    pathDirectory = pathDirectory.substring(0, index+1);
                    updateDataTable();}

                System.out.println("click at backButton");
                System.out.println("pathDirectory: " + pathDirectory.length());
            }
        });
        for (final FileHandle file: allFiles) {
            if(file.isDirectory()){ //если папка, то добавляем в таблицу
                table.row();
                TextButton directoryButton = new TextButton(file.name(), skin);
                table.add(directoryButton).left();
                directoryButton.addListener(new ClickListener() {
                    public void clicked (InputEvent event, float x, float y) {
                        pathDirectory += file.name() + "/";
                        updateDataTable();
                        System.out.println("click at directoryButton");
                    }
                });
            }
        }
        for (FileHandle file: mp3Files) {
            table.row();
            TextButton mp3button = new TextButton(file.name(), skin);
            table.add(mp3button).left();
            mp3button.addListener(new ClickListener() {
                public void clicked (InputEvent event, float x, float y) {
                    ConfirmDialog dia = new ConfirmDialog("title",skin);
                    dia.show(stage);
//                    loadmp3File();TODO
                    System.out.println("click at mp3button");
                }
            });
        }
    }

    public static class ConfirmDialog extends Dialog{

        public ConfirmDialog(String title, Skin skin) {
            super(title, skin);
        }
        {
            text("Vovik mudak?");
            button("Yes","soglasen");
            button("No","ne pitaisya meny obmanut'");
        }

        @Override
        protected void result(Object object) {
            System.out.println(object.toString());
        }
    }
}
