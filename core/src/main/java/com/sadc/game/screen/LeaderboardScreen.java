package com.sadc.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
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
    private final BitmapFont titleFont;
    private final SpriteBatch spriteBatch;
    private final float height = 350;
    private final Texture chavPicture;
    private final Texture chav2Picture;
    private List<LeaderboardListing> leaderboardList = new ArrayList<LeaderboardListing>();
    public LeaderboardScreen(){
        chavPicture = new Texture("chav.jpg");
        chav2Picture = new Texture("chav2.jpg");
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
        generator = new FreeTypeFontGenerator(Gdx.files.internal(GameConstants.LONDON_FONT));
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
        titleFont.draw(spriteBatch, "Top Chavs", 250, 400);
        float leaderboardHeight = height;
        for (LeaderboardListing leaderboardListing: leaderboardList){
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
