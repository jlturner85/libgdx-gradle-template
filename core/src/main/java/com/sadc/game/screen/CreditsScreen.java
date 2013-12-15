package com.sadc.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.sadc.game.GameConstants;

public class CreditsScreen extends GameScreen {
    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    private final SpriteBatch spriteBatch;
    private final BitmapFont creditFont;
    private final BitmapFont titleFont;
    private final FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(GameConstants.LONDON_FONT));
    public CreditsScreen(){
        spriteBatch = new SpriteBatch();
        titleFont = generator.generateFont(20);
        creditFont = generator.generateFont(15);
    }
    @Override
    public void update(float delta) {
        boolean exit = Gdx.input.isKeyPressed(GameConstants.ESCAPE_KEY);
        boolean p1Enter = Gdx.input.isKeyPressed(GameConstants.P1_B);
        boolean p2Enter = Gdx.input.isKeyPressed(GameConstants.P2_B);
        if ((exit && !GameConstants.OLD_ESCAPE_PRESSED) || (p1Enter && !GameConstants.OLD_P1_ENTER_PRESSED)
                || (p2Enter && !GameConstants.OLD_P2_ENTER_PRESSED)) {
            this.nextGameScreen = new MenuScreen();
            this.screenDone = true;
        }
        GameConstants.OLD_P1_ENTER_PRESSED = p1Enter;
        GameConstants.OLD_P2_ENTER_PRESSED = p2Enter;
        GameConstants.OLD_ESCAPE_PRESSED = exit;
    }

    @Override
    public void draw(float delta) {
        float creditXPosition = 30;
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        String creditsText = "Another Year of Taking the Biscuit...";
        titleFont.draw(spriteBatch, creditsText, 60, 400);
        float creditHeight = 325;

        creditFont.draw(spriteBatch, "Fernando Mosqueda - Artist", creditXPosition, creditHeight);
        creditFont.draw(spriteBatch, "Gerald Halbeisen - Artist/Programmer", creditXPosition, creditHeight - 35);
        creditFont.draw(spriteBatch, "Justin Turner - Programmer", creditXPosition, creditHeight - 70);
        creditFont.draw(spriteBatch, "Tom Farello - Programmer", creditXPosition, creditHeight - 105);
        creditFont.draw(spriteBatch, "Danny Carroll, Darren Danvers - Game Jam Theme", creditXPosition, creditHeight - 140);
        creditFont.draw(spriteBatch, "Perturbator - Music", creditXPosition, creditHeight - 175);
        creditFont.draw(spriteBatch, "Game Jam Theme - London Underground", creditXPosition, creditHeight - 210);
        creditFont.draw(spriteBatch, "SADC Game Jam 2013 12/13/13-12/15/13", creditXPosition, creditHeight - 245);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        creditFont.dispose();
        titleFont.dispose();
        generator.dispose();
    }
}
