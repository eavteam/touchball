package com.eavteam.touchball.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.eavteam.touchball.TouchBallGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = TouchBallGame.TITLE + " v" + TouchBallGame.VERSION;
		config.width = 480;
		config.height = 800;
		config.useGL30 = true;
		config.vSyncEnabled = true;
		new LwjglApplication(new TouchBallGame(), config);
	}
}
