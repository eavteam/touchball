package com.eavteam.touchball.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BackgroundActor extends Actor {
    private Texture backgroundTexture;
    private Sprite backgroundSprite;

    public BackgroundActor(){
        backgroundTexture = new Texture("images/background5554.jpg");
        backgroundSprite = new Sprite(backgroundTexture);
        backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void rotationFuck(float z){
        backgroundSprite.setRotation(z);
    }

    @Override
    public void draw(Batch batch, float alpha){
        backgroundSprite.draw(batch);
    }
//TODO
    public void dispose() {
        backgroundTexture.dispose();
    }
}
