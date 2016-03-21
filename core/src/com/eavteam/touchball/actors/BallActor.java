package com.eavteam.touchball.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.eavteam.touchball.common.Assets;


public class BallActor extends Actor {

    private Sprite ballSprite;
    private Circle circle;
    private float angle;
    private BodyDef bodyDef, bodyDef2;
    private FixtureDef fixtureDef;
    private MouseJointDef mouseJointDef;
    private MouseJoint mouseJoint;
    private Body body, body2;
    private World world;
    private Vector2 vector21, vector22;

    private static ActorGestureListener actorGestureListener = new ActorGestureListener(10/Assets.PPM, 0.4f, 1.1f, 0.15f){

        @Override
        public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
            BallActor ball = (BallActor) event.getTarget();
            ball.vector21.set(ball.getX() + x, ball.getY() + y);
            ball.mouseJointDef.bodyB = ball.body;
            ball.mouseJointDef.target.set(ball.vector21.x, ball.vector21.y);
            ball.mouseJoint = (MouseJoint) ball.world.createJoint(ball.mouseJointDef);
        }

        @Override
        public void fling (InputEvent event, float velocityX, float velocityY, int button) {
        }

        @Override
        public void pan (InputEvent event, float x, float y, float deltaX, float deltaY) {
            BallActor ball = (BallActor) event.getTarget();
            if(ball.mouseJoint != null) ball.mouseJoint.setTarget(ball.vector22.set(ball.getX() + x, ball.getY() + y));
        }

        @Override
        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            BallActor ball = (BallActor) event.getTarget();
            if(ball.world.getJointCount() != 0) {
                ball.world.destroyJoint(ball.mouseJoint);
                ball.body.setLinearDamping(0.5f); //сообщаем замедление по линейной скорости
                ball.body.setAngularDamping(1f); //сообщаем замедление по угловой скорости
            }
        }

    };

    public BallActor(World world){
        this.world = world;
        vector21 = new Vector2();
        vector22 = new Vector2();

        circle = new Circle();
        ballSprite = new Sprite(Assets.manager.get(Assets.ball,Texture.class));
        ballSprite.setColor(new Color(1f, 0.2f, 1f, 1f));
        setSize(4);
        setBounds(ballSprite.getX(),ballSprite.getY(),ballSprite.getWidth(),ballSprite.getHeight());

        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set((Gdx.graphics.getWidth() / 2) / Assets.PPM, (Gdx.graphics.getHeight() / 2) / Assets.PPM);
        CircleShape shape = new CircleShape();
        shape.setRadius(ballSprite.getWidth()/2);

        fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = .1f;     //плотность
        fixtureDef.friction = 0.18f;    //трение
        fixtureDef.restitution = .85f; //остаток энергии после столкновения

        body = this.world.createBody(this.bodyDef);
        body.createFixture(this.fixtureDef).setUserData(this.ballSprite);

        bodyDef2 = new BodyDef();
        bodyDef2.type = BodyDef.BodyType.StaticBody;
        body2 = this.world.createBody(this.bodyDef2);
        mouseJointDef = new MouseJointDef();
        mouseJointDef.collideConnected = true;
        mouseJointDef.maxForce = 1000;
        mouseJointDef.bodyA = body2;

        refreshPosition();
        startListener();
    }

    public void startListener(){
        this.addListener(actorGestureListener);
    }

    public void stopListener(){
        if(this.world.getJointCount() != 0){
            this.world.destroyJoint(this.mouseJoint);
            this.body.setLinearDamping(0.5f); //сообщаем замедление по линейной скорости
            this.body.setAngularDamping(1f); //сообщаем замедление по угловой скорости
        }
        this.actorGestureListener.getGestureDetector().cancel();
        this.setTouchable(Touchable.disabled);
    }

    //размер задается в % от высоты дисплея
    public void setSize(float percent){
        this.setSize((Gdx.graphics.getHeight() * percent / 100) / Assets.PPM, (Gdx.graphics.getHeight() * percent / 100) / Assets.PPM);
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width,height);
        ballSprite.setSize(width, height);
        ballSprite.setOriginCenter(); // для вращения вокруг своей оси
        circle.setRadius(width/2);
    }

    public Circle getCircle(){
        return circle;
    }

    @Override
    public void moveBy(float x, float y) {
        this.body.setTransform(this.getX() + x, this.getY() + y, this.body.getAngle());
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x - ballSprite.getWidth()/2, y - ballSprite.getHeight()/2);
        this.ballSprite.setPosition(x - ballSprite.getWidth()/2, y - ballSprite.getHeight()/2);
        this.circle.setPosition(x - ballSprite.getWidth()/2, y - ballSprite.getHeight()/2);
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
        angle += this.body.getAngularVelocity()*delta*Assets.PPM; //расчет угла поворота спрайта
        this.ballSprite.setRotation(angle);

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
