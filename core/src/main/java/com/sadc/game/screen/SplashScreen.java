package com.sadc.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;

public class SplashScreen extends GameScreen {
    private float splashScreenTime;
    private final SpriteBatch spriteBatch;
    private final Texture background;
    public SplashScreen(){
        splashScreenTime = 2;
        spriteBatch = new SpriteBatch();
        background = new Texture("badlogic.jpg");
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                nextGameScreen = new MenuScreen();
                screenDone = true;
            }
        }, splashScreenTime);
    }

    @Override
    public void update(float delta) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void draw(float delta) {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.draw(background, 130, 130, 400, 300);
        spriteBatch.end();
    }

    @Override
    public void dispose(){
        spriteBatch.dispose();
        background.dispose();
    }
}
