package com.sadc.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.sadc.game.GameConstants;

public class SplashScreen extends GameScreen {
    private float splashScreenTime;
    private final SpriteBatch spriteBatch;
    private final Texture background;
    public SplashScreen(){
        if (GameConstants.currentMusic != null){
            GameConstants.currentMusic.stop();
            GameConstants.currentMusic.dispose();
        }
        GameConstants.currentMusic = Gdx.audio.newSound(Gdx.files.internal("menu.mp3"));
        long id = GameConstants.currentMusic.play(GameConstants.MUSIC_VOLUME);
        GameConstants.currentMusic.setLooping(id, true);
        splashScreenTime = 5;
        spriteBatch = new SpriteBatch();
        background = new Texture("seems-legit-tunnel.jpg");
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
        boolean exit = Gdx.input.isKeyPressed(GameConstants.ESCAPE_KEY);
        boolean p1Enter = Gdx.input.isKeyPressed(GameConstants.P1_B);
        if ((exit && !GameConstants.OLD_ESCAPE_PRESSED) || (p1Enter && !GameConstants.OLD_P1_ENTER_PRESSED)) {
            this.nextGameScreen = new MenuScreen();
            this.screenDone = true;
        }
        GameConstants.OLD_P1_ENTER_PRESSED = p1Enter;
        GameConstants.OLD_ESCAPE_PRESSED = exit;
    }

    @Override
    public void draw(float delta) {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.draw(background, 80, 50, 500, 400);
        spriteBatch.end();
    }

    @Override
    public void dispose(){
        spriteBatch.dispose();
        background.dispose();
    }
}
