package com.sadc.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.sadc.game.GameConstants;
import com.sadc.game.gameobject.GameUtils;
import com.sadc.game.gameobject.LeaderboardListing;

import java.util.ArrayList;

public class FinishScreen extends GameScreen {
    private String successText = "Quite Good!";
    private String failText = "Bollocks, you are just awful.";
    private String recordText = "Jolly good! You set a record! Enter your initials.";
    private Texture chav1;
    private Texture happyFamily;
    private String initials = "";
    private String finishTimeString;
    private long finishTime;
    private Texture sky;
    private boolean recordSet = false;
    private String textToDisplay;
    private BitmapFont font;
    private final FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(GameConstants.LONDON_FONT));
    private SpriteBatch spriteBatch = new SpriteBatch();
    private int currentLetterPosition;
    private String currentSelectedLetter = GameConstants.INITIAL_CHARACTER_ARRAY[0];
    private int numberOfSelectedLetters = 0;
    private String levelName;
    private int levelNumber;
    private int recordPlacement;
    private String[] initialsArray = {"", "", ""};
    private ArrayList<LeaderboardListing> leaderboardListings;
    private boolean success = false;

    public FinishScreen(String finishTimeString, long finishTime, final boolean success, final String currentLevel, final int levelNumber){
        this.levelNumber = levelNumber;
        chav1 = new Texture("chav.jpg");
        happyFamily = new Texture("happy.png");
        sky = new Texture("datNightSky.png");
        levelName = currentLevel;
        this.success = success;
        leaderboardListings = GameUtils.getLeaderBoardListing(currentLevel + ".txt");
        if (success){
            int i = 0;
            for (LeaderboardListing leaderboardListing: leaderboardListings){
                if (finishTime < leaderboardListing.getFinishTime()){
                    recordSet = true;
                    recordPlacement = i;
                    break;
                }
                i++;
            }

        }
        this.finishTimeString = finishTimeString;
        this.finishTime = finishTime;
        font = generator.generateFont(GameConstants.MENU_FONT_SIZE);
        if (!success){
            textToDisplay = failText;
        } else if (recordSet) {
            textToDisplay = recordText;
        } else {
            textToDisplay = successText;
        }

    }
    @Override
    public void update(final float delta) {
        if (recordSet){
            boolean p1Left = Gdx.input.isKeyPressed(GameConstants.P1_LEFT);
            boolean p1Right = Gdx.input.isKeyPressed(GameConstants.P1_RIGHT);
            boolean p1Enter = Gdx.input.isKeyPressed(GameConstants.P1_B);
            if (p1Left && !GameConstants.OLD_P1_LEFT_PRESSED){
                if (currentLetterPosition > 0){
                    currentLetterPosition = currentLetterPosition - 1;
                } else {
                    currentLetterPosition = GameConstants.INITIAL_CHARACTER_ARRAY.length - 1;
                }
                currentSelectedLetter = GameConstants.INITIAL_CHARACTER_ARRAY[currentLetterPosition];
            } else if (p1Right && !GameConstants.OLD_P1_RIGHT_PRESSED){
                if (currentLetterPosition < GameConstants.INITIAL_CHARACTER_ARRAY.length - 1){
                    currentLetterPosition = currentLetterPosition + 1;
                } else {
                    currentLetterPosition = 0;
                }
                currentSelectedLetter = GameConstants.INITIAL_CHARACTER_ARRAY[currentLetterPosition];
            } else if (p1Enter && !GameConstants.OLD_P1_ENTER_PRESSED) {
                initialsArray[numberOfSelectedLetters] = currentSelectedLetter;
                if (numberOfSelectedLetters < 2){
                    numberOfSelectedLetters++;
                } else {
                    GameUtils.writeNewScoresToLeaderboard(levelName + ".txt", recordPlacement, initialsArray[0]
                            + initialsArray[1] + initialsArray[2], finishTime, finishTimeString, leaderboardListings);
                    nextGameScreen = new LeaderboardScreen(levelName, levelNumber);
                    screenDone = true;
                }
            }
            GameConstants.OLD_P1_LEFT_PRESSED = Gdx.input.isKeyPressed(GameConstants.P1_LEFT);
            GameConstants.OLD_P1_RIGHT_PRESSED = Gdx.input.isKeyPressed(GameConstants.P1_RIGHT);
            GameConstants.OLD_P1_ENTER_PRESSED = Gdx.input.isKeyPressed(GameConstants.P1_B);
        } else {

            boolean exit = Gdx.input.isKeyPressed(GameConstants.ESCAPE_KEY);
            boolean p1Enter = Gdx.input.isKeyPressed(GameConstants.P1_B);
            if ((exit && !GameConstants.OLD_ESCAPE_PRESSED) || (p1Enter && !GameConstants.OLD_P1_ENTER_PRESSED)) {
                this.nextGameScreen = new MenuScreen();
                this.screenDone = true;
            }
            GameConstants.OLD_P1_ENTER_PRESSED = p1Enter;
            GameConstants.OLD_ESCAPE_PRESSED = exit;
        }

    }

    @Override
    public void draw(float delta) {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.draw(sky, 0, 0);
        font.draw(spriteBatch, textToDisplay, 60, 400);
        if (this.success){
            font.draw(spriteBatch, "Total Time: "+ finishTimeString, 60, 375);
            spriteBatch.draw(happyFamily, 400, 0);
        } else {
            spriteBatch.draw(chav1, 400, 40);
        }
        if (recordSet){

            initialsArray[numberOfSelectedLetters] = currentSelectedLetter;
            font.draw(spriteBatch, initialsArray[0], 250, 300);
            font.draw(spriteBatch, initialsArray[1], 265, 300);
            font.draw(spriteBatch, initialsArray[2], 280, 300);
        }
        spriteBatch.end();
    }

    @Override
    public void dispose(){
        font.dispose();
        generator.dispose();
        chav1.dispose();
        happyFamily.dispose();
    }

}
