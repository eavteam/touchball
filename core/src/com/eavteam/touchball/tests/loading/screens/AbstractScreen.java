package com.eavteam.touchball.tests.loading.screens;

import com.badlogic.gdx.Screen;
import com.eavteam.touchball.TouchBallGame;

/**
 * @author Mats Svensson
 */
public abstract class AbstractScreen implements Screen {

    protected TouchBallGame game;

    public AbstractScreen(TouchBallGame game) {
        this.game = game;
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}
