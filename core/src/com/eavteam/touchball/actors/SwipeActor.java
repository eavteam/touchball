package com.eavteam.touchball.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.eavteam.touchball.swipe.mesh.SwipeTriStrip;

public class SwipeActor extends Actor {

    private Texture tex;
    private ShapeRenderer shapes;
    private SwipeTriStrip tris;

    public SwipeActor(){
        tris = new SwipeTriStrip();
        tex = new Texture("images/gradient.png");
        tex.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        shapes = new ShapeRenderer();

    }

    public void init(){
        this.tex.bind();
        this.tris.thickness = 20f;
        this.tris.color = Color.ORANGE;
    }

    public SwipeTriStrip getTris(){
        return tris;
    }

    public void dispose() {
        tex.dispose();
        shapes.dispose();
    }
}
