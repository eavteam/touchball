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

    private Sprite roundSprite;
    private Circle circle;
    private float percent;
    private TweenManager tweenManager;

    public BallRoundActor(){

        roundSprite = new Sprite(Assets.manager.get(Assets.roundWhite,Texture.class));
        roundSprite.setColor(new Color(0.7f, 0.7f, 0.7f, 0.6f));

        circle = new Circle();
//        circle.radius = roundSprite.getHeight() / 2;

        setSize(20);
        setPosition((Gdx.graphics.getWidth() / 2) / Assets.PPM, (Gdx.graphics.getHeight() / 2) / Assets.PPM);

        tweenManager = new TweenManager();
        Tween.registerAccessor(Actor.class,new ActorAccessor());

        Tween.set(this,ActorAccessor.SIZE).target(70).start(tweenManager);
        Tween.to(this,ActorAccessor.SIZE,1).target(20).start(tweenManager);


    }
    @Override
    public void setPosition(float centerX, float centerY){
        super.setPosition(centerX - this.circle.radius,centerY - this.circle.radius);
        roundSprite.setPosition(centerX - this.circle.radius, centerY - this.circle.radius);
        circle.x = centerX;
        circle.y = centerY;
    }

    //размер задается в % от высоты дисплея
    public void setSize(float percent){
        this.percent = percent;
        this.setSize(Gdx.graphics.getHeight() * percent / 100 , Gdx.graphics.getHeight() * percent / 100 );
    }
    @Override
    public void setSize(float x,float y){
        x /= Assets.PPM;
        y /= Assets.PPM;
        super.setSize(x,y);
        roundSprite.setSize(x,y);
        circle.radius = roundSprite.getHeight() / 2;
        setPosition(circle.x, circle.y);
    }

    public float getSize(){ return percent;}

    public float getRadius(){
        return circle.radius;
    }

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
}
