package com.sadc.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.sadc.game.GameConstants;
import com.sadc.game.animation.Animator;

public class CarSelectScreen extends GameScreen {
    private SpriteBatch spriteBatch = new SpriteBatch();
    private BitmapFont font;
    private Texture sky;
    private Texture car1= new Texture("car1_4frame.png");
    private Texture car2 = new Texture("car2_4frame.png");
    private Texture car3 = new Texture("car3_4frame.png");
    private Texture car4 = new Texture("car4_4frame.png");
    private Texture car5 = new Texture("car5_4frame.png");
    private Texture tardis = new Texture("tardis_4frame.png");
    private Texture selectBox;
    private Animator car1Animator;
    private Animator car2Animator;
    private Animator car3Animator;
    private Animator car4Animator;
    private Animator car5Animator;
    private Animator tardisAnimator;
    private int currentLevelPosition = 0;
    private float selectX;
    private float selectY = 190;
    private boolean tardisCheat = false;
    private final FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(GameConstants.LONDON_FONT));
    public CarSelectScreen(){
        font = generator.generateFont(GameConstants.MENU_FONT_SIZE);
        sky = new Texture("datNightSky.png");
        selectBox = new Texture("selectBox.png");
        car1Animator = new Animator(car1, GameConstants.CAR1_SPRITE_COLUMNS, GameConstants.CAR1_SPRITE_ROWS, GameConstants.CAR1_FRAME_DURATION);
        car2Animator = new Animator(car2, GameConstants.CAR1_SPRITE_COLUMNS, GameConstants.CAR1_SPRITE_ROWS, GameConstants.CAR1_FRAME_DURATION);
        car3Animator = new Animator(car3, GameConstants.CAR1_SPRITE_COLUMNS, GameConstants.CAR1_SPRITE_ROWS, GameConstants.CAR1_FRAME_DURATION);
        car4Animator = new Animator(car4, GameConstants.CAR1_SPRITE_COLUMNS, GameConstants.CAR1_SPRITE_ROWS, GameConstants.CAR1_FRAME_DURATION);
        car5Animator = new Animator(car5, GameConstants.CAR1_SPRITE_COLUMNS, GameConstants.CAR1_SPRITE_ROWS, GameConstants.CAR1_FRAME_DURATION);
        tardisAnimator = new Animator(tardis, GameConstants.CAR1_SPRITE_COLUMNS, GameConstants.CAR1_SPRITE_ROWS, GameConstants.CAR1_FRAME_DURATION);
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

        if (Gdx.input.isKeyPressed(GameConstants.P1_X) && Gdx.input.isKeyPressed(GameConstants.P1_DOWN)){
            tardisCheat = true;
        }
        if (p1Enter && !GameConstants.OLD_P1_ENTER_PRESSED) {
            if (tardisCheat){
                GameConstants.CURRENT_SELECTED_CAR = 5;
            } else {
                GameConstants.CURRENT_SELECTED_CAR = currentLevelPosition;
            }
            this.nextGameScreen = new LoadingScreen("com.sadc.game.screen.LevelSelectScreen");
            this.screenDone = true;
        }

        if (p1Left && !GameConstants.OLD_P1_LEFT_PRESSED){
            if (currentLevelPosition > 0){
                currentLevelPosition = currentLevelPosition - 1;
            } else {
                currentLevelPosition = 4;
            }

        } else if (p1Right && !GameConstants.OLD_P1_RIGHT_PRESSED){
            if (currentLevelPosition < 4){
                currentLevelPosition = currentLevelPosition + 1;
            } else {
                currentLevelPosition = 0;
            }
        }
        switch(currentLevelPosition){
            case 0:
                selectX = 115;
                break;
            case 1:
                selectX = 205;
                break;
            case 2:
                selectX = 295;
                break;
            case 3:
                selectX = 385;
                break;
            case 4:
                selectX = 475;
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
        font.draw(spriteBatch, "Select your car", 230, 400);
        car1Animator.draw(spriteBatch, 125, 200);
        car2Animator.draw(spriteBatch, 215, 200);
        car3Animator.draw(spriteBatch, 305, 200);
        car4Animator.draw(spriteBatch, 395, 200);
        car5Animator.draw(spriteBatch, 485, 200);

        if (tardisCheat) {
            spriteBatch.draw(selectBox, 320, 50);
            tardisAnimator.draw(spriteBatch, 330, 40);
        } else {
            spriteBatch.draw(selectBox, selectX, selectY);
        }
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        font.dispose();
        sky.dispose();
    }
}
