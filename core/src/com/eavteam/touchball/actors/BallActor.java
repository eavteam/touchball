package com.eavteam.touchball.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.eavteam.touchball.common.Assets;

public class BallActor extends Actor {

    private Sprite ballSprite;
//    --------------------------------------------------
    private BodyDef bodyDef;
    private FixtureDef fixtureDef;
    private Body body;
    private float velocityX, velocityY;
//---------------------------------------------------

    private static ActorGestureListener actorGestureListener = new ActorGestureListener(10/Assets.PPM, 0.4f, 1.1f, 0.15f){

        @Override
        public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
            BallActor ball = (BallActor) event.getTarget();
            ball.moveBy(x,y);
            ball.body.setLinearVelocity(0,0);
            ball.body.setAngularVelocity(0);
            ball.velocityX = 0;ball.velocityY = 0;
        }

        @Override
        public void fling (InputEvent event, float velocityX, float velocityY, int button) {
            BallActor ball = (BallActor) event.getTarget();
            ball.velocityX = velocityX; ball.velocityY = velocityY;
        }

        @Override
        public void pan (InputEvent event, float x, float y, float deltaX, float deltaY) {
            BallActor ball = (BallActor) event.getTarget();
            ball.moveBy(x,y);
        }

        @Override
        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            BallActor ball = (BallActor) event.getTarget();
            ball.moveBy(x,y);
            ball.body.setLinearVelocity(ball.velocityX,ball.velocityY);
//            ball.body.applyLinearImpulse(new Vector2(ball.body.getMass()*ball.velocityX, ball.body.getMass()*ball.velocityY), new Vector2(0, 0), true );
//            ball.body.setLinearVelocity(100f, 100f);
        }

    };


    public BallActor(){

        ballSprite = new Sprite(Assets.manager.get(Assets.ball,Texture.class));
        setSize(4);
        setBounds(ballSprite.getX(),ballSprite.getY(),ballSprite.getWidth(),ballSprite.getHeight());

//      ------------------------------------------
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set((Gdx.graphics.getWidth() / 2) / Assets.PPM, (Gdx.graphics.getHeight() / 2) / Assets.PPM);
        CircleShape shape = new CircleShape();
        shape.setRadius(ballSprite.getWidth()/2);

        fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
//        fixtureDef.
        fixtureDef.density = .1f;     //плотность
        fixtureDef.friction = 1.8f;    //трение
        fixtureDef.restitution = .8f; //остаток энергии после столкновения
//        --------------------------------------

        refreshPosition();
        addListener(actorGestureListener);
    }

//---------------------------------------------
    public void makeBody(World world){
        body = world.createBody(this.bodyDef);
        body.createFixture(this.fixtureDef).setUserData(this.ballSprite);
    }
//---------------------------------------------

    //размер задается в % от высоты дисплея
    public void setSize(float percent){
        this.setSize((Gdx.graphics.getHeight() * percent / 100) / Assets.PPM, (Gdx.graphics.getHeight() * percent / 100) / Assets.PPM);
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
        super.setPosition(x - ballSprite.getWidth()/2, y - ballSprite.getHeight()/2);
        this.ballSprite.setPosition(x - ballSprite.getWidth()/2, y - ballSprite.getHeight()/2);
    }

    public void refreshPosition(){
        setPosition((Gdx.graphics.getWidth() / 2)/Assets.PPM, (Gdx.graphics.getHeight() / 2)/Assets.PPM);
    }

    @Override
    public void draw(Batch batch, float alpha){
        this.ballSprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        this.setPosition(this.body.getPosition().x, this.body.getPosition().y);
//        System.out.println("Velocity: " + body.getLinearVelocity().toString() + " mass:" + body.getMassData().mass + " " + ballSprite.getWidth());
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
