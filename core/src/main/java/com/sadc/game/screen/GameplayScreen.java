package com.sadc.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sadc.game.GameConstants;

public class GameplayScreen extends GameScreen{

    private final SpriteBatch spriteBatch;
    private final Texture track;
    private float aliveTime;

    public GameplayScreen() {
        spriteBatch = new SpriteBatch();
        track = new Texture("twocircles.png");
        aliveTime = 1.4f;
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        track.dispose();
    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void update(float delta) {
        boolean exit = Gdx.input.isKeyPressed(GameConstants.ESCAPE_KEY);
        if (exit){
            Gdx.app.exit();
        }
    }

    @Override
    public void draw(float delta) {
        aliveTime *=  1 + delta;
        if (aliveTime > 3) aliveTime -= 1.8;
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.setBlendFunction(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        spriteBatch.draw(track, 0, 0, 439f / 2, 417f / 2, 439, 417, aliveTime * 0.0256f, aliveTime * 0.0256f, 0, 0, 0, 439, 417, false, false);
        spriteBatch.draw(track, 0, 0, 439f / 2, 417f / 2, 439, 417, aliveTime * 0.064f, aliveTime * 0.064f, 0, 0, 0, 439, 417, false, false);
        spriteBatch.draw(track, 0, 0, 439f / 2, 417f / 2, 439, 417, aliveTime * 0.16f, aliveTime * 0.16f, 0, 0, 0, 439, 417, false, false);
        spriteBatch.draw(track, 0, 0, 439f / 2, 417f / 2, 439, 417, aliveTime * 0.4f, aliveTime * 0.4f, 0, 0, 0, 439, 417, false, false);
        spriteBatch.draw(track, 0, 0, 439f / 2, 417f / 2, 439, 417, aliveTime, aliveTime, 0, 0, 0, 439, 417, false, false);
        spriteBatch.end();
    }
}
