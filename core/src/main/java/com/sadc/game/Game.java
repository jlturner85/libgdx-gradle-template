package com.sadc.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sadc.game.screen.GameScreen;
import com.sadc.game.screen.GameplayScreen;
import com.sadc.game.screen.MenuScreen;
import com.sadc.game.screen.SplashScreen;

/**
 * Created with IntelliJ IDEA.
 * Copyright (c) 2013 HEB
 * All rights reserved.
 * <p/>
 * Class Description:
 *
 * @author Justin Turner (t590035)
 *         Date: 12/10/13
 */

public class Game extends com.badlogic.gdx.Game {
    private Music music;

    @Override
    public void render () {
        GameScreen currentScreen = getScreen();

        // update the screen
        currentScreen.render(Gdx.graphics.getDeltaTime());

        // When the screen is done we change to the
        // next screen. Ideally the screen transitions are handled
        // in the screen itself or in a proper state machine.
        if (currentScreen.isScreenDone()) {
            // dispose the resources of the current screen
            currentScreen.dispose();
            //set the next screen
            setScreen(currentScreen.getNextGameScreen());
        }
    }

    @Override
    public GameScreen getScreen () {
        return (GameScreen) super.getScreen();
    }
    /**
     * Called when the {@link com.badlogic.gdx.Application} is first created.
     */
    @Override
    public void create() {
        setScreen(new SplashScreen());
        //music = Gdx.audio.newMusic(Gdx.files.getFileHandle("data/8.12.mp3", Files.FileType.Internal));
        //music.setLooping(true);
        //music.play();
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyUp(int keycode) {
                if (keycode == Input.Keys.ENTER && Gdx.app.getType() == Application.ApplicationType.WebGL) {
                    if (!Gdx.graphics.isFullscreen()) Gdx.graphics.setDisplayMode(Gdx.graphics.getDisplayModes()[0]);
                }
                return true;
            }
        });
    }
}
