package com.eavteam.touchball.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.eavteam.touchball.common.Assets;
import com.eavteam.touchball.tests.swipe.SwipeHandler;
import com.eavteam.touchball.tests.swipe.mesh.SwipeTriStrip;

public class SwipeActor extends Actor {

    private Texture tex;
    private ShapeRenderer shapes;
    private SwipeTriStrip tris;
    private SwipeHandler swipe;

    public SwipeActor(){
        tris = new SwipeTriStrip();
        tex = Assets.manager.get(Assets.gradient,Texture.class);
        tex.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        shapes = new ShapeRenderer();
        swipe = new SwipeHandler(20,5,10);
        Gdx.input.setInputProcessor(swipe);
    }

    private void init(){
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        this.tex.bind();
        this.tris.thickness = 15f;
        this.tris.color = Color.ORANGE;
        return;
    }

    public void update(Camera camera){
        this.init();
        this.tris.update(swipe.getFixedList(), swipe.path(), swipe.getDissolve(),swipe.getTimer());
        this.tris.draw(camera);
    }

    public SwipeTriStrip getTris(){
        return tris;
    }

    public void dispose() {
        shapes.dispose();
    }
}
