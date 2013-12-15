package com.sadc.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.sadc.game.GameConstants;

import java.lang.reflect.Constructor;

/**
 * @author Justin Turner (t590035)
 *         Date: 12/13/13
 */

public class LoadingScreen extends GameScreen {
    private boolean loadingDrawn = false;
    private final SpriteBatch spriteBatch;
    private final BitmapFont font;
    private final FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(GameConstants.LONDON_FONT));
    private final String classNameString;
    public LoadingScreen(String className){
        classNameString = className;

        spriteBatch = new SpriteBatch();
        font = generator.generateFont(40);
    }

    @Override
    public void update(float delta) {
        if (loadingDrawn){
            try{
                Class<?> nextScreen = Class.forName(classNameString);
                nextGameScreen = (GameScreen) nextScreen.newInstance();
                screenDone = true;
            } catch (Exception e){

            }

        }
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void draw(float delta) {

        spriteBatch.begin();
        font.draw(spriteBatch, "Loading...", 455, 50);
        spriteBatch.end();
        //signal that the screen has been drawn
        loadingDrawn = true;
    }

    @Override
    public void dispose(){
        spriteBatch.dispose();
        font.dispose();
        generator.dispose();
    }


}
