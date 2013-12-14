package com.sadc.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sadc.game.GameConstants;
import com.sadc.game.gameobject.Player;
import com.sadc.game.gameobject.Track;

public class GameplayScreen extends GameScreen{

    private final SpriteBatch spriteBatch;

    private boolean paused;
    private Sound explosionSound;
    private Sound trainSound;
    private Sound hitSound;
    private Track track;

    public GameplayScreen() {
        GameConstants.currentMusic.stop();
        GameConstants.currentMusic.dispose();
        GameConstants.currentMusic = Gdx.audio.newSound(Gdx.files.internal("gameplaymusic1.mp3"));
        long id = GameConstants.currentMusic.play(0.05f);
        GameConstants.currentMusic.setLooping(id, true);
        spriteBatch = new SpriteBatch();

        track = new Track();
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("soundeffects/explosion.wav"));
        hitSound = Gdx.audio.newSound(Gdx.files.internal("soundeffects/hit.wav"));
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        explosionSound.dispose();
        hitSound.dispose();
        track.dispose();
    }

    /**
     * Called when the screen should render itself.
     *    asdfasdf
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void update(float delta) {
        if (!paused) {
            track.update(delta);
        }

        if ((!GameConstants.OLD_ESCAPE_PRESSED) && (Gdx.input.isKeyPressed(GameConstants.ESCAPE_KEY))){
            this.gamePaused = true;
        }
        GameConstants.OLD_ESCAPE_PRESSED = Gdx.input.isKeyPressed(GameConstants.ESCAPE_KEY);
    }

    @Override
    public void draw(float delta) {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.setBlendFunction(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        track.draw(delta, spriteBatch);
        spriteBatch.end();
    }
}
