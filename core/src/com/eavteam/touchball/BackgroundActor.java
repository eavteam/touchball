package com.eavteam.touchball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by nepeyvoda-va on 05.02.2016.
 */
public class BackgroundActor extends Actor {
    private Texture backgroundTexture;
    private Sprite backgroundSprite;

    public BackgroundActor(){
        backgroundTexture = new Texture("background5554.jpg");
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
