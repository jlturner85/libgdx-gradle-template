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
import com.sadc.game.gameobject.trackobject.MissingTrack;
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
        setTimer(7200);
        setTrackName("Liverpool Street");
        setTunnel1(new Texture("tunnel1.png"));
        setTunnel2(new Texture("tunnel2.png"));
        setTunnelFrames(8);

        List<TrackObject> objects = getObjects();
        List<Racer> racers = getRacers();

        objects.add(new Checkpoint(200, 2700));
        objects.add(new Checkpoint(400)); // finish line

        objects.add(new Boost(10, 0));
        objects.add(new Boost(15, 0));
        objects.add(new Boost(22.5f, 60));
        objects.add(new Boost(22.5f, -60));
        objects.add(new Wall(25.5f, 0));

        objects.add(new Boost(30, 60));
        objects.add(new Boost(30, -60));
        objects.add(new Boost(35, 90));
        objects.add(new Boost(35, -90));
        objects.add(new Boost(40, 120));
        objects.add(new Boost(40, -120));
        objects.add(new Boost(45, 150));
        objects.add(new Boost(45, -150));
        objects.add(new Boost(50, 180));

        objects.add(new Wall(62, 0));
        objects.add(new Wall(62, 20));
        objects.add(new Wall(62, -20));
        objects.add(new Wall(62, 40));
        objects.add(new Wall(62, -40));
        racers.add(new Racer(63, 0));
        objects.add(new Boost(65, 180));

        objects.add(new Boost(75, 0));
        objects.add(new Boost(75, 90));
        objects.add(new Boost(75, -90));
        objects.add(new Boost(75, 180));
        objects.add(new Wall(75, 45));
        objects.add(new Wall(75, -45));
        objects.add(new Wall(75, 135));
        objects.add(new Wall(75, -135));

        objects.add(new Boost(85, 0));
        objects.add(new Wall(92.5f, 0));

        objects.add(new Boost(100, -45));
        objects.add(new Wall(100, 135));
        objects.add(new Boost(107.5f, -90));
        objects.add(new Wall(107.5f, 90));
        objects.add(new Boost(115, -135));
        objects.add(new Wall(115, 45));
        objects.add(new Boost(122.5f, 180));
        objects.add(new Wall(122.5f, 0));

        racers.add(new Racer(130, 0));

        objects.add(new Boost(150, 0));
        objects.add(new Wall(160, 0));
        objects.add(new Wall(163, 30));
        objects.add(new Wall(163, -30));
        objects.add(new Wall(166, 60));
        objects.add(new Wall(166, -60));
        objects.add(new Wall(169, 90));
        objects.add(new Wall(169, -90));
        objects.add(new Wall(172, 120));
        objects.add(new Wall(172, -120));
        objects.add(new Train(180, 2));

        objects.add(new Boost(190, 180));

        // checkpoint

        objects.add(new Boost(210, 180));
        objects.add(new Boost(215, 135));
        objects.add(new Boost(220, 90));
        objects.add(new Wall(221, 30));
        objects.add(new Wall(221, 150));
        objects.add(new Wall(222, 30));
        objects.add(new Wall(222, 150));

        objects.add(new Boost(230, 90));
        objects.add(new Boost(235, 45));
        objects.add(new Boost(240, 0));
        objects.add(new Wall(241, 60));
        objects.add(new Wall(241, -60));
        objects.add(new Wall(242, 60));
        objects.add(new Wall(242, -60));

        objects.add(new Wall(250, 30));
        objects.add(new Wall(252, 0));
        objects.add(new Wall(254, -30));
        objects.add(new Wall(256, -60));
        objects.add(new Wall(258, -80));
        objects.add(new Wall(260, -100));
        objects.add(new Boost(262, -145));

        objects.add(new Boost(270, 180));
        objects.add(new Wall(275, 180));
        objects.add(new Wall(280, 135));
        objects.add(new Wall(280, -135));
        objects.add(new Wall(285, 90));
        objects.add(new Wall(285, -90));
        objects.add(new Wall(285, 180));

        objects.add(new Boost(295, 45));
        objects.add(new Boost(295, -45));
        objects.add(new Boost(300, 0));
        racers.add(new Racer(307.5f, 45));
        objects.add(new Boost(315, 45));

        objects.add(new Boost(320, 90));
        objects.add(new Boost(325, 135));
        objects.add(new Boost(330, 180));
        objects.add(new Wall(330, -135));
        objects.add(new Train(335, 3));
        objects.add(new Boost(340, 180));

        objects.add(new Boost(345, 180));
        objects.add(new Boost(346.5f, 150));
        objects.add(new Boost(346.5f, -150));
        objects.add(new Boost(348, 90));
        objects.add(new Boost(348, -90));
        racers.add(new Racer(348, 0));

        objects.add(new Wall(355, 60));
        objects.add(new Wall(356.5f, 30));
        objects.add(new Wall(358, 0));
        objects.add(new Wall(359.5f, -30));
        objects.add(new Boost(361, -90));
        objects.add(new Boost(364, -45));
        objects.add(new Boost(364, -135));
        objects.add(new Boost(367, 0));
        objects.add(new Boost(367, 180));
        objects.add(new Boost(370, 45));
        objects.add(new Boost(370, 135));
        objects.add(new Boost(373, 90));
        objects.add(new Boost(376, 45));
        objects.add(new Boost(376, 135));
        objects.add(new Boost(379, 0));
        objects.add(new Boost(379, 180));
        objects.add(new Boost(382, -45));
        objects.add(new Boost(382, -135));
        objects.add(new Boost(385, -90));

        objects.add(new Wall(398, 0));
    }

}
