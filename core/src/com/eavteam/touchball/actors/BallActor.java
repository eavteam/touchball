package com.eavteam.touchball.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.awt.*;

/**
 * Created by хуй пизда джигурда on 05.02.2016.
 */
public class BallActor extends Actor {
    private Texture ballTexture;
    private Sprite ballSprite;
    private Rectangle rectangle;

    private boolean increment = false;
    private float speedAnimationColor = 0f;
    private float scaleX = 1f;
    private float scaleY = 1f;
    private float colorR = 1f;


    public BallActor(){
        ballTexture = new Texture("images/ball_ink.png");

        ballSprite = new Sprite(ballTexture);
        ballSprite.setSize(Gdx.graphics.getHeight() * 6 / 100, Gdx.graphics.getHeight() * 6 / 100); // размер шарика 6% от длины дисплея
        ballSprite.setScale(scaleX, scaleY);

        rectangle = new Rectangle();
        rectangle.x = (int)ballSprite.getX();
        rectangle.y = (int)ballSprite.getY();
        rectangle.width = (int)ballSprite.getWidth();
        rectangle.height = (int)ballSprite.getHeight();

        this.setPosition((Gdx.graphics.getWidth() / 2) - (this.getWidth() / 2), (Gdx.graphics.getHeight() / 2) - (this.getHeight() / 2));

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

    public void setAnimationColor(float speedAnimation){
        this.speedAnimationColor = speedAnimation;
    }

    @Override
    public void draw(Batch batch, float alpha){
        this.ballSprite.draw(batch);
    }
// TODO
    public void dispose() {
        this.ballTexture.dispose();
    }
}
