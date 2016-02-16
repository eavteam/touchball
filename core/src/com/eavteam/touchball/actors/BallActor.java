package com.eavteam.touchball.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.eavteam.touchball.common.Assets;

public class BallActor extends Actor {

    private Sprite ballSprite;
//    --------------------------------------------------
    private BodyDef bodyDef;
    private FixtureDef fixtureDef;
    public Body body;
    private float velocityX, velocityY;
//---------------------------------------------------
    private static DragListener dl = new DragListener() {
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            BallActor b = (BallActor) event.getTarget();
            b.moveBy(x,y);
            return true;
        }

        @Override
        public void touchDragged(InputEvent event, float x, float y, int pointer) {
            BallActor b = (BallActor) event.getTarget();
            b.moveBy(x,y);

        }

        @Override
        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            BallActor b = (BallActor) event.getTarget();
            b.moveBy(x,y);
            b.body.setLinearVelocity(b.velocityX,b.velocityY);
        }
    };


    public BallActor(){

        ballSprite = new Sprite(Assets.manager.get(Assets.ball,Texture.class));
        setSize(ballSprite.getTexture().getWidth() * 20 / 100, ballSprite.getTexture().getHeight() * 20 / 100);
        setBounds(ballSprite.getX(),ballSprite.getY(),ballSprite.getWidth(),ballSprite.getHeight());

        setSize(4);

//      ------------------------------------------
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set((Gdx.graphics.getWidth() / 2), (Gdx.graphics.getHeight() / 2));
        CircleShape shape = new CircleShape();
        shape.setRadius(ballSprite.getWidth()/2);

        fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.8f;
        fixtureDef.restitution = 3f;
//        --------------------------------------

        refreshPosition();
        addListener(dl);
    }

//---------------------------------------------
    public void makeBody(World world){
        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        body.setUserData(ballSprite);
    }

    public void updateVelocity(float velocityX, float velocityY){
        this.velocityX = velocityX; this.velocityY = velocityY;
    }
//---------------------------------------------

    //размер задается в % от высоты дисплея
    public void setSize(float percent){
        setSize(Gdx.graphics.getHeight() * percent / 100,Gdx.graphics.getHeight() * percent / 100);
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width,height);
        ballSprite.setSize(width, height);
    }

    @Override
    public void moveBy(float x, float y) {
        this.body.setTransform(this.getX() + x, this.getY() + y, this.body.getAngle());
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x - ballSprite.getWidth()/2,y - ballSprite.getHeight()/2);
        this.ballSprite.setPosition(x - ballSprite.getWidth()/2, y - ballSprite.getHeight()/2);
    }

    public void refreshPosition(){
        setPosition((Gdx.graphics.getWidth() / 2), (Gdx.graphics.getHeight() / 2));
    }

    @Override
    public void draw(Batch batch, float alpha){
        this.ballSprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        this.setPosition(this.body.getPosition().x, this.body.getPosition().y);
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
