package com.sadc.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.sadc.game.GameConstants;
import com.sadc.game.animation.Animator;

/**
 * Created with IntelliJ IDEA.
 * Copyright (c) 2013 HEB
 * All rights reserved.
 * <p/>
 * Class Description:
 *
 * @author Justin Turner (t590035)
 *         Date: 12/15/13
 */

public class LevelSelectScreen extends GameScreen {
    private SpriteBatch spriteBatch = new SpriteBatch();
    private BitmapFont font;
    private Texture sky;
    private Texture car1= new Texture("car1_4frame.png");
    private Texture selectBox;
    private Texture liverpoolStreet;
    private Texture paddington;
    private Texture arsenal;
    private float selectX;
    private float selectY = 185;
    private String selectedLevel;
    private String[] levelStringArray = {"liverpool", "paddington", "arsenal"};
    private int currentLevelPosition = 0;
    private final FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(GameConstants.LONDON_FONT));
    public LevelSelectScreen(){
        font = generator.generateFont(GameConstants.MENU_FONT_SIZE);
        sky = new Texture("datNightSky.png");
        liverpoolStreet = new Texture("liverpool.png");
        paddington = new Texture("paddington.png");
        arsenal = new Texture("arsenal.png");
        selectBox = new Texture("selectBox.png");
    }

    @Override
    public void update(float delta) {
        boolean p1Left = Gdx.input.isKeyPressed(GameConstants.P1_LEFT);
        boolean p1Right = Gdx.input.isKeyPressed(GameConstants.P1_RIGHT);
        boolean exit = Gdx.input.isKeyPressed(GameConstants.ESCAPE_KEY);
        boolean p1Enter = Gdx.input.isKeyPressed(GameConstants.P1_B);
        if (exit && !GameConstants.OLD_ESCAPE_PRESSED){
            this.nextGameScreen = new MenuScreen();
            this.screenDone = true;
        }

        if (p1Enter && !GameConstants.OLD_P1_ENTER_PRESSED) {
            this.nextGameScreen = new LoadingScreen("com.sadc.game.screen.GameplayScreen");
            this.screenDone = true;
        }

        if (p1Left && !GameConstants.OLD_P1_LEFT_PRESSED){
            if (currentLevelPosition > 0){
                currentLevelPosition = currentLevelPosition - 1;
            } else {
                currentLevelPosition = levelStringArray.length- 1;
            }
            selectedLevel = levelStringArray[currentLevelPosition];
        } else if (p1Right && !GameConstants.OLD_P1_RIGHT_PRESSED){
            if (currentLevelPosition < levelStringArray.length- 1){
                currentLevelPosition = currentLevelPosition + 1;
            } else {
                currentLevelPosition = 0;
            }
            selectedLevel = levelStringArray[currentLevelPosition];
        }
        switch(currentLevelPosition){
            case 0:
                selectX = 132;
                break;
            case 1:
                selectX = 257;
                break;
            case 2:
                selectX = 382;
                break;
        }
        GameConstants.OLD_P1_LEFT_PRESSED = Gdx.input.isKeyPressed(GameConstants.P1_LEFT);
        GameConstants.OLD_P1_RIGHT_PRESSED = Gdx.input.isKeyPressed(GameConstants.P1_RIGHT);
        GameConstants.OLD_P1_ENTER_PRESSED = p1Enter;
        GameConstants.OLD_ESCAPE_PRESSED = exit;
    }

    @Override
    public void draw(float delta) {
        spriteBatch.begin();
        spriteBatch.draw(sky, 0, 0);
        font.draw(spriteBatch, "Select a Stop", 230, 400);
        spriteBatch.draw(liverpoolStreet, 125, 200);
        spriteBatch.draw(paddington, 250, 200);
        spriteBatch.draw(arsenal, 375, 200);
        font.draw(spriteBatch, "easy", 160, 175);
        font.draw(spriteBatch, "medium", 275, 175);
        font.draw(spriteBatch, "hard", 410, 175);
        spriteBatch.draw(selectBox, selectX, selectY, 110, 110);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        font.dispose();
        sky.dispose();
    }
}
