package com.eavteam.touchball.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Assets {

    public static final AssetManager manager = new AssetManager();

    public static final String ball = "images/ball_ink.png";
    public static final String eavLogo = "images/eav_logo.png";
    public static final String roundWhite = "images/round_white.png";
    public static final String gradient = "images/gradient.png";
    public static final String background = "images/background_sky.png";
    public static final String gradientBall = "images/gradient_ball.png";
    public static final String blenderSection = "images/blender_section.png";
    public static final float PPM = 50;

    public static BitmapFont font24 = generateFont(24);
    public static BitmapFont font32 = generateFont(32);
    public static BitmapFont font48 = generateFont(48);
    private static final String FONT_CHARACTERS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";

    private static BitmapFont generateFont(int size) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/obelix_pro_broken.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.characters = FONT_CHARACTERS; // Cимволы.
        param.size = size;
        BitmapFont font = generator.generateFont(param);
        font.setColor(Color.WHITE);
        generator.dispose();
        return font;
    }

    public static void load() {
        manager.load(ball, Texture.class);
        manager.load(eavLogo, Texture.class);
        manager.load(roundWhite, Texture.class);
        manager.load(gradient, Texture.class);
        manager.load(background, Texture.class);
        manager.load(gradientBall, Texture.class);
        manager.load(blenderSection, Texture.class);
    }

    public static void dispose() {
        manager.dispose();
        font24.dispose();
        font32.dispose();
        font48.dispose();
    }

}