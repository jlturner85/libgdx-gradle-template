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

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sadc.game.GameConstants;
import com.sadc.game.gameobject.trackobject.Boost;
import com.sadc.game.gameobject.trackobject.TrackObject;

/**
 * Don't tell me when to write Javadocs
 *
 * @author f536985 (Tom Farello)
 */
public class Track {

    private static final int TOTAL_FRAMES = 8;

    private float length;

    private List<TrackObject> objects;
    private Player player;

    private final Texture tunnel1;
    private final Texture tunnel2;
    private final Texture fade;

    public Track() {
        tunnel1 = new Texture("tunnel1.png");
        tunnel2 = new Texture("tunnel2.png");
        fade = new Texture("fade.png");

        player = new Player(1);

        //debug
        length = 1000;
        objects = new ArrayList<TrackObject>();
        for (int i = 1; i < 100; i++) {
            Boost b = new Boost(i * 8, i * 30);
            objects.add(b);
        }

    }

    public void dispose() {
        tunnel1.dispose();
        tunnel2.dispose();
        fade.dispose();
        player.dispose();
        for (TrackObject o : objects) {
            o.dispose();
        }
    }

    public void update(float delta) {
        player.update(delta);
        for (TrackObject o : objects) {
            o.update(delta, player);
        }
    }

    public void draw(float delta, SpriteBatch spriteBatch) {
        float distance = player.getDistance();
        float drawDistance = 1 + (distance % 0.087f);
        int frame = TOTAL_FRAMES - 1 - (int) ((distance / 0.087f) % TOTAL_FRAMES);
        float scale = 2.117920011581067f;
        for (int i = 0; i < 50; i++) {
            Texture texture;
            if (i % TOTAL_FRAMES == frame) {
                texture = tunnel2;
            } else {
                texture = tunnel1;
            }
            spriteBatch.draw(texture, GameConstants.SCREEN_WIDTH / 2 - 250, GameConstants.SCREEN_HEIGHT / 2 - 250,
                    250, 250, 500, 500, drawDistance * scale, drawDistance * scale, 0, 0, 0, 500, 500, false, false);
            scale *= 0.92f;
        }
        for (TrackObject o : objects) {
            o.draw(delta, player.getDistance(), spriteBatch);
        }

        spriteBatch.draw(fade, GameConstants.SCREEN_WIDTH / 2 - 25, GameConstants.SCREEN_HEIGHT / 2 - 25,
                25, 25, 50, 50, 1, 1, 0, 0, 0, 50, 50, false, false);
        player.draw(delta, spriteBatch);
    }

}
