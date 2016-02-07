package com.eavteam.touchball.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.awt.*;

/**
 * Created by nepeyvoda-va on 05.02.2016.
 */
public class BallActor extends Actor {
    private Texture ballTexture;
    private Sprite ballSprite;
    private Rectangle rectangle;

    private boolean b = false;
    private float speedAnimation = 0;
    private float scaleX = 0.5f;
    private float scaleY = 0.5f;
    private float colorR = 1;


    public BallActor(){
        ballTexture = new Texture("images/ball_ink.png");

        ballSprite = new Sprite(ballTexture);
        ballSprite.setSize(ballTexture.getWidth(), ballTexture.getHeight());
        ballSprite.setScale(0.5f, 0.5f);

        rectangle = new Rectangle();
        rectangle.x = (int)ballSprite.getX();
        rectangle.y = (int)ballSprite.getY();
        rectangle.width = (int)ballSprite.getWidth();
        rectangle.height = (int)ballSprite.getHeight();
    }

    public void setPosition(float x, float y){
        this.ballSprite.setPosition(x, y);
        this.rectangle.x = (int)x;
        this.rectangle.y = (int)y;
    }

    public void setScale(float x, float y){
        this.ballSprite.setScale(x, y);
        this.rectangle.width = (int)x;
        this.rectangle.height = (int)y;
    }

    public float getWidth(){
        return this.ballSprite.getWidth();
    }

    public float getHeight(){
        return this.ballSprite.getHeight();
    }

    public void setAnimationColor(float speed){
        this.speedAnimation = speed;
    }

    public void animationUpdate(float delta){

        this.ballSprite.setColor(1f, 1f, 1f, 1f);
    }

    @Override
    public void draw(Batch batch, float alpha){
        this.ballSprite.draw(batch);
    }
// TODO
    public void dispose() {
        ballTexture.dispose();
    }
}
