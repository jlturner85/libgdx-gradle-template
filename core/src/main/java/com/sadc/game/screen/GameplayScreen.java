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
    private final Texture track;
    private float distance;

    private Player player;

    public GameplayScreen() {
        spriteBatch = new SpriteBatch();
        track = new Texture("tunnel.png");
        distance = 1;

        player = new Player(1);
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
        player.update(delta);

        boolean exit = Gdx.input.isKeyPressed(GameConstants.ESCAPE_KEY);
        if (exit){
            Gdx.app.exit();
        }
    }

    @Override
    public void draw(float delta) {
        distance += delta * player.getSpeed();
        while (distance > 1.126126126) distance -= .126126126;
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.setBlendFunction(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        float scale = 2.586403467047338f;
        for (int i = 0; i < 30; i++) {
            spriteBatch.draw(track, GameConstants.SCREEN_WIDTH / 2 - 250, GameConstants.SCREEN_HEIGHT / 2 - 250,
                    250, 250, 500, 500, distance * scale, distance * scale, 0, 0, 0, 500, 500, false, false);
            scale *= 0.888f;
        }

        player.draw(delta, spriteBatch);
        spriteBatch.end();
    }
}
