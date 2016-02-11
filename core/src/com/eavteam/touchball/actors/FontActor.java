package com.eavteam.touchball.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class FontActor extends Actor {


    public BitmapFont orangeBig, white;
    private static final String FONT_CHARACTERS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";

    public FontActor(){
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/obelix_pro_broken.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();

        param.characters = FONT_CHARACTERS; // Cимволы.
        param.size = Gdx.graphics.getHeight() / 36; // Размер шрифта.
        orangeBig = generator.generateFont(param); // Генерируем шрифт.
        orangeBig.setColor(Color.ORANGE); // Цвет.

        param.size = Gdx.graphics.getHeight() / 20;
        white = generator.generateFont(param);
        white.setColor(Color.WHITE);

        generator.dispose();

    }

    public void orangeBig(){

    }

    public void white(){

    }

    @Override
    public void act(float delta) {

    }

    @Override
    public boolean remove() {
        white.dispose();
        orangeBig.dispose();
        return super.remove();
    }
}
