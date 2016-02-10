package com.eavteam.touchball.screens;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.eavteam.touchball.TouchBallGame;
import com.eavteam.touchball.tween.SpriteAccessor;

public class OpenningScreen implements Screen {

    private final TouchBallGame game;
    private OrthographicCamera camera;
    private TweenManager tweenManager;
    private Texture logo;
    private Sprite logoSprite;

    public OpenningScreen(TouchBallGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 480, 800);
        logo = new Texture(Gdx.files.internal("images/Vovik.png"));
        logoSprite = new Sprite(logo);
        logoSprite.setPosition(Gdx.graphics.getWidth()/2- logoSprite.getWidth()/2,Gdx.graphics.getHeight()/2- logoSprite.getHeight()/2);
    }

    @Override
    public void show() {
        tweenManager = new TweenManager();
        Tween.registerAccessor(Sprite.class,new SpriteAccessor());

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        tweenManager.update(delta);
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        logoSprite.draw(game.batch);
//        game.font.draw(game.batch, "TouchBall", ((Gdx.graphics.getWidth() / 2) - 2*game.font.getXHeight() ), Gdx.graphics.getHeight() - game.font.getLineHeight() );
//        game.font.draw(game.batch, "version for little Vovochka", (Gdx.graphics.getWidth() / 8), Gdx.graphics.getHeight() - 2 * game.font.getLineHeight());
        game.batch.end();

        if(Gdx.input.isTouched()){
            game.setScreen(new PlayScreen(game));
            dispose();
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
        logo.dispose();
    }
}
