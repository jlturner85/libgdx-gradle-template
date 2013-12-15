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

public class CarSelectScreen extends GameScreen {
    private SpriteBatch spriteBatch = new SpriteBatch();
    private BitmapFont font;
    private Texture sky;
    private Texture car1= new Texture("car1_4frame.png");
    private Texture selectBox;
    private Animator car1Animator;
    private final FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(GameConstants.LONDON_FONT));
    public CarSelectScreen(){
        font = generator.generateFont(GameConstants.MENU_FONT_SIZE);
        sky = new Texture("datNightSky.png");
        selectBox = new Texture("selectBox.png");
        car1Animator = new Animator(car1, GameConstants.CAR1_SPRITE_COLUMNS, GameConstants.CAR1_SPRITE_ROWS, GameConstants.CAR1_FRAME_DURATION);

    }

    @Override
    public void update(float delta) {
        boolean exit = Gdx.input.isKeyPressed(GameConstants.ESCAPE_KEY);
        boolean p1Enter = Gdx.input.isKeyPressed(GameConstants.P1_B);
        if (exit && !GameConstants.OLD_ESCAPE_PRESSED){
            this.nextGameScreen = new MenuScreen();
            this.screenDone = true;
        }

        if (p1Enter && !GameConstants.OLD_P1_ENTER_PRESSED) {
            this.nextGameScreen = new LoadingScreen("com.sadc.game.screen.LevelSelectScreen");
            this.screenDone = true;
        }
        GameConstants.OLD_P1_ENTER_PRESSED = p1Enter;
        GameConstants.OLD_ESCAPE_PRESSED = exit;
    }

    @Override
    public void draw(float delta) {
        spriteBatch.begin();
        spriteBatch.draw(sky, 0, 0);
        font.draw(spriteBatch, "Select your car", 230, 400);
        car1Animator.draw(spriteBatch, 150, 200);
        spriteBatch.draw(selectBox, 140, 190);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        font.dispose();
        sky.dispose();
    }
}
