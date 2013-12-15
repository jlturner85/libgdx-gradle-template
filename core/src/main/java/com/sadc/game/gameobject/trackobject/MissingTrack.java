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

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sadc.game.gameobject.Player;

/**
 * @author f536985 (Tom Farello)
 */
public class MissingTrack extends TrackObject {

    public static int TOP = 1;
    public static int BOTTOM = 2;
    public static int LEFT = 3;
    public static int RIGHT = 4;

    private float length;
    private int side;

    public MissingTrack(float distance, float length, int side) {
        setActive(true);
        setDistance(distance);
        setAngle(0);
        setWidth(90);

        this.length = length;
        this.side = side;
    }

    @Override
    public boolean collide(Player player) {
        float angleDiff = Math.abs(getAngle() - player.getAngle()) % 360;
        return isActive() && ((Math.abs(getDistance() - player.getDistance()) < (player.getSpeed() + 1) / 70f ||
                (player.getDistance() - getDistance() > 0 && player.getDistance() - getDistance() < length)) &&
                (angleDiff < getWidth() || angleDiff > 360 - getWidth()));
    }

    @Override
    public void dispose() { }

    @Override
    public void update(float delta, Player player) {
        if (collide(player)) {
            //player.fallOff();
        }
    }

    @Override
    public void draw(float delta, float playerDistance, SpriteBatch spriteBatch) { }

    public float getDistance() {
        return super.getDistance();
    }
    public float getLength() {
        return length;
    }
    public int getSide() {
        return side;
    }
}
