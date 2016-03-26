package com.eavteam.touchball.actors;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.eavteam.touchball.common.Assets;
import com.eavteam.touchball.tween.ActorAccessor;


public class BallActor extends Actor {
    public final static float size = 4;
    private Sprite ballSprite;
    private Circle circle;
    private float angle;
    private float sizeForTween;
    private BodyDef bodyDef, bodyDef2;
    private FixtureDef fixtureDef;
    private MouseJointDef mouseJointDef;
    private MouseJoint mouseJoint;
    private Body body, body2;
    private World world;
    private Vector2 vector21, vector22;
    private TweenManager tweenManager;

    private static ActorGestureListener actorGestureListener = new ActorGestureListener(10/Assets.PPM, 0.4f, 1.1f, 0.15f){

        @Override
        public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
            BallActor ball = (BallActor) event.getTarget();
            ball.mouseJointDef.maxForce = 1000;
            ball.mouseJointDef.target.set(ball.vector21.set(ball.body.getPosition()));
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
                ball.body.setLinearDamping(1f); //сообщаем замедление по линейной скорости
                ball.body.setAngularDamping(1f); //сообщаем замедление по угловой скорости
            }
        }

    };

    public BallActor(World world){
        this.world = world;
        vector21 = new Vector2();
        vector22 = new Vector2();

        circle = new Circle();
        Assets.manager.load(Assets.ball, Texture.class);
        ballSprite = new Sprite(Assets.manager.get(Assets.ball,Texture.class));
        ballSprite.setColor(new Color(1f, 0.2f, 1f, 1f));
        setSize(size);
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
        shape.dispose();

        bodyDef2 = new BodyDef();
        bodyDef2.type = BodyDef.BodyType.StaticBody;
        body2 = this.world.createBody(this.bodyDef2);
        mouseJointDef = new MouseJointDef();
        mouseJointDef.collideConnected = false;
        mouseJointDef.bodyA = body2;
        mouseJointDef.bodyB = body;

        refreshPosition();
        startListener();
        tweenManager = new TweenManager();
    }

    public void startListener(){
        this.addListener(actorGestureListener);
        this.setTouchable(Touchable.enabled);
        this.actorGestureListener.getGestureDetector().reset();
    }

    public void stopListener(){
        if(this.world.getJointCount() != 0){
            this.world.destroyJoint(this.mouseJoint);
            this.body.setLinearDamping(1f); //сообщаем замедление по линейной скорости
            this.body.setAngularDamping(1f); //сообщаем замедление по угловой скорости
        }
        this.actorGestureListener.getGestureDetector().cancel();
        this.setTouchable(Touchable.disabled);
    }

    public Body getBody(){
        return this.body;
    }

    //размер задается в % от высоты дисплея
    public void setSize(float sizeInPercent){
        this.sizeForTween = sizeInPercent;
        this.setSize((Gdx.graphics.getHeight() * sizeInPercent / 100) / Assets.PPM, (Gdx.graphics.getHeight() * sizeInPercent / 100) / Assets.PPM);
    }

    public float getSize(){ return sizeForTween;}

    public void atata(float targetX, float targetY){
        this.mouseJointDef.maxForce = 3;
        this.mouseJointDef.target.set(this.vector21.set(this.body.getPosition()));
        this.mouseJoint = (MouseJoint) this.world.createJoint(this.mouseJointDef);
        if(this.mouseJoint != null) this.mouseJoint.setTarget(this.vector22.set(targetX, targetY));
        Tween.registerAccessor(Actor.class,new ActorAccessor());
        Tween.set(this,ActorAccessor.BALLSIZE).target(this.size).start(tweenManager);
        Tween.to(this,ActorAccessor.BALLSIZE,1).target(0).start(tweenManager);
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width,height);
        ballSprite.setSize(width, height);
        ballSprite.setOriginCenter(); // для вращения вокруг своей оси
        circle.setRadius(width/2);
        this.setPosition(circle.x, circle.y);
    }

    public Circle getCircle(){
        return circle;
    }

    @Override
    public void moveBy(float x, float y) {
        this.body.setTransform(this.getX() + x, this.getY() + y, this.body.getAngle());
    }

    @Override
    public void setPosition(float centerX, float centerY) {
        super.setPosition(centerX - ballSprite.getWidth()/2, centerY - ballSprite.getHeight()/2);
        this.ballSprite.setPosition(centerX - ballSprite.getWidth()/2, centerY - ballSprite.getHeight()/2);
        this.circle.setPosition(centerX, centerY);
    }

    public void refreshPosition(){
        setPosition((Gdx.graphics.getWidth() / 2)/Assets.PPM, (Gdx.graphics.getHeight() / 2)/Assets.PPM);
        this.body.setTransform((Gdx.graphics.getWidth() / 2)/Assets.PPM, (Gdx.graphics.getHeight() / 2)/Assets.PPM , 0);
    }

    @Override
    public void draw(Batch batch, float alpha){
        this.ballSprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        this.setPosition(this.body.getPosition().x, this.body.getPosition().y);
        angle += this.body.getAngularVelocity()*delta*MathUtils.radiansToDegrees; //расчет угла поворота спрайта
        this.ballSprite.setRotation(angle);
        tweenManager.update(delta);
    }

    @Override
    public Actor hit(float x, float y, boolean touchable) {
        return super.hit(x,y,touchable);
    }

    @Override
    public boolean remove() {
        return super.remove();
    }

    public void refresh(){
        tweenManager.killAll();
        this.setSize(0);
        if(this.world.getJointCount() != 0) this.world.destroyJoint(this.mouseJoint);
        Tween.set(this,ActorAccessor.BALLSIZE).target(0).start(tweenManager);
        Tween.to(this,ActorAccessor.BALLSIZE,1).target(this.size).start(tweenManager);
        refreshPosition();
        this.startListener();
    }

}
