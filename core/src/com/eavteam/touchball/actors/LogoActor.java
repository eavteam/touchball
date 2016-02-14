package com.eavteam.touchball.actors;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.eavteam.touchball.TouchBallGame;
import com.eavteam.touchball.common.Assets;
import com.eavteam.touchball.screens.MenuScreen;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class LogoActor extends Image {

    public LogoActor(){
        super(Assets.manager.get(Assets.eavLogo,Texture.class));
        Texture tex = Assets.manager.get(Assets.eavLogo,Texture.class);

        setSize(tex.getWidth() * 20 / 100, tex.getHeight() * 20 / 100);
        setPosition(Gdx.graphics.getWidth()/2- getWidth()/2,Gdx.graphics.getHeight()/2- getHeight()/1.5f);
        setBounds(getX(),getY(),getWidth(), getHeight());

        addAction(sequence(alpha(0f), delay(1f), fadeIn(2f), delay(1f), fadeOut(2f),delay(1f),
                new Action() {
                    @Override
                    public boolean act(float delta) {
                        TouchBallGame game = (TouchBallGame) Gdx.app.getApplicationListener();
                        game.setScreen(new MenuScreen(game));
                        return true;
                    }
                }));
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
