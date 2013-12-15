package com.sadc.game.screen;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.sadc.game.GameConstants;

public class MenuScreen  extends GameScreen{
    private final SpriteBatch spriteBatch;
    private final FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(GameConstants.LONDON_FONT));
    private final FreeTypeFontGenerator titleGenerator = new FreeTypeFontGenerator(Gdx.files.internal(GameConstants.LONDON_FONT));
    private final BitmapFont titleFont;
    private final BitmapFont startGameFont;
    private final BitmapFont creditsFont;
    private final BitmapFont exitFont;
    private final BitmapFont leaderboardFont;
    private static final int START_SELECTED = 1;
    private static final int CREDITS_SELECTED = 2;
    private static final int LEADERBOARD_SELECTED = 3;
    private static final int EXIT_SELECTED = 4;
    private int selectedMenuItem;
    private final String titleText = "Hard Lines in The Tube";
    private final String startGameText = "Start Game";
    private final String creditsText = "Credits";
    private final String exitText = "Exit";
    private final String leaderboardText = "Leaderboard";
    private boolean oldP1Up = false;
    private boolean oldP1Down = false;
    private boolean oldP2Up = false;
    private boolean oldP2Down = false;
    private Texture lightPole;
    private Texture skyLine;
    private Texture sky;
    private Texture moon;
    private Texture cloud1;
    private Texture cloud2;
    private Texture cloud3;
    private float moonX=500;
    private float moonY=210;
    private float frontCloud1X = 700;
    private float backCloud3X = 500;
    private float frontCloud2X = 300;
    private float backCloud1X = 100;
    private float frontCloud3X = -100;
    private float backCloud2X = -300;



    private float cloudY = 75;
    public MenuScreen(){
        spriteBatch = new SpriteBatch();
        lightPole = new Texture("lightPole.png");
        skyLine = new Texture("slyline.png");
        sky = new Texture("datNightSky.png");
        moon = new Texture("alotGlowMoon.png");
        cloud1 = new Texture("cloud1.png");
        cloud2 = new Texture("cloud2.png");
        cloud3 = new Texture("cloud3.png");
        //the number passed to the generator is the size of the text
        startGameFont = generator.generateFont(GameConstants.MENU_FONT_SIZE);
        creditsFont = generator.generateFont(GameConstants.MENU_FONT_SIZE);
        exitFont = generator.generateFont(GameConstants.MENU_FONT_SIZE);
        leaderboardFont = generator.generateFont(GameConstants.MENU_FONT_SIZE);
        titleFont = titleGenerator.generateFont(50);
        titleFont.setColor(Color.BLACK);
        startGameFont.setColor(Color.BLACK);
        selectedMenuItem = START_SELECTED;
    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {
        spriteBatch.dispose();
        startGameFont.dispose();
        creditsFont.dispose();
        leaderboardFont.dispose();
        exitFont.dispose();
        sky.dispose();
        skyLine.dispose();
        lightPole.dispose();
        moon.dispose();
        generator.dispose();
        titleFont.dispose();
    }

    @Override
    public void update(float delta){
        boolean p1Up = Gdx.input.isKeyPressed(GameConstants.P1_UP);
        boolean p1Down = (Gdx.input.isKeyPressed(GameConstants.P1_DOWN));
        boolean p2Up =  Gdx.input.isKeyPressed(GameConstants.P2_UP);
        boolean p2Down = Gdx.input.isKeyPressed(GameConstants.P2_DOWN);
        boolean p1Enter = Gdx.input.isKeyPressed(GameConstants.P1_B);
        boolean p2Enter = Gdx.input.isKeyPressed(GameConstants.P2_B);
        boolean exit = Gdx.input.isKeyPressed(GameConstants.ESCAPE_KEY);
        float cloudSpeed = .2f;
        float reset = -375f;
        float moonSpeed = .03f;
        if(moonY<325){
            moonX= moonX - moonSpeed;
            moonY= moonY + moonSpeed;
        }
        float overLap = 800f;

        backCloud1X = checkCloudOverlap(backCloud1X, overLap, reset, cloudSpeed);
        backCloud2X = checkCloudOverlap(backCloud2X, overLap, reset, cloudSpeed);
        backCloud3X = checkCloudOverlap(backCloud3X, overLap, reset, cloudSpeed);
        frontCloud1X = checkCloudOverlap(frontCloud1X, overLap, reset, cloudSpeed);
        frontCloud2X = checkCloudOverlap(frontCloud2X, overLap, reset, cloudSpeed);
        frontCloud3X = checkCloudOverlap(frontCloud3X, overLap, reset, cloudSpeed);
        if (exit){
            Gdx.app.exit();
        } else if (p1Up || p1Down || p2Up || p2Down || p1Enter || p2Enter){
            switch(selectedMenuItem){
                case START_SELECTED:
                    if ((p1Up && !oldP1Up) || (p2Up && !oldP2Up)){
                        exitHighlighted();
                    } else if ((p1Down && !oldP1Down) || (p2Down && !oldP2Down)) {
                        leaderboardHighlighted();
                    } else if ((p1Enter && !GameConstants.OLD_P1_ENTER_PRESSED) || (p2Enter && !GameConstants.OLD_P2_ENTER_PRESSED)) {
                        this.nextGameScreen = new LoadingScreen("com.sadc.game.screen.CarSelectScreen");
                        this.screenDone = true;
                    }
                    break;
                case LEADERBOARD_SELECTED:
                    if ((p1Up && !oldP1Up) || (p2Up && !oldP2Up)){
                        startGameHighlighted();
                    } else if ((p1Down && !oldP1Down) || (p2Down && !oldP2Down)) {
                        creditsHighlighted();
                    } else if ((p1Enter && !GameConstants.OLD_P1_ENTER_PRESSED) || (p2Enter && !GameConstants.OLD_P2_ENTER_PRESSED)) {
                        this.nextGameScreen = new LeaderboardScreen("Liverpool St.", 0);
                        this.screenDone = true;
                    }
                    break;
                case CREDITS_SELECTED:
                    if ((p1Up && !oldP1Up) || (p2Up && !oldP2Up)){
                        leaderboardHighlighted();
                    } else if ((p1Down && !oldP1Down) || (p2Down && !oldP2Down)) {
                        exitHighlighted();
                    } else if ((p1Enter && !GameConstants.OLD_P1_ENTER_PRESSED) || (p2Enter && !GameConstants.OLD_P2_ENTER_PRESSED)) {
                        //show the credits screen
                        this.nextGameScreen = new CreditsScreen();
                        this.screenDone = true;
                    }
                    break;
                case EXIT_SELECTED:
                    if ((p1Up && !oldP1Up) || (p2Up && !oldP2Up)){
                        creditsHighlighted();
                    } else if ((p1Down && !oldP1Down) || (p2Down && !oldP2Down)) {
                        startGameHighlighted();
                    } else if ((p1Enter && !GameConstants.OLD_P1_ENTER_PRESSED) || (p2Enter && !GameConstants.OLD_P2_ENTER_PRESSED)) {
                      Gdx.app.exit();
                    }
                    break;
            }
        }
        oldP1Up = Gdx.input.isKeyPressed(GameConstants.P1_UP);
        oldP1Down = Gdx.input.isKeyPressed(GameConstants.P1_DOWN);
        oldP2Up = Gdx.input.isKeyPressed(GameConstants.P2_UP);
        oldP2Down = Gdx.input.isKeyPressed(GameConstants.P2_DOWN);
        GameConstants.OLD_P1_ENTER_PRESSED = p1Enter;
        GameConstants.OLD_P2_ENTER_PRESSED = p2Enter;
    }

    @Override
    public void draw(float delta){
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.draw(sky, 0, 0);
        spriteBatch.draw(cloud1, backCloud1X, cloudY);
        spriteBatch.draw(cloud2, backCloud2X, cloudY);
        spriteBatch.draw(cloud3, backCloud3X, cloudY);
        spriteBatch.draw(moon, moonX, moonY);
        spriteBatch.draw(skyLine, 100, 0);
        spriteBatch.draw(cloud1, frontCloud1X, cloudY);
        spriteBatch.draw(cloud2, frontCloud2X, cloudY);
        spriteBatch.draw(cloud3, frontCloud3X, cloudY);
        float width = startGameFont.getBounds(creditsText).width;
        //to change a font's color: font.setColor(Color.BLUE);
        //to change spritebatch's color: spriteBatch.setColor(Color.BLUE);
        titleFont.draw(spriteBatch, titleText, 50, 400);
        startGameFont.draw(spriteBatch, startGameText, 300 - width / 2, 300);
        leaderboardFont.draw(spriteBatch, leaderboardText, 290 - width / 2, 280);
        creditsFont.draw(spriteBatch, creditsText, 312 - width / 2, 260);
        exitFont.draw(spriteBatch, exitText, 322 - width / 2, 240);
        spriteBatch.end();
    }

    private void startGameHighlighted(){
        creditsFont.setColor(Color.WHITE);
        exitFont.setColor(Color.WHITE);
        leaderboardFont.setColor(Color.WHITE);
        startGameFont.setColor(Color.BLACK);
        selectedMenuItem = START_SELECTED;
    }

    private void creditsHighlighted(){
        startGameFont.setColor(Color.WHITE);
        exitFont.setColor(Color.WHITE);
        leaderboardFont.setColor(Color.WHITE);
        creditsFont.setColor(Color.BLACK);
        selectedMenuItem = CREDITS_SELECTED;
    }

    private void exitHighlighted(){
        startGameFont.setColor(Color.WHITE);
        creditsFont.setColor(Color.WHITE);
        leaderboardFont.setColor(Color.WHITE);
        exitFont.setColor(Color.BLACK);
        selectedMenuItem = EXIT_SELECTED;
    }

    private void leaderboardHighlighted(){
        startGameFont.setColor(Color.WHITE);
        creditsFont.setColor(Color.WHITE);
        exitFont.setColor(Color.WHITE);
        leaderboardFont.setColor(Color.BLACK);
        selectedMenuItem = LEADERBOARD_SELECTED;
    }

    private float checkCloudOverlap(float x, float overlap, float reset, float cloudSpeed){
        if(x>overlap){
            return reset;
        } else {
            x += cloudSpeed;
            return x;
        }
    }
}
