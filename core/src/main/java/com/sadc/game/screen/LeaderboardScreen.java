package com.sadc.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.sadc.game.GameConstants;
import com.sadc.game.gameobject.LeaderboardListing;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardScreen extends GameScreen {
    private FileHandle leaderboardFile;
    private final FreeTypeFontGenerator generator;
    private final BitmapFont font;
    private final SpriteBatch spriteBatch;
    private final float height = 400;
    private List<LeaderboardListing> leaderboardList = new ArrayList<LeaderboardListing>();
    public LeaderboardScreen(){
        leaderboardFile = Gdx.files.internal("leaderboard.txt");
        String text = leaderboardFile.readString();
        String[] leaderboardStrings = text.split(":");
        //create leaderboard object for each score, and place it in the correct order in the list
        for(int i = 0; i < leaderboardStrings.length; i++){
            String[] leaderboardItem = leaderboardStrings[i].split("-");
            LeaderboardListing leaderboardListing = new LeaderboardListing();
            leaderboardListing.setName(leaderboardItem[0]);
            leaderboardListing.setScore(leaderboardItem[1]);
            leaderboardList.add(i, leaderboardListing);
        }
        generator = new FreeTypeFontGenerator(Gdx.files.internal("Minecraftia.ttf"));
        font = generator.generateFont(12);
        spriteBatch = new SpriteBatch();
    }
    @Override
    public void draw(float delta) {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        float leaderboardHeight = height;
        for (LeaderboardListing leaderboardListing: leaderboardList){
            font.draw(spriteBatch, leaderboardListing.getName(), 100, leaderboardHeight);
            font.draw(spriteBatch, leaderboardListing.getScore(), 200, leaderboardHeight);
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
        if (exit){
            Gdx.app.exit();
        }
        if ((p1Enter && !GameConstants.OLD_P1_ENTER_PRESSED) || (p2Enter && !GameConstants.OLD_P2_ENTER_PRESSED)) {
            this.nextGameScreen = new MenuScreen();
            this.screenDone = true;
        }
        GameConstants.OLD_P1_ENTER_PRESSED = p1Enter;
        GameConstants.OLD_P2_ENTER_PRESSED = p2Enter;
    }

}
