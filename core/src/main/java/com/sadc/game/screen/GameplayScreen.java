package com.sadc.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sadc.game.GameConstants;
import com.sadc.game.gameobject.Player;

public class GameplayScreen extends GameScreen{

    private final SpriteBatch spriteBatch;
    private final Texture track1;
    private final Texture track2;
    private final Texture fade;
    private float distance;
    private Player player;
    private Sound explosionSound;
    private Sound trainSound;
    private Sound hitSound;

    public GameplayScreen() {
        GameConstants.currentMusic.stop();
        GameConstants.currentMusic.dispose();
        //GameConstants.currentMusic = Gdx.audio.newSound(Gdx.files.internal("gameplaymusic1.mp3"));
        //long id = GameConstants.currentMusic.play(0.05f);
        //GameConstants.currentMusic.setLooping(id, true);
        spriteBatch = new SpriteBatch();
        track1 = new Texture("tunnel1.png");
        track2 = new Texture("tunnel2.png");
        fade = new Texture("fade.png");
        distance = 0;

        player = new Player(1);
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("soundeffects/explosion.wav"));
        hitSound = Gdx.audio.newSound(Gdx.files.internal("soundeffects/hit.wav"));
        trainSound = Gdx.audio.newSound(Gdx.files.internal("soundeffects/trains005.wav"));
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        track1.dispose();
        track2.dispose();
    }

    /**
     * Called when the screen should render itself.
     *    asdfasdf
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void update(float delta) {
        player.update(delta);
        distance += player.getSpeed() / 60f;

        if ((!GameConstants.OLD_ESCAPE_PRESSED) && (Gdx.input.isKeyPressed(GameConstants.ESCAPE_KEY))){
            this.gamePaused = true;
        }
        GameConstants.OLD_ESCAPE_PRESSED = Gdx.input.isKeyPressed(GameConstants.ESCAPE_KEY);
    }

    @Override
    public void draw(float delta) {
        float drawDistance = 1 + (distance % 0.087f);
        int frame = 7 - (int) ((distance / 0.087f) % 8);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.setBlendFunction(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        float scale = 2.117920011581067f;
        for (int i = 0; i < 50; i++) {
            Texture texture;
            if (i % 8 == frame) {
                texture = track2;
            } else {
                texture = track1;
            }
            spriteBatch.draw(texture, GameConstants.SCREEN_WIDTH / 2 - 250, GameConstants.SCREEN_HEIGHT / 2 - 250,
                    250, 250, 500, 500, drawDistance * scale, drawDistance * scale, 0, 0, 0, 500, 500, false, false);
            scale *= 0.92f;
        }
        spriteBatch.draw(fade, GameConstants.SCREEN_WIDTH / 2 - 25, GameConstants.SCREEN_HEIGHT / 2 - 25,
                25, 25, 50, 50, 1, 1, 0, 0, 0, 50, 50, false, false);

        player.draw(delta, spriteBatch);
        spriteBatch.end();
    }
}
