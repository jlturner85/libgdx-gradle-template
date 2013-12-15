/*
 * $Id$
 *
 * Copyright (c) 2013 HEB
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of HEB.
 */
package com.sadc.game.gameobject.trackobject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sadc.game.GameConstants;
import com.sadc.game.gameobject.Player;
import com.sadc.game.util.GameUtil;

/**
 * @author f536985 (Tom Farello)
 */
public class Train extends TrackObject {

    private int timeout;
    private int numCars;
    private Sound trainSound = Gdx.audio.newSound(Gdx.files.internal("soundeffects/train-pass-by-01.mp3"));
    private Sound trainWhistle = Gdx.audio.newSound(Gdx.files.internal("soundeffects/train-whistle-01.mp3"));
    private Texture frontTexture;
    private Texture insideTexture;
    private Texture middleTexture;
    private Texture lightsTexture;

    public Train(float distance, int numCars) {
        setActive(true);
        setDistance(distance);
        setAngle(0);
        setWidth(100);

        this.numCars = numCars;
        frontTexture = new Texture("kickAssTrain.png");
        insideTexture = new Texture("kickaAssInsideTrain.png");
        middleTexture = new Texture("kickAssMiddleOfTrain.png");
        lightsTexture = new Texture("trainheadlights.png");
    }

    @Override
    public void dispose() {
        frontTexture.dispose();
        insideTexture.dispose();
        middleTexture.dispose();
    }

    @Override
    public void update(float delta, Player player) {
        setDistance(getDistance() - 1f / 120f);
        if (collide(player)) {
            player.crash();
            setActive(false);
            timeout = 240;
        }
    }

    @Override
    public void draw(float delta, float playerDistance, SpriteBatch spriteBatch) {
        if (timeout > 0) {
            timeout--;
        } else {
            setActive(true);
        }
        for (int i = numCars - 1; i >= 0; i--) {
            float distance = getDistance() + 2 * i;
            float drawDistance = (float)Math.pow(2 , playerDistance - (distance + 4f / 3));
            GameUtil.setColorByDrawDistance(drawDistance, spriteBatch);
            if (drawDistance <= 1) {
                spriteBatch.draw(insideTexture, GameConstants.SCREEN_WIDTH / 2 - 175, 15,
                        175, GameConstants.SCREEN_HEIGHT / 2 - 15, 350, 350, drawDistance, drawDistance, getAngle(), 0, 0, 350, 350, false, false);
            }
            drawDistance = (float)Math.pow(2 , playerDistance - distance);
            GameUtil.setColorByDrawDistance(drawDistance, spriteBatch);
            if (drawDistance <= 1) {
                spriteBatch.draw(i == 0 ? frontTexture : middleTexture, GameConstants.SCREEN_WIDTH / 2 - 175, 15,
                        175, GameConstants.SCREEN_HEIGHT / 2 - 15, 350, 350, drawDistance, drawDistance, getAngle(), 0, 0, 350, 350, false, false);
            }
            if (i == 0) {
                GameUtil.setColorByDrawDistance(1, spriteBatch);
                if (drawDistance <= 1) {
                    spriteBatch.draw(lightsTexture, GameConstants.SCREEN_WIDTH / 2 - 175, 15,
                            175, GameConstants.SCREEN_HEIGHT / 2 - 15, 350, 350, drawDistance, drawDistance, getAngle(), 0, 0, 350, 350, false, false);
                }
            }
        }

    }
}
