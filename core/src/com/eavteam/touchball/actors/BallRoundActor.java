package com.eavteam.touchball.actors;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.eavteam.touchball.common.Assets;
import com.eavteam.touchball.tween.ActorAccessor;

public class BallRoundActor extends Actor {
    public static final float size = 20;
    private Sprite roundSprite;
    private Circle circle;
    private float sizeForTween;
    private TweenManager tweenManager;

    public BallRoundActor(){

        roundSprite = new Sprite(Assets.manager.get(Assets.roundWhite,Texture.class));
        roundSprite.setColor(new Color(0.5f, 0.5f, 0.5f, 0.6f));

        circle = new Circle();

        setSize(size);
        setPosition((Gdx.graphics.getWidth() / 2) / Assets.PPM, (Gdx.graphics.getHeight() / 2) / Assets.PPM);

        tweenManager = new TweenManager();
        Tween.registerAccessor(Actor.class,new ActorAccessor());

        Tween.set(this,ActorAccessor.BALLROUNDSIZE).target(70).start(tweenManager);
        Tween.to(this,ActorAccessor.BALLROUNDSIZE,1).target(size).start(tweenManager);
    }
    @Override
    public void setPosition(float centerX, float centerY){
        super.setPosition(centerX - this.circle.radius,centerY - this.circle.radius);
        roundSprite.setPosition(centerX - this.circle.radius, centerY - this.circle.radius);
        circle.x = centerX;
        circle.y = centerY;
    }

    //размер задается в % от высоты дисплея
    public void setSize(float sizeInPercent){
        this.sizeForTween = sizeInPercent;
        this.setSize(Gdx.graphics.getHeight() * sizeInPercent / 100 , Gdx.graphics.getHeight() * sizeInPercent / 100 );
    }
    @Override
    public void setSize(float width,float height){
        width /= Assets.PPM;
        height /= Assets.PPM;
        super.setSize(width,height);
        roundSprite.setSize(width,height);
        circle.radius = roundSprite.getHeight() / 2;
        setPosition(circle.x, circle.y);
    }

    public float getSize(){ return sizeForTween;}

    public Circle getCircle(){return circle;}

    @Override
    public void draw(Batch batch, float alpha){
        roundSprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        tweenManager.update(delta);
    }

    @Override
    public boolean remove() {
        return super.remove();
    }

    public void refresh(){
        tweenManager.killAll();
        Tween.set(this,ActorAccessor.BALLROUNDSIZE).target(70).start(tweenManager);
        Tween.to(this,ActorAccessor.BALLROUNDSIZE,1).target(size).start(tweenManager);
    }
}
