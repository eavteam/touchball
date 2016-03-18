package com.eavteam.touchball.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.eavteam.touchball.common.Assets;

/**
 * Created by nepeyvoda-va on 19.02.2016.
 */
public class HardBox extends Actor {
    private BodyDef bodyDef;
    private FixtureDef fixtureDef;

    public HardBox(){
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
    }

    public void makeBody(World world){
     world.createBody(bodyDef).createFixture(fixtureDef);
    }
}
