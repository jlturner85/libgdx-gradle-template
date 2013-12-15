package com.sadc.game.animation;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by h526717 on 12/13/13.
 */
public class Animator implements ApplicationListener {

    private static final float ALMOST_ZERO = 0.00000001f;

    private int        FRAME_COLS;// = 6;         // #1
    private int        FRAME_ROWS;// = 5;         // #2

    Animation                       walkAnimation;          // #3
    Texture                         walkSheet;              // #4
    TextureRegion[]                 walkFrames;             // #5
    SpriteBatch                     spriteBatch;            // #6
    TextureRegion                   currentFrame;           // #7

    float stateTime;                                        // #8

    float frameDuration;
    float x;
    float y;
    float originX;
    float originY;
    float width;
    float height;
    float scaleX;
    float scaleY;
    float rotation;
//    int srcX;
//    int srcY;
//    int srcWidth; int srcHeight;
//    boolean flipX;
//    boolean flipY;

    // The first parameter is the frame time and the second is an array of regions (frames) making up the animation
    public Animator (Texture texture, int frameColumns, int frameRows, float frameDuration){//, float frameDuration, TextureRegion... keyFrames){
        walkSheet = texture;
        this.FRAME_COLS = frameColumns;
        this.FRAME_ROWS = frameRows;
        this.frameDuration = frameDuration;
        create();
    }

    @Override
    public void create() {
//        walkSheet = new Texture("animation_sheet.png");     // #9
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() /
                FRAME_COLS, walkSheet.getHeight() / FRAME_ROWS);                                // #10
        walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }
        walkAnimation = new Animation(frameDuration, walkFrames);              // #11
//        spriteBatch = new SpriteBatch();                                // #12
        stateTime = 0f;                                                 // #13
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
//        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);                                            // #14
        stateTime += Gdx.graphics.getDeltaTime();                       // #15
        currentFrame = walkAnimation.getKeyFrame(stateTime, true);      // #16
//        spriteBatch.begin();
        if (rotation == 0){
            spriteBatch.draw(currentFrame, x, y);
        } else {
            spriteBatch.draw(currentFrame, x, y, originX, originY, width, height, scaleX, scaleY, rotation);                         // #17
        }
//        spriteBatch.end();

    }

    public void draw(
            SpriteBatch spriteBatch,
            float x, float y,
            float originX, float originY,
            float width, float height,
            float scaleX, float scaleY,
            float rotation
//            int srcX, int srcY,
//            int srcWidth, int srcHeight,
//            boolean flipX, boolean flipY
){
        this.spriteBatch = spriteBatch;
        this.x = x;
        this.y = y;
        this.originX = originX;
        this.originY = originY;
        this.width = width;
        this.height = height;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.rotation = rotation;
//        this.srcX = srcX;
//        this.srcY = srcY;
//        this.srcWidth = srcWidth;
//        this.srcHeight = srcHeight;
//        this.flipX = flipX;
//        this.flipY = flipY;
        // call render
        render();
    }

    public void draw(
            SpriteBatch spriteBatch,
            float x, float y) {
        this.spriteBatch = spriteBatch;
        this.x = x;
        this.y = y;
        render();
    }


    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
