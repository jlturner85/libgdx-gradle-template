/*
 * $Id$
 *
 * Copyright (c) 2013 HEB
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of HEB.
 */
package com.sadc.game.gameobject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sadc.game.GameConstants;

/**
 * Fuck Javadocs.
 *
 * @author f536985 (Tom Farello)
 */
public class Player {
    private Sound idleSound;
    private Sound firstGearSound;
    private Sound secondGearSound;
    private Sound thirdGearSound;
    private Sound fourthGearSound;
    private Sound fifthGearSound;
    private Sound explosionSound;
    private float speed;
    private float topSpeed;
    private float acceleration;
    private float angle;
    private float spin;

    private int leftKey;
    private int rightKey;

    private Texture texture;

    public Player(int playerNum) {
        speed = 0;
        topSpeed = 5;
        acceleration = 0.003f;
        angle = 0;
        spin = 0;

        if (playerNum == 1) {
            leftKey = GameConstants.P1_LEFT;
            rightKey = GameConstants.P1_RIGHT;
        } else if (playerNum == 2) {
            leftKey = GameConstants.P2_LEFT;
            rightKey = GameConstants.P2_RIGHT;
        }
        idleSound = Gdx.audio.newSound(Gdx.files.internal("soundeffects/car/loop_0.wav"));
        firstGearSound = Gdx.audio.newSound(Gdx.files.internal("soundeffects/car/loop_1_0.wav"));
        secondGearSound = Gdx.audio.newSound(Gdx.files.internal("soundeffects/car/loop_2_0.wav"));
        thirdGearSound = Gdx.audio.newSound(Gdx.files.internal("soundeffects/car/loop_3_0.wav"));
        fourthGearSound = Gdx.audio.newSound(Gdx.files.internal("soundeffects/car/loop_4_0.wav"));
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("soundeffects/explosion.wav"));
        long id = firstGearSound.play(0.5f);
        firstGearSound.setLooping(id, true);
        texture = new Texture("car.png");
    }

    public void update(float delta) {
        speed += acceleration * (topSpeed - speed);

        boolean left = Gdx.input.isKeyPressed(leftKey);
        boolean right = Gdx.input.isKeyPressed(rightKey);

        if (left) {
            spin -= GameConstants.TORQUE;
            if (spin < -GameConstants.MAX_SPIN) spin = -GameConstants.MAX_SPIN;
        }
        if (right) {
            spin += GameConstants.TORQUE;
            if (spin > GameConstants.MAX_SPIN) spin = GameConstants.MAX_SPIN;
        }
        if (!left && !right) {
            if (spin > 0) {
                spin -= GameConstants.FRICTION;
                if (spin < 0) {
                    spin = 0;
                }
            } else if (spin < 0) {
                spin += GameConstants.FRICTION;
                if (spin > 0) {
                    spin = 0;
                }
            }
        }

        angle += spin;
        while (angle > 180) angle -= 360;
        while (angle < -180) angle += 360;
    }

    public void draw (float delta, SpriteBatch spriteBatch) {
        spriteBatch.draw(texture, GameConstants.SCREEN_WIDTH / 2 - 25, 15,
                25, GameConstants.SCREEN_HEIGHT / 2 - 15, 50, 50, 1, 1, angle, 0, 0, 50, 50, false, false);
    }

    public float getSpeed() {
        return this.speed;
    }

}
