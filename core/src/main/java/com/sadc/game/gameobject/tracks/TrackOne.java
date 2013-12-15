/*
 * $Id$
 *
 * Copyright (c) 2013 HEB
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of HEB.
 */
package com.sadc.game.gameobject.tracks;

import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.sadc.game.gameobject.Racer;
import com.sadc.game.gameobject.Track;
import com.sadc.game.gameobject.trackobject.Boost;
import com.sadc.game.gameobject.trackobject.Checkpoint;
import com.sadc.game.gameobject.trackobject.TrackObject;
import com.sadc.game.gameobject.trackobject.Train;
import com.sadc.game.gameobject.trackobject.Wall;
import com.sadc.game.screen.GameplayScreen;

/**
 * @author f536985 (Tom Farello)
 */
public class TrackOne extends Track {

    public TrackOne(GameplayScreen screen) {
        super(screen);
        setTimer(3600);
        setTrackName("Cockfosters");
        setTunnel1(new Texture("tunnel1.png"));
        setTunnel2(new Texture("tunnel2.png"));
        setTunnelFrames(8);

        List<TrackObject> objects = getObjects();
        List<Racer> racers = getRacers();

        objects.add(new Checkpoint(175, 1800));
        objects.add(new Checkpoint(350, 1800));
        objects.add(new Checkpoint(600)); // finish line

        objects.add(new Boost(5, 0));
        objects.add(new Boost(10, 60));
        objects.add(new Boost(10, -60));
        objects.add(new Boost(15, 120));
        objects.add(new Boost(15, -120));
        objects.add(new Boost(20, 180));

        objects.add(new Wall(30, 0));
        objects.add(new Boost(38, 135));
        objects.add(new Wall(40, 180));
        objects.add(new Boost(48, 90));
        objects.add(new Wall(50, 135));
        objects.add(new Boost(60, 0));
        objects.add(new Wall(60.5f, 30));
        objects.add(new Wall(60.5f, -30));
        objects.add(new Wall(61, 30));
        objects.add(new Wall(61, -30));
        objects.add(new Wall(61.5f, 30));
        objects.add(new Wall(61.5f, -30));
        objects.add(new Wall(62, 30));
        objects.add(new Wall(62, -30));

        objects.add(new Train(150, 3));
    }

}
