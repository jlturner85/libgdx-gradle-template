package com.sadc.game.screen;

import com.badlogic.gdx.Screen;

public abstract class GameScreen implements Screen {



    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    protected boolean screenDone;
    protected GameScreen nextGameScreen;

    public GameScreen getNextGameScreen() {
        return nextGameScreen;
    }

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
    }
}
