package com.eavteam.touchball.actors;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.eavteam.touchball.common.Assets;
import com.eavteam.touchball.screens.PlayScreen;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleBy;


public class BallActor extends Actor {

    private Sprite ballSprite;
    private Circle circle;
    private float lastX;
    private float lastY;
    private float velocityX;
    private float velocityY;
    private float gravity;

    private static DragListener dl = new DragListener() {
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            BallActor b = (BallActor) event.getTarget();
            b.moveBy(x,y);
            b.lastX = b.getX();
            b.lastY = b.getY();
            return true;
        }

        @Override
        public void touchDragged(InputEvent event, float x, float y, int pointer) {
            BallActor b = (BallActor) event.getTarget();
            b.moveBy(x,y);
            b.lastX = b.getX();
            b.lastY = b.getY();
        }

        @Override
        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            BallActor b = (BallActor) event.getTarget();
            b.moveBy(x,y);

        }
    };

    public BallActor(){

        ballSprite = new Sprite(Assets.manager.get(Assets.ball,Texture.class));
        circle = new Circle();
        circle.radius = ballSprite.getHeight() / 2;
        setSize(ballSprite.getTexture().getWidth() * 20 / 100, ballSprite.getTexture().getHeight() * 20 / 100);
        setBounds(ballSprite.getX(),ballSprite.getY(),ballSprite.getWidth(),ballSprite.getHeight());

        setSize(4);

        refreshPosition();
        addListener(dl);

    }

    //размер задается в % от высоты дисплея
    public void setSize(float percent){
        setSize(Gdx.graphics.getHeight() * percent / 100,Gdx.graphics.getHeight() * percent / 100);
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width,height);
        ballSprite.setSize(width, height);
        circle.radius = ballSprite.getHeight() / 2;
        setPosition(this.circle.x, this.circle.y);
    }

    @Override
    public void moveBy(float x, float y) {
        setPosition(this.getX()+ x,this.getY() + y);

    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x - this.circle.radius,y - this.circle.radius);
        this.ballSprite.setPosition(x - this.circle.radius, y - this.circle.radius);
        this.circle.x = x;
        this.circle.y = y;
    }

    public void refreshPosition(){
        setPosition((Gdx.graphics.getWidth() / 2), (Gdx.graphics.getHeight() / 2));
    }

    public float getRadius(){
        return this.circle.radius;
    }

    @Override
    public void draw(Batch batch, float alpha){
        this.ballSprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

    }


    @Override
    public Actor hit(float x, float y, boolean touchable) {
        return super.hit(x,y,touchable);
    }

    @Override
    public boolean remove() {
        return super.remove();
    }

}
