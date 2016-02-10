package com.eavteam.touchball.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Actor;

/*
 Женечка просто дура, с выдвинутой без особой на то причины вздрыжневой генетальной пяней.
  */
public class BallRoundActor extends Actor {
    private Texture roundTexture;
    private Sprite roundSprite;
    private Circle circle;

    public BallRoundActor(){
        roundTexture = new Texture("images/round2.png");
        roundSprite = new Sprite(roundTexture);
        roundSprite.setSize(Gdx.graphics.getHeight() * 20 / 100, Gdx.graphics.getHeight() * 20 / 100); //20% высоты дисплея

        circle = new Circle();
        circle.radius = roundSprite.getHeight() / 2;

        this.setPosition((Gdx.graphics.getWidth() / 2), (Gdx.graphics.getHeight() / 2));
    }

    public void setPosition(float centerX, float centerY){
        this.roundSprite.setPosition(centerX - this.circle.radius, centerY - this.circle.radius);
        this.circle.x = centerX;
        this.circle.y = centerY;
    }

    public void setScale(float scale){
        this.roundSprite.setScale(scale);
        this.circle.radius = scale * roundSprite.getHeight() / 2;
        this.setPosition(this.circle.x, this.circle.y);
    }

    public float getRadius(){
        return this.roundSprite.getWidth() / 2;
    }

    @Override
    public void draw(Batch batch, float alpha){
        this.roundSprite.draw(batch);
    }

    // TODO
    public void dispose() {
        this.roundTexture.dispose();
    }

}
