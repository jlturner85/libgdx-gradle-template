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

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sadc.game.GameConstants;
import com.sadc.game.gameobject.GameUtils;
import com.sadc.game.gameobject.Player;

/**
 * @author f536985 (Tom Farello)
 */
public class Checkpoint extends TrackObject {

    private boolean finishLine;
    private int bonusSeconds;

    private Texture check1;
    private Texture check2;

    public Checkpoint(float distance) {
        finishLine = true;
        bonusSeconds = 0;
        setActive(true);
        setDistance(distance);
        setAngle(0);
        setWidth(180);
        check1 = new Texture("finish1.png");
        check2 = new Texture("finish2.png");
    }

    public Checkpoint(float distance, int bonusSeconds) {
        finishLine = false;
        this.bonusSeconds = bonusSeconds;
        setActive(true);
        setDistance(distance);
        setAngle(0);
        setWidth(180);
        check1 = new Texture("check1.png");
        check2 = new Texture("check2.png");
    }

    @Override
    public void update(float delta, Player player) { }

    @Override
    public void draw(float delta, float playerDistance, SpriteBatch spriteBatch) {
        float scale = 1;
        for (int i = 0; i < 4; i++) {
            float drawDistance = ((float)Math.pow(2 , playerDistance - (getDistance())) * scale);
            GameUtils.setColorByDrawDistance(drawDistance, spriteBatch);
            spriteBatch.draw(i % 2 == 0 ? check1 : check2, GameConstants.SCREEN_WIDTH / 2 - 250, GameConstants.SCREEN_HEIGHT / 2 - 250,
                    250, 250, 500, 500, drawDistance, drawDistance, 0, 0, 0, 500, 500, false, false);
            scale *= 0.92f;
        }
    }
}
