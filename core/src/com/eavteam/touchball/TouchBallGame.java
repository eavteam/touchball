package com.eavteam.touchball;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.eavteam.touchball.screens.MenuScreen;

public class TouchBallGame extends Game {
	public SpriteBatch batch;
	public BitmapFont font, levels;
	private static final String FONT_CHARACTERS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";

	@Override
	public void create () {
		batch = new SpriteBatch();
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/obelix_pro_broken.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
		param.size = Gdx.graphics.getHeight() / 36; // Размер шрифта.
		param.characters = FONT_CHARACTERS; // Cимволы.
		font = generator.generateFont(param); // Генерируем шрифт.
		param.size = Gdx.graphics.getHeight() / 20;
		levels = generator.generateFont(param);
		font.setColor(Color.ORANGE); // Цвет.
		levels.setColor(Color.WHITE);
		generator.dispose();

		this.setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
		}
}
