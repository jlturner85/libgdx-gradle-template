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
import com.sadc.game.gameobject.GameUtils;
import com.sadc.game.gameobject.Player;
import com.sadc.game.gameobject.Racer;

/**
 * @author f536985 (Tom Farello)
 */
public class Train extends TrackObject {

    private float triggerDistance;
    private boolean triggered;

    private boolean trainSoundPlayed;
    private boolean trackWhistlePlayed;

    private int numCars;
    private Sound trainSound = Gdx.audio.newSound(Gdx.files.internal("soundeffects/train-pass-by-01.mp3"));
    private Sound trainWhistle = Gdx.audio.newSound(Gdx.files.internal("soundeffects/train-whistle-01.mp3"));
    private Sound explosionSound = Gdx.audio.newSound(Gdx.files.internal("soundeffects/explosion.wav"));
    private Texture frontTexture;
    private Texture insideTexture;
    private Texture middleTexture;
    private Texture lightsTexture;

    public Train(float distance, int numCars) {
        setActive(true);
        setDistance(distance);
        setAngle(0);
        setWidth(100);
        triggerDistance = distance - 10;

        this.numCars = numCars;
        frontTexture = new Texture("kickAssTrain.png");
        insideTexture = new Texture("kickaAssInsideTrain.png");
        middleTexture = new Texture("kickAssMiddleOfTrain.png");
        lightsTexture = new Texture("trainheadlights.png");
    }

    @Override
    public boolean collide(Player player) {
        float angleDiff = Math.abs(getAngle() - player.getAngle()) % 360;
        return isActive() && ((Math.abs(getDistance() - player.getDistance()) < (player.getSpeed() + 1) / 70f ||
                (player.getDistance() - getDistance() > 0 && player.getDistance() - getDistance() < 2 * numCars)) &&
                (angleDiff < getWidth() || angleDiff > 360 - getWidth()));
    }

    @Override
    public boolean collide(Racer racer) {
        float angleDiff = Math.abs(getAngle() - racer.getAngle()) % 360;
        return isActive() && ((Math.abs(getDistance() - racer.getDistance()) < (racer.getSpeed() + 1) / 70f ||
                (racer.getDistance() - getDistance() > 0 && racer.getDistance() - getDistance() < 2 * numCars)) &&
                (angleDiff < getWidth() || angleDiff > 360 - getWidth()));
    }

    @Override
    public void dispose() {
        frontTexture.dispose();
        insideTexture.dispose();
        middleTexture.dispose();
    }

    @Override
    public void update(float delta, Player player) {
        if (triggered) {
            float speed = Math.max(0, player.getSpeed() - 7);
            setDistance(getDistance() - (speed / 60f));
            if (collide(player)) {
                player.crash(240);
                setActive(false);
                trainSound.stop();
                trainWhistle.stop();
                explosionSound.play();
            }
        } else if (player.getDistance() >= triggerDistance) {
            triggered = true;
        }
    }

    @Override
    public void draw(float delta, float playerDistance, SpriteBatch spriteBatch) {
        if (isActive()) {
            for (int i = numCars - 1; i >= 0; i--) {
                float distance = getDistance() + 2 * i;
                float drawDistance = (float)Math.pow(2 , playerDistance - (distance + 4f / 3));
                GameUtils.setColorByDrawDistance(drawDistance, spriteBatch);
                if (drawDistance <= 1) {
                    spriteBatch.draw(insideTexture, GameConstants.SCREEN_WIDTH / 2 - 175, 15,
                            175, GameConstants.SCREEN_HEIGHT / 2 - 15, 350, 350, drawDistance, drawDistance, getAngle(), 0, 0, 350, 350, false, false);
                }
                drawDistance = (float)Math.pow(2 , playerDistance - distance);
                GameUtils.setColorByDrawDistance(drawDistance, spriteBatch);
                if (drawDistance <= 1) {
                    spriteBatch.draw(i == 0 ? frontTexture : middleTexture, GameConstants.SCREEN_WIDTH / 2 - 175, 15,
                            175, GameConstants.SCREEN_HEIGHT / 2 - 15, 350, 350, drawDistance, drawDistance, getAngle(), 0, 0, 350, 350, false, false);
                }
                if (i == 0) {
                    GameUtils.setColorByDrawDistance(1, spriteBatch);
                    if (drawDistance <= 1) {
                        spriteBatch.draw(lightsTexture, GameConstants.SCREEN_WIDTH / 2 - 175, 15,
                                175, GameConstants.SCREEN_HEIGHT / 2 - 15, 350, 350, drawDistance, drawDistance, getAngle(), 0, 0, 350, 350, false, false);
                    }
                }
            }
        }

        if (getDistance() - playerDistance < 12 && !trainSoundPlayed) {
            trainSoundPlayed = true;
            trainSound.play();
        }
        if (getDistance() - playerDistance < 2 && !trackWhistlePlayed) {
            trackWhistlePlayed = true;
            trainWhistle.play();
        }
        if (getDistance() - playerDistance < -2 * (numCars + 2)) {
            trainSound.stop();
        }
    }
}
