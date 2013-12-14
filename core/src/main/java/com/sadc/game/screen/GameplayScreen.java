package com.sadc.game.screen;

import com.badlogic.gdx.Gdx;
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

    public GameplayScreen() {
        spriteBatch = new SpriteBatch();
        track1 = new Texture("tunnel1.png");
        track2 = new Texture("tunnel2.png");
        fade = new Texture("fade.png");
        distance = 0;

        player = new Player(1);
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

        boolean exit = Gdx.input.isKeyPressed(GameConstants.ESCAPE_KEY);
        if (exit){
            Gdx.app.exit();
        }
    }

    @Override
    public void draw(float delta) {
        float drawDistance = 1 + (distance % 0.087f);
        int frame = 11 - (int) ((distance / 0.087f) % 12);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.setBlendFunction(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        float scale = 2.117920011581067f;
        for (int i = 0; i < 50; i++) {
            Texture texture;
            if (i % 12 == frame) {
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
