package com.eavteam.touchball.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.eavteam.touchball.common.Assets;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class LogoActor extends Image {

    public LogoActor(){
        super(Assets.manager.get(Assets.eavLogo,Texture.class));
        Texture tex = Assets.manager.get(Assets.eavLogo,Texture.class);

        setSize(tex.getWidth() * 20 / 100, tex.getHeight() * 20 / 100);
        setPosition(Gdx.graphics.getWidth()/2- getWidth()/2,Gdx.graphics.getHeight()/2- getHeight()/1.5f);
        setBounds(getX(),getY(),getWidth(), getHeight());

        addAction(sequence(alpha(0f),delay(2f),fadeIn(3f),delay(2f),fadeOut(2f)));
    }

    @Override
    public boolean remove() {
        return super.remove();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
