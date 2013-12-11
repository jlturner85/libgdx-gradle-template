package com.sadc.game.screen;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class MenuScreen  extends GameScreen{
    private final SpriteBatch spriteBatch;
    private final Texture background;
    private final FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Minecraftia.ttf"));
    private final BitmapFont font;
    public MenuScreen(){
        spriteBatch = new SpriteBatch();
        background = new Texture("badlogic.jpg");
        font = generator.generateFont(12);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
        //spriteBatch.disableBlending();
        //spriteBatch.setColor(Color.WHITE);
        //spriteBatch.draw(background, 0, 0, 480, 320, 0, 0, 512, 512, false, false);
        //spriteBatch.enableBlending();
        //spriteBatch.draw(logo, 0, 320 - 128, 480, 128, 0, 0, 512, 256, false, false);
        //spriteBatch.setBlendFunction(GL10.GL_ONE, GL10.GL_ONE_MINUS_SRC_ALPHA);
        spriteBatch.draw(background, 0, 0);
        String text = "Touch screen to start!";
        float width = font.getBounds(text).width;
        font.draw(spriteBatch, text, 240 - width / 2, 128);
        if (Gdx.app.getType() == Application.ApplicationType.WebGL) {
            text = "Testing";
            width = font.getBounds(text).width;
            font.draw(spriteBatch, "Press Enter for Fullscreen Mode", 240 - width / 2, 128 - font.getLineHeight());
        }
        spriteBatch.end();
    }
    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {
        spriteBatch.dispose();
        font.dispose();
        background.dispose();
        generator.dispose();
    }
}
