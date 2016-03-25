package com.eavteam.touchball.actors;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.eavteam.touchball.common.Assets;
import com.eavteam.touchball.tween.ActorAccessor;

public class TargetActor extends Actor {
    private Sprite targetSprite;
    private float angle;
    private float speedOfRotation;
    private Circle circle, circle2;
    private static final float size = 6;
    private float percent;
    private TweenManager tweenManager;

    public TargetActor(){
        circle = new Circle();
        circle2 = new Circle();
        circle2.setPosition((Gdx.graphics.getWidth() / 2) / Assets.PPM, (Gdx.graphics.getHeight() / 2) / Assets.PPM);
        circle2.radius = (Gdx.graphics.getHeight() * 4 * 10 / 200) / Assets.PPM;
        targetSprite = new Sprite(Assets.manager.get(Assets.target, Texture.class));
        targetSprite.setColor(Color.RED);
        setSize(size);
        setBounds(targetSprite.getX(),targetSprite.getY(),targetSprite.getWidth(),targetSprite.getHeight());
        refreshPosition();
        setSpeedOfRotation(300f);

        tweenManager = new TweenManager();
        Tween.registerAccessor(Actor.class,new ActorAccessor());
        Tween.set(this,ActorAccessor.TARGETSIZE).target(0).start(tweenManager);
        Tween.to(this,ActorAccessor.TARGETSIZE,1).target(size).start(tweenManager);
    }

    private void setSpeedOfRotation(float speedOfRotation){
        this.speedOfRotation = speedOfRotation;
    }

    public Circle getCircle(){
        return this.circle;
    }

    //размер задается в % от высоты дисплея
    public void setSize(float percent){
        this.percent = percent;
        this.setSize((Gdx.graphics.getHeight() * percent / 100) / Assets.PPM, (Gdx.graphics.getHeight() * percent / 100) / Assets.PPM);
    }

    public float getSize(){ return percent;}

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        targetSprite.setSize(width, height);
        targetSprite.setOriginCenter(); // для вращения вокруг своей оси
        this.circle.setRadius(width / 2);
        this.setPosition(this.circle.x, this.circle.y);
    }

    //TODO add random position
    public void refreshPosition(){
        float radius = (Gdx.graphics.getHeight() * this.size / 200) / Assets.PPM;
        float x = MathUtils.random(radius, Gdx.graphics.getWidth() / Assets.PPM - radius);
        float y = MathUtils.random(radius, Gdx.graphics.getHeight() / Assets.PPM - radius);
        if(this.circle2.overlaps(new Circle(x, y, radius))){
            this.refreshPosition();
        }else{
            this.setPosition( x, y);}
    }

    @Override
    public void setPosition(float centerX, float centerY) {
        super.setPosition(centerX - targetSprite.getWidth()/2, centerY - targetSprite.getHeight()/2);
        this.targetSprite.setPosition(centerX - targetSprite.getWidth()/2, centerY - targetSprite.getHeight()/2);
        this.circle.setPosition(centerX, centerY);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        this.targetSprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        angle -= delta*speedOfRotation;
        targetSprite.setRotation(angle);
        tweenManager.update(delta);
    }

    @Override
    public Actor hit(float x, float y, boolean touchable) {
        return super.hit(x, y, touchable);
    }

    @Override
    public boolean remove() {
        return super.remove();
    }

    public void refresh(){
        tweenManager.killAll();
        this.setSize(0);
        refreshPosition();
        Tween.set(this,ActorAccessor.TARGETSIZE).target(0).start(tweenManager);
        Tween.to(this,ActorAccessor.TARGETSIZE,1).target(this.size).start(tweenManager);
    }

}
