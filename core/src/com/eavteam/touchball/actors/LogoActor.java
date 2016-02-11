package com.eavteam.touchball.actors;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.eavteam.touchball.screens.MenuScreen;
import com.eavteam.touchball.tween.SpriteAccessor;

public class LogoActor extends Actor {

    private Sprite logoSprite;
    private TweenManager tweenManager;

    public LogoActor(){

        logoSprite = new Sprite(new Texture(Gdx.files.internal("images/eav_logo.png")));
        logoSprite.setSize(logoSprite.getTexture().getWidth() * 20 / 100, logoSprite.getTexture().getHeight() * 20 / 100);
        logoSprite.setPosition(Gdx.graphics.getWidth()/2- logoSprite.getWidth()/2,Gdx.graphics.getHeight()/2- logoSprite.getHeight()/1.5f);

        setBounds(logoSprite.getX(),logoSprite.getY(),logoSprite.getWidth()*20/100,logoSprite.getRegionHeight()*20/100);

        setTouchable(Touchable.enabled);
        tweenManager = new TweenManager();
        Tween.registerAccessor(Sprite.class,new SpriteAccessor());
        Tween.set(logoSprite,SpriteAccessor.ALPHA).target(0).start(tweenManager);
        Tween.to(logoSprite,SpriteAccessor.ALPHA,2).target(1).repeatYoyo(1,2).setCallback(new TweenCallback() {
            @Override
            public void onEvent(int type, BaseTween<?> source) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MenuScreen());
            }
        })
                .start(tweenManager);
    }


    @Override
    public boolean remove() {
        logoSprite.getTexture().dispose();
        return super.remove();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        logoSprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        tweenManager.update(delta);
    }
}
