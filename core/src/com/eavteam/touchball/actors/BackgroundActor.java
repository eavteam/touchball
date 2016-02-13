package com.eavteam.touchball.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.eavteam.touchball.common.Assets;

public class BackgroundActor extends Actor {

    private Sprite backgroundSprite;

    public BackgroundActor(){
        backgroundSprite = new Sprite(Assets.manager.get(Assets.background,Texture.class));
        backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.setPosition(0, 0);
    }

    @Override
    public void draw(Batch batch, float alpha){
        backgroundSprite.draw(batch);
    }

    public void dispose() {
        this.remove();
    }
}
