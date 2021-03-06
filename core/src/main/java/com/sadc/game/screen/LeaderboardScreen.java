package com.sadc.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.sadc.game.GameConstants;
import com.sadc.game.gameobject.GameUtils;
import com.sadc.game.gameobject.LeaderboardListing;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardScreen extends GameScreen {

    private final FreeTypeFontGenerator generator;
    private final BitmapFont font;
    private final BitmapFont titleFont;
    private final SpriteBatch spriteBatch;
    private final float height = 350;
    private final Texture chavPicture;
    private final Texture chav2Picture;
    private ArrayList[] leaderboardListArray = new ArrayList[GameConstants.NUMBER_OF_TRACKS];
    private String [] trackNameArray = new String[GameConstants.NUMBER_OF_TRACKS];
    private ArrayList<LeaderboardListing> currentLeaderBoardList;
    private String currentTrackName;
    private int currentLevelPosition = 0;
    public LeaderboardScreen(final String levelName, int levelNumber){
        chavPicture = new Texture("chav.jpg");
        chav2Picture = new Texture("chav2.jpg");
        trackNameArray[0] = GameConstants.TRACK_1_NAME;
        trackNameArray[1] = GameConstants.TRACK_2_NAME;
        trackNameArray[2] = GameConstants.TRACK_3_NAME;
        leaderboardListArray[0]= GameUtils.getLeaderBoardListing(GameConstants.TRACK_1_LEADERBOARD);
        leaderboardListArray[1]= GameUtils.getLeaderBoardListing(GameConstants.TRACK_2_LEADERBOARD);
        leaderboardListArray[2]= GameUtils.getLeaderBoardListing(GameConstants.TRACK_3_LEADERBOARD);
        currentLeaderBoardList = leaderboardListArray[levelNumber];
        currentTrackName = levelName;
        generator = new FreeTypeFontGenerator(Gdx.files.internal("Minecraftia.ttf"));
        titleFont = generator.generateFont(40);
        font = generator.generateFont(GameConstants.MENU_FONT_SIZE);
        spriteBatch = new SpriteBatch();
    }
    @Override
    public void draw(float delta) {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.draw(chavPicture, 80, 200, 150, 150);
        spriteBatch.draw(chav2Picture, 450, 185, 125, 175);
        titleFont.draw(spriteBatch, "Top Chavs", 200, 450);
        font.draw(spriteBatch, "<      " + currentTrackName + "      >", 190, 385);
        float leaderboardHeight = height;
        for (LeaderboardListing leaderboardListing: currentLeaderBoardList){
            font.draw(spriteBatch, leaderboardListing.getName(), 250, leaderboardHeight);
            font.draw(spriteBatch, leaderboardListing.getScore(), 350, leaderboardHeight);
            leaderboardHeight -= 20;
        }
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        font.dispose();
        generator.dispose();
    }

    @Override
    public void update(float delta) {
        boolean exit = Gdx.input.isKeyPressed(GameConstants.ESCAPE_KEY);
        boolean p1Enter = Gdx.input.isKeyPressed(GameConstants.P1_B);
        boolean p2Enter = Gdx.input.isKeyPressed(GameConstants.P2_B);
        boolean p1Left = Gdx.input.isKeyPressed(GameConstants.P1_LEFT);
        boolean p1Right = Gdx.input.isKeyPressed(GameConstants.P1_RIGHT);

        if ((exit && !GameConstants.OLD_ESCAPE_PRESSED) || (p1Enter && !GameConstants.OLD_P1_ENTER_PRESSED)
                || (p2Enter && !GameConstants.OLD_P2_ENTER_PRESSED)) {
            this.nextGameScreen = new MenuScreen();
            this.screenDone = true;
        }

        if (p1Left && !GameConstants.OLD_P1_LEFT_PRESSED){
            if (currentLevelPosition > 0){
                currentLevelPosition = currentLevelPosition - 1;
            } else {
                currentLevelPosition = leaderboardListArray.length - 1;
            }
            currentLeaderBoardList = leaderboardListArray[currentLevelPosition];
            currentTrackName = trackNameArray[currentLevelPosition];
        } else if (p1Right && !GameConstants.OLD_P1_RIGHT_PRESSED){
            if (currentLevelPosition < leaderboardListArray.length - 1){
                currentLevelPosition = currentLevelPosition + 1;
            } else {
                currentLevelPosition = 0;
            }
            currentLeaderBoardList = leaderboardListArray[currentLevelPosition];
            currentTrackName = trackNameArray[currentLevelPosition];
        }
        GameConstants.OLD_P1_LEFT_PRESSED = Gdx.input.isKeyPressed(GameConstants.P1_LEFT);
        GameConstants.OLD_P1_RIGHT_PRESSED = Gdx.input.isKeyPressed(GameConstants.P1_RIGHT);
        GameConstants.OLD_P1_ENTER_PRESSED = p1Enter;
        GameConstants.OLD_P2_ENTER_PRESSED = p2Enter;
        GameConstants.OLD_ESCAPE_PRESSED = exit;
    }

}
