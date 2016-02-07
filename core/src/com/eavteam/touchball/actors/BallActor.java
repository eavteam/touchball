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


    public BallActor(){
        ballTexture = new Texture("images/Ball.png");

        ballSprite = new Sprite(ballTexture);
        ballSprite.setSize(ballTexture.getWidth(), ballTexture.getHeight());

        rectangle = new Rectangle();
        rectangle.x = (int)ballSprite.getX();
        rectangle.y = (int)ballSprite.getY();
        rectangle.width = (int)ballSprite.getWidth();
        rectangle.height = (int)ballSprite.getHeight();
    }

    public void setPosition(float x, float y){
        this.ballSprite.setPosition(x, y);
//        this.rectangle.x = (int)x; this.rectangle.y = (int)y;
    }

    public void setScale(float x, float y){
        this.ballSprite.setScale(x, y);
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
