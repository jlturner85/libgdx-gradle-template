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
    private final BitmapFont font;
    private final FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Minecraftia.ttf"));
    public CreditsScreen(){
        spriteBatch = new SpriteBatch();
        font = generator.generateFont(12);
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

    @Override
    public void draw(float delta) {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        String creditsText = "Credits";
        float width = font.getBounds(creditsText).width;
        font.draw(spriteBatch, creditsText, 312 - width / 2, 110);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        font.dispose();
        generator.dispose();
    }
}
