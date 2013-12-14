package com.sadc.game.screen;

import com.badlogic.gdx.Gdx;
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

    private Track track;

    public GameplayScreen() {
        GameConstants.currentMusic.stop();
        GameConstants.currentMusic.dispose();
        GameConstants.currentMusic = Gdx.audio.newSound(Gdx.files.internal("gameplaymusic1.mp3"));
        long id = GameConstants.currentMusic.play(GameConstants.MUSIC_VOLUME);
        GameConstants.currentMusic.setLooping(id, true);
        spriteBatch = new SpriteBatch();

        track = new Track();
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
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

        boolean exit = Gdx.input.isKeyPressed(GameConstants.ESCAPE_KEY);
        if (exit){
            Gdx.app.exit();
        }
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
