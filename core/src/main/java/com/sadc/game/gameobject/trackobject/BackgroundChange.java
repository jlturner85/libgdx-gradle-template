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
import com.sadc.game.gameobject.Player;

/**
 * @author f536985 (Tom Farello)
 */
public class BackgroundChange extends TrackObject {

    public BackgroundChange(float distance, int side) {
        setActive(true);
        setDistance(distance);
        setAngle(0);
        setWidth(180);
        if (side == 1) {
            setTexture(new Texture("datNightSky.png"));
        }
    }

    @Override
    public void update(float delta, Player player) {
        if (collide(player)) {
            setActive(false);
            player.changeBackground(getTexture());
        }
    }

    @Override
    public void draw(float delta, float playerDistance, SpriteBatch spriteBatch) { }

}
