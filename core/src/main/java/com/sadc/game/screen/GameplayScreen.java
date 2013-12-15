package com.sadc.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.sadc.game.GameConstants;
import com.sadc.game.gameobject.Track;

public class GameplayScreen extends GameScreen{

    private final SpriteBatch spriteBatch;

    private final FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(GameConstants.LONDON_FONT));
    private BitmapFont kphFont;
    private BitmapFont timerFont;

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
        kphFont = generator.generateFont(32);
        timerFont = generator.generateFont(56);

        track = new Track();
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("soundeffects/explosion.wav"));
        hitSound = Gdx.audio.newSound(Gdx.files.internal("soundeffects/hit.wav"));
    }

    @Override
    public void dispose() {
        generator.dispose();
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
            this.paused = true;
        }
        GameConstants.OLD_ESCAPE_PRESSED = Gdx.input.isKeyPressed(GameConstants.ESCAPE_KEY);
    }

    @Override
    public void draw(float delta) {
        if (!paused){
            Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
            spriteBatch.begin();
            spriteBatch.setBlendFunction(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            track.draw(delta, spriteBatch);
            kphFont.draw(spriteBatch, (int)(track.getPlayer().getSpeed() * 40) + " KMH", 15, 50);
            timerFont.draw(spriteBatch, Integer.toString((int)Math.ceil(track.getTimer() / 60f)) , 15, 450);
            spriteBatch.end();
        }
    }

    @Override
    public void pause(){
        this.paused = true;
    }

    @Override
    public void resume(){
        this.paused = false;
    }

}
