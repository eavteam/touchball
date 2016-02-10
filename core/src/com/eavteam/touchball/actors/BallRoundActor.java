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
import com.eavteam.touchball.tween.ActorAccessor;

/*
 Женечка просто дура, с выдвинутой без особой на то причины вздрыжневой генетальной пяней.
  */
public class BallRoundActor extends Actor {
    private Texture roundTexture;
    private Sprite roundSprite;
    private Circle circle;
    private float percent;
    private TweenManager tweenManager;

    public BallRoundActor(){
        roundTexture = new Texture("images/round_white.png");
        roundSprite = new Sprite(roundTexture);
       // roundSprite.setSize(Gdx.graphics.getHeight() * 20 / 100, Gdx.graphics.getHeight() * 20 / 100); //20% высоты дисплея
        roundSprite.setColor(new Color(0.7f, 0.7f, 0.7f, 0.6f));
        circle = new Circle();
        circle.radius = roundSprite.getHeight() / 2;

        setSize(20f);
        setPosition((Gdx.graphics.getWidth() / 2), (Gdx.graphics.getHeight() / 2));
        tweenManager = new TweenManager();
    }

    public void setPosition(float centerX, float centerY){
        this.roundSprite.setPosition(centerX - this.circle.radius, centerY - this.circle.radius);
        this.circle.x = centerX;
        this.circle.y = centerY;
    }

    //размер задается в % от высоты дисплея
    public void setSize(float percent){
        this.percent = percent;
        this.roundSprite.setSize(Gdx.graphics.getHeight() * percent / 100, Gdx.graphics.getHeight() * percent / 100);
        this.circle.radius = roundSprite.getHeight() / 2;
        this.setPosition(this.circle.x, this.circle.y);
    }

    public float getSize(){ return this.percent;}

    public float getRadius(){
        return this.circle.radius;
    }

    public Circle getCircle(){return this.circle;}

    @Override
    public void draw(Batch batch, float alpha){
        this.roundSprite.draw(batch);
    }

    public void show(){
        Tween.registerAccessor(Actor.class,new ActorAccessor());

        Tween.set(this,ActorAccessor.SIZE).target(70).start(tweenManager);
        Tween.to(this,ActorAccessor.SIZE,1).target(20).start(tweenManager);
    }

    public void update(float delta){
        tweenManager.update(delta);
    }

    // TODO
    public void dispose() {
        this.roundTexture.dispose();
    }

}
