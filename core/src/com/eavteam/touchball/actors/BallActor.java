package com.eavteam.touchball.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class BallActor extends Actor {
    private Texture ballTexture;
    private Sprite ballSprite;
    private Circle circle;

    public BallActor(){
        ballTexture = new Texture("images/ball_ink.png");

        ballSprite = new Sprite(ballTexture);
        ballSprite.setSize(Gdx.graphics.getHeight() * 6 / 100, Gdx.graphics.getHeight() * 6 / 100); // размер шарика 6% от длины дисплея

        circle = new Circle();
        circle.radius = ballSprite.getHeight() / 2;

        this.setPosition((Gdx.graphics.getWidth() / 2), (Gdx.graphics.getHeight() / 2));
    }

    //координатами задается центр шарика
    public void setPosition(float centerX, float centerY){
        this.ballSprite.setPosition(centerX - this.circle.radius, centerY - this.circle.radius);
        this.circle.x = centerX;
        this.circle.y = centerY;
    }

    //размер задается в % от высоты дисплея
    public void setSize(float percent){
        this.ballSprite.setSize(Gdx.graphics.getHeight() * percent / 100, Gdx.graphics.getHeight() * percent / 100);
        this.circle.radius = ballSprite.getHeight() / 2;
        this.setPosition(this.circle.x, this.circle.y);
    }

    public float getRadius(){
        return this.circle.radius;
    }

    @Override
    public void draw(Batch batch, float alpha){
        this.ballSprite.draw(batch,alpha);
    }
    // TODO
    public void dispose() {
        this.ballTexture.dispose();
    }
}
