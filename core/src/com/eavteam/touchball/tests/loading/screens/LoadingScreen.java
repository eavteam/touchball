package com.eavteam.touchball.tests.loading.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.eavteam.touchball.TouchBallGame;
import com.eavteam.touchball.common.Assets;
import com.eavteam.touchball.screens.MenuScreen;
import com.eavteam.touchball.tests.loading.LoadingBar;

/**
 * @author Mats Svensson
 */
public class LoadingScreen extends AbstractScreen {

    private Stage stage;

    private Image logo;
    private Image loadingFrame;
    private Image loadingBarHidden;
    private Image screenBg;
    private Image loadingBg;

    private float startX, endX;
    private float percent;

    private Actor loadingBar;

    public LoadingScreen(TouchBallGame game) {
        super(game);
    }

    @Override
    public void show() {
        // Tell the manager to load assets for the loading screen
        Assets.manager.load("progressBar/loading.pack", TextureAtlas.class);
        // Wait until they are finished loading
        Assets.manager.finishLoading();

        // Initialize the stage where we will place everything
        stage = new Stage();

        Table table = new Table();
        // Get our textureatlas from the manager
        TextureAtlas atlas = Assets.manager.get("progressBar/loading.pack", TextureAtlas.class);

        // Grab the regions from the atlas and create some images
        logo = new Image(atlas.findRegion("libgdx-logo"));
        loadingFrame = new Image(atlas.findRegion("loading-frame"));
        loadingBarHidden = new Image(atlas.findRegion("loading-bar-hidden"));
        screenBg = new Image(atlas.findRegion("screen-bg"));
        loadingBg = new Image(atlas.findRegion("loading-frame-bg"));

        // Add the loading bar animation
        Animation anim = new Animation(0.05f, atlas.findRegions("loading-bar-anim") );
        anim.setPlayMode(Animation.PlayMode.LOOP_REVERSED);
        loadingBar = new LoadingBar(anim);

        // Or if you only need a static bar, you can do
        // loadingBar = new Image(atlas.findRegion("loading-bar1"));

        // Add all the actors to the stage
        table.top();
        table.addActor(screenBg);
        table.row();
        table.addActor(loadingBar);
        table.row();
        table.addActor(loadingBg);
        table.row();
        table.addActor(loadingBarHidden);
        table.row();
        table.addActor(loadingFrame);
        table.row();
        table.addActor(logo);

        stage.addActor(table);

        // Add everything to be loaded, for instance:
        // game.manager.load("data/assets1.pack", TextureAtlas.class);
        // game.manager.load("data/assets2.pack", TextureAtlas.class);
        // game.manager.load("data/assets3.pack", TextureAtlas.class);
    }

    @Override
    public void resize(int width, int height) {
        // Set our screen to always be XXX x 480 in size
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Assets.manager.update()) { // Load some, will return true if done loading
            if (Gdx.input.isTouched()) { // If the screen is touched after the game is done loading, go to the main menu screen
                game.setScreen(new MenuScreen(game));
            }
        }

        // Interpolate the percentage to make it more smooth
        percent = Interpolation.linear.apply(percent, Assets.manager.getProgress(), 0.1f);

        // Update positions (and size) to match the percentage
        loadingBarHidden.setX(startX + endX * percent);
        loadingBg.setX(loadingBarHidden.getX() + 30);
        loadingBg.setWidth(450 - 450 * percent);
        loadingBg.invalidate();

        // Show the loading screen
        stage.act();
        stage.draw();
    }

    @Override
    public void hide() {

    }
}
