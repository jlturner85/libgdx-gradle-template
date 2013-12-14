package com.sadc.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.sadc.game.GameConstants;

public abstract class GameScreen implements Screen {



    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    protected boolean screenDone;
    protected GameScreen nextGameScreen;
    protected boolean gamePaused;
    public GameScreen getNextGameScreen() {
        return nextGameScreen;
    }
    private SpriteBatch spriteBatch = new SpriteBatch();
    private BitmapFont font = new FreeTypeFontGenerator(Gdx.files.internal(GameConstants.LONDON_FONT)).generateFont(20);
    public abstract void update(float delta);
    public abstract void draw(float delta);
    public boolean isScreenDone() {
        return screenDone;
    }
    @Override
    public void render(float delta) {
        //To change body of implemented methods use File | Settings | File Templates.
        update(delta);
        draw(delta);
    }

    /**
     * @see com.badlogic.gdx.ApplicationListener#resize(int, int)
     */
    @Override
    public void resize(int width, int height) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Called when this screen becomes the current screen for a {@link com.badlogic.gdx.Game}.
     */
    @Override
    public void show() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Called when this screen is no longer the current screen for a {@link com.badlogic.gdx.Game}.
     */
    @Override
    public void hide() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * @see com.badlogic.gdx.ApplicationListener#pause()
     */
    @Override
    public void pause() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * @see com.badlogic.gdx.ApplicationListener#resume()
     */
    @Override
    public void resume() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {
        //To change body of implemented methods use File | Settings | File Templates.
        spriteBatch.dispose();
    }

    public void drawPause(){
        spriteBatch.begin();
        font.draw(spriteBatch, "Paused", 400, 60);
        font.draw(spriteBatch, "Return to Menu", 350, 60);
        spriteBatch.end();
    }

    public void updatePause(){
        if((!GameConstants.OLD_ESCAPE_PRESSED) && (Gdx.input.isKeyPressed(GameConstants.ESCAPE_KEY))){
            gamePaused = false;
        }
        if((!GameConstants.OLD_P1_ENTER_PRESSED) && (Gdx.input.isKeyPressed(GameConstants.P1_B))){
            nextGameScreen = new LoadingScreen("com.sadc.game.screen.SplashScreen");
            screenDone = true;
        }
        GameConstants.OLD_ESCAPE_PRESSED = Gdx.input.isKeyPressed(GameConstants.ESCAPE_KEY);
        GameConstants.OLD_P1_ENTER_PRESSED = Gdx.input.isKeyPressed(GameConstants.P1_B);
    }
}
