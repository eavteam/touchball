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
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.eavteam.touchball.common.Assets;

public class TargetActor extends Actor {
    private Sprite targetSprite;
    private float angle;
    private float speedOfRotation;
    private Body body;
    private BodyDef bodyDef;
    private MouseJointDef mouseJointDef;
    private MouseJoint mouseJoint;
    private World world;
    private Circle circle;

    public TargetActor(World world){
        this.world = world;
        circle = new Circle();
        targetSprite = new Sprite(Assets.manager.get(Assets.target, Texture.class));
        targetSprite.setColor(Color.RED);
        setSize(6f);
        setBounds(targetSprite.getX(),targetSprite.getY(),targetSprite.getWidth(),targetSprite.getHeight());
        refreshPosition();
        setSpeedOfRotation(300f);
    }

    private void setSpeedOfRotation(float speedOfRotation){
        this.speedOfRotation = speedOfRotation;
    }

    public Circle getCircle(){
        return this.circle;
    }

    public void atata(Body bodyIn){
        this.bodyDef = new BodyDef();
        this.bodyDef.type = BodyDef.BodyType.StaticBody;
        this.body = this.world.createBody(this.bodyDef);

        this.mouseJointDef = new MouseJointDef();
        this.mouseJointDef.bodyA = this.body;
        this.mouseJointDef.bodyB = bodyIn;
        this.mouseJointDef.collideConnected = true;
        this.mouseJointDef.maxForce = 2;
        this.mouseJointDef.target.set(bodyIn.getPosition());
        this.mouseJoint = (MouseJoint) this.world.createJoint(this.mouseJointDef);
        this.mouseJoint.setTarget(new Vector2(targetSprite.getX() + targetSprite.getWidth()/2, targetSprite.getY() + targetSprite.getHeight()/2));
    }

    //размер задается в % от высоты дисплея
    public void setSize(float percent){
        this.setSize((Gdx.graphics.getHeight() * percent / 100) / Assets.PPM, (Gdx.graphics.getHeight() * percent / 100) / Assets.PPM);
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        targetSprite.setSize(width, height);
        targetSprite.setOriginCenter(); // для вращения вокруг своей оси
        this.circle.setRadius(width / 2);
    }

    //TODO add random position
    public void refreshPosition(){
        setPosition(((Gdx.graphics.getWidth() - 300) / 2)/Assets.PPM, ( (Gdx.graphics.getHeight() + 500) / 2)/Assets.PPM);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x - targetSprite.getWidth()/2, y - targetSprite.getHeight()/2);
        this.targetSprite.setPosition(x - targetSprite.getWidth()/2, y - targetSprite.getHeight()/2);
        this.circle.setPosition(x - targetSprite.getWidth()/2, y - targetSprite.getHeight()/2);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        this.targetSprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        angle -= delta*speedOfRotation;
        targetSprite.setRotation(angle);
    }

    @Override
    public Actor hit(float x, float y, boolean touchable) {
        return super.hit(x, y, touchable);
    }

    @Override
    public boolean remove() {
        return super.remove();
    }

}
