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
        ballTexture = new Texture(Gdx.files.internal("images/ball_ink.png"));

        ballSprite = new Sprite(ballTexture);
        ballSprite.setSize(Gdx.graphics.getHeight() * 6 / 100, Gdx.graphics.getHeight() * 6 / 100); // размер шарика 6% от длины дисплея
        ballSprite.setScale(1f, 1f);

        circle = new Circle();
        circle.radius = ballSprite.getHeight() / 2;
        circle.x = ballSprite.getX() + ballSprite.getHeight() / 2;
        circle.y = ballSprite.getY() + ballSprite.getHeight() / 2;

        this.setPosition((Gdx.graphics.getWidth() / 2), (Gdx.graphics.getHeight() / 2));
    }

    //координатами задается центр шарика
    public void setPosition(float centerX, float centerY){
        this.ballSprite.setPosition(centerX - this.ballSprite.getHeight()/2, centerY - this.ballSprite.getHeight()/2);
        this.circle.x = centerX;
        this.circle.y = centerY;
    }

    public void setScale(float scale){
        this.ballSprite.setScale(scale);
        this.circle.radius = ballSprite.getHeight() / 2;
        this.setPosition(this.circle.x, this.circle.y);
    }

    public float getRadius(){
        return this.ballSprite.getWidth() / 2;
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
