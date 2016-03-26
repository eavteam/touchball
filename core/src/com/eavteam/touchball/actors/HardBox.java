package com.eavteam.touchball.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.eavteam.touchball.common.Assets;

public class HardBox extends Actor {
    private BodyDef bodyDef;
    private FixtureDef fixtureDef;
    private World world;

    public HardBox(World world){
        this.world = world;
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0,0);

        // ограда по периметру :)
        ChainShape chainShape = new ChainShape();
        chainShape.createChain(new Vector2[]{new Vector2(0,0), new Vector2(Gdx.graphics.getWidth() / Assets.PPM,0),
                new Vector2(Gdx.graphics.getWidth() / Assets.PPM, Gdx.graphics.getHeight() / Assets.PPM),
                new Vector2(0, Gdx.graphics.getHeight() / Assets.PPM), new Vector2(0,0)});

        fixtureDef = new FixtureDef();
        fixtureDef.shape = chainShape;
        fixtureDef.friction = .5f;
        fixtureDef.restitution = 0;
        makeBody();
    }

    private void makeBody(){
     this.world.createBody(bodyDef).createFixture(fixtureDef);
    }
}
