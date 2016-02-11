package com.eavteam.touchball.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class FontActor extends Actor {

    public BitmapFont white;
    private static final String FONT_CHARACTERS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";

    public FontActor(int size){
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/obelix_pro_broken.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();

        param.characters = FONT_CHARACTERS; // Cимволы.
        param.size = size;
        white = generator.generateFont(param);
        white.setColor(Color.WHITE);

        generator.dispose();
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {

    }

    @Override
    public void act(float delta) {

    }


    @Override
    public boolean remove() {
        white.dispose();
        return super.remove();
    }
}
