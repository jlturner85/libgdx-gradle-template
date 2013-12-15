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
public class TrackThree extends Track {

    public TrackThree(GameplayScreen screen) {
        super(screen);
        setTimer(3600);
        setTrackName("Arsenal");
        setTunnel1(new Texture("tunnel1.png"));
        setTunnel2(new Texture("tunnel2.png"));
        setTunnelFrames(8);

        List<TrackObject> objects = getObjects();
        List<Racer> racers = getRacers();

        objects.add(new Checkpoint(150, 1200));
        objects.add(new Checkpoint(210)); // finish line

        racers.add(new Racer(15, 0));
        racers.add(new Racer(16, 30));
        racers.add(new Racer(17, -30));
        objects.add(new Train(20, 3));

        objects.add(new Boost(20, 180));
        objects.add(new Boost(21, 180));
        objects.add(new Boost(22, 180));

        objects.add(new Wall(25, 135));
        objects.add(new Wall(25, -135));
        objects.add(new Wall(25.5f, 120));
        objects.add(new Wall(25.5f, -150));
        objects.add(new Wall(26, 105));
        objects.add(new Wall(26, -165));
        objects.add(new Wall(26.5f, 90));
        objects.add(new Wall(26.5f, 180));
        objects.add(new Wall(27, 75));
        objects.add(new Wall(27, 165));
        objects.add(new Wall(27.5f, 60));
        objects.add(new Wall(27.5f, 150));
        objects.add(new Wall(28, 45));
        objects.add(new Wall(28, 135));
        objects.add(new Wall(28.5f, 30));
        objects.add(new Wall(28.5f, 120));
        objects.add(new Wall(29, 15));
        objects.add(new Wall(29, 105));
        objects.add(new Wall(29.5f, 0));
        objects.add(new Wall(29.5f, 90));
        objects.add(new Wall(30, -15));
        objects.add(new Wall(30, 75));
        objects.add(new Wall(30.5f, -30));
        objects.add(new Wall(30.5f, 60));
        objects.add(new Wall(31, -45));
        objects.add(new Wall(31, 45));
        objects.add(new Wall(31.5f, -60));
        objects.add(new Wall(31.5f, 30));
        objects.add(new Wall(32, -75));
        objects.add(new Wall(32, 15));
        objects.add(new Wall(32.5f, -90));
        objects.add(new Wall(32.5f, 0));
        objects.add(new Wall(33, -105));
        objects.add(new Wall(33, -15));
        objects.add(new Wall(33.5f, -120));
        objects.add(new Wall(33.5f, -30));
        objects.add(new Wall(34, -135));
        objects.add(new Wall(34, -45));
        objects.add(new Wall(34.5f, -150));
        objects.add(new Wall(34.5f, -60));
        objects.add(new Wall(35, -165));
        objects.add(new Wall(35, -75));
        objects.add(new Wall(35.5f, 180));
        objects.add(new Wall(35.5f, -90));
        objects.add(new Wall(36, 165));
        objects.add(new Wall(36, -105));
        objects.add(new Wall(36.5f, 150));
        objects.add(new Wall(36.5f, -120));
        objects.add(new Wall(37, 135));
        objects.add(new Wall(37, -135));

        objects.add(new Boost(40, 180));
        objects.add(new Wall(45, 30));
        objects.add(new Wall(45, 60));
        objects.add(new Wall(45, 90));
        objects.add(new Wall(45, 120));
        objects.add(new Wall(45, 150));
        objects.add(new Wall(45, 180));
        objects.add(new Wall(45, -150));
        objects.add(new Wall(45, -120));
        objects.add(new Wall(45, -90));
        objects.add(new Wall(45, -60));
        objects.add(new Wall(45, -30));

        objects.add(new Boost(50, 150));
        objects.add(new Wall(55, 30));
        objects.add(new Wall(55, 60));
        objects.add(new Wall(55, 90));
        objects.add(new Wall(55, 120));
        objects.add(new Wall(55, 150));
        objects.add(new Wall(55, 180));
        objects.add(new Wall(55, -150));
        objects.add(new Wall(55, -120));
        objects.add(new Wall(55, -90));
        objects.add(new Wall(55, -60));
        objects.add(new Wall(55, 0));

        objects.add(new Boost(60, -120));
        objects.add(new Wall(65, 30));
        objects.add(new Wall(65, 90));
        objects.add(new Wall(65, 120));
        objects.add(new Wall(65, 150));
        objects.add(new Wall(65, 180));
        objects.add(new Wall(65, -150));
        objects.add(new Wall(65, -120));
        objects.add(new Wall(65, -90));
        objects.add(new Wall(65, -60));
        objects.add(new Wall(65, -30));
        objects.add(new Wall(65, 0));

        objects.add(new Boost(70, 0));
        objects.add(new Wall(75, 30));
        objects.add(new Wall(75, 60));
        objects.add(new Wall(75, 90));
        objects.add(new Wall(75, 120));
        objects.add(new Wall(75, 150));
        objects.add(new Wall(75, 180));
        objects.add(new Wall(75, -150));
        objects.add(new Wall(75, -120));
        objects.add(new Wall(75, -90));
        objects.add(new Wall(75, -60));
        objects.add(new Wall(75, -30));

        objects.add(new Train(82, 5));

        objects.add(new Wall(82, 180));
        objects.add(new Wall(83.5f, 150));
        objects.add(new Wall(83.5f, -150));
        objects.add(new Wall(85, 120));
        objects.add(new Wall(85, -120));
        objects.add(new Wall(87.5f, 150));
        objects.add(new Wall(87.5f, -150));

        objects.add(new Boost(92, 0));
        objects.add(new Boost(93, 45));
        objects.add(new Boost(94, 90));
        objects.add(new Boost(95, 135));
        objects.add(new Boost(96, 180));
        objects.add(new Boost(97, -135));
        objects.add(new Boost(98, -90));
        objects.add(new Boost(99, -45));
        objects.add(new Wall(100, 0));

        objects.add(new Train(105, 3));
        objects.add(new Boost(106, -165));

        objects.add(new Wall(110, 0));
        objects.add(new Wall(110, 90));
        objects.add(new Wall(110, -90));
        objects.add(new Wall(110, 180));
        objects.add(new Wall(113, 45));
        objects.add(new Wall(113, -45));
        objects.add(new Wall(113, 135));
        objects.add(new Wall(113, -135));
        objects.add(new Wall(116, 0));
        objects.add(new Wall(116, 90));
        objects.add(new Wall(116, -90));
        objects.add(new Wall(116, 180));
        objects.add(new Wall(119, 45));
        objects.add(new Wall(119, -45));
        objects.add(new Wall(119, 135));
        objects.add(new Wall(119, -135));
        objects.add(new Wall(122, 0));
        objects.add(new Wall(122, 90));
        objects.add(new Wall(122, -90));
        objects.add(new Wall(122, 180));
        objects.add(new Wall(125, 45));
        objects.add(new Wall(125, -45));
        objects.add(new Wall(125, 135));
        objects.add(new Wall(125, -135));

        objects.add(new Boost(130, 0));
        objects.add(new Boost(130, 30));
        objects.add(new Boost(130, 60));
        objects.add(new Boost(130, 90));
        objects.add(new Boost(130, 120));
        objects.add(new Boost(130, 150));
        objects.add(new Boost(130, 180));
        objects.add(new Boost(130, -150));
        objects.add(new Boost(130, -120));
        objects.add(new Boost(130, -90));
        objects.add(new Boost(130, -60));
        objects.add(new Boost(130, -30));

        racers.add(new Racer(134, 0));
        racers.add(new Racer(135, 180));
        racers.add(new Racer(136, 45));
        racers.add(new Racer(137, -135));
        racers.add(new Racer(138, 90));
        racers.add(new Racer(139, -90));

        // checkpoint

        objects.add(new Wall(160, 30));
        objects.add(new Wall(160, 60));
        objects.add(new Wall(160, 90));
        objects.add(new Wall(160, 120));
        objects.add(new Wall(160, 150));
        objects.add(new Wall(160, 180));
        objects.add(new Wall(160, -150));
        objects.add(new Wall(160, -120));
        objects.add(new Wall(160, -90));
        objects.add(new Wall(160, -60));
        objects.add(new Wall(160, -30));

        objects.add(new Wall(160.5f, -60));
        objects.add(new Wall(161, -75));
        objects.add(new Wall(161.5f, -90));
        objects.add(new Wall(161.5f, 0));
        objects.add(new Wall(162, -105));
        objects.add(new Wall(162, -15));
        objects.add(new Wall(162.5f, -120));
        objects.add(new Wall(162.5f, -30));
        objects.add(new Wall(163, -135));
        objects.add(new Wall(163, -45));
        objects.add(new Wall(163.5f, -150));
        objects.add(new Wall(163.5f, -60));
        objects.add(new Wall(164, -165));
        objects.add(new Wall(164, -75));
        objects.add(new Wall(164.5f, 180));
        objects.add(new Wall(164.5f, -90));
        objects.add(new Wall(165, -105));
        objects.add(new Wall(165.5f, -120));
        objects.add(new Wall(166, 135));
        objects.add(new Wall(166, -135));
        objects.add(new Wall(166.5f, 135));
        objects.add(new Wall(166.5f, -135));
        objects.add(new Wall(167, 120));
        objects.add(new Wall(167.5f, 105));
        objects.add(new Wall(168, 90));
        objects.add(new Wall(168, 180));
        objects.add(new Wall(168.5f, 75));
        objects.add(new Wall(168.5f, 165));
        objects.add(new Wall(169, 60));
        objects.add(new Wall(169, 150));
        objects.add(new Wall(169.5f, 45));
        objects.add(new Wall(169.5f, 135));
        objects.add(new Wall(170, 30));
        objects.add(new Wall(170, 120));
        objects.add(new Wall(170.5f, 15));
        objects.add(new Wall(170.5f, 105));
        objects.add(new Wall(171, 0));
        objects.add(new Wall(171, 90));
        objects.add(new Wall(171.5f, 75));
        objects.add(new Wall(172, 60));
        objects.add(new Wall(172.5f, -45));
        objects.add(new Wall(172.5f, 45));
        objects.add(new Boost(172.5f, 0));

        objects.add(new Wall(160.5f, 60));
        objects.add(new Wall(161, 75));
        objects.add(new Wall(161.5f, 90));
        objects.add(new Wall(161.5f, -0));
        objects.add(new Wall(162, 105));
        objects.add(new Wall(162, 15));
        objects.add(new Wall(162.5f, 120));
        objects.add(new Wall(162.5f, 30));
        objects.add(new Wall(163, 135));
        objects.add(new Wall(163, 45));
        objects.add(new Wall(163.5f, 150));
        objects.add(new Wall(163.5f, 60));
        objects.add(new Wall(164, 165));
        objects.add(new Wall(164, 75));
        objects.add(new Wall(164.5f, 180));
        objects.add(new Wall(164.5f, 90));
        objects.add(new Wall(165, 105));
        objects.add(new Wall(165.5f, 120));
        objects.add(new Wall(166, -135));
        objects.add(new Wall(166, 135));
        objects.add(new Wall(166.5f, -135));
        objects.add(new Wall(166.5f, 135));
        objects.add(new Wall(167, -120));
        objects.add(new Wall(167.5f, -105));
        objects.add(new Wall(168, -90));
        objects.add(new Wall(168, 180));
        objects.add(new Wall(168.5f, -75));
        objects.add(new Wall(168.5f, -165));
        objects.add(new Wall(169, -60));
        objects.add(new Wall(169, -150));
        objects.add(new Wall(169.5f, -45));
        objects.add(new Wall(169.5f, -135));
        objects.add(new Wall(170, -30));
        objects.add(new Wall(170, -120));
        objects.add(new Wall(170.5f, -15));
        objects.add(new Wall(170.5f, -105));
        objects.add(new Wall(171, 0));
        objects.add(new Wall(171, -90));
        objects.add(new Wall(171.5f, -75));
        objects.add(new Wall(172, -60));

        objects.add(new Boost(166, 180));

        objects.add(new Train(185, 3));
        objects.add(new Wall(182, 180));
        objects.add(new Wall(182, 150));
        objects.add(new Wall(182, -150));

        objects.add(new Boost(185, 180));
        objects.add(new Wall(186, 180));
        objects.add(new Boost(187, 120));
        objects.add(new Boost(187, -120));
        objects.add(new Wall(188, 120));
        objects.add(new Wall(188, -120));
        objects.add(new Boost(189, 120));
        objects.add(new Boost(189, -120));
        objects.add(new Wall(190, 60));
        objects.add(new Wall(190, -60));
        objects.add(new Boost(191, 0));
        objects.add(new Wall(192, 0));

        objects.add(new Train(200,5));
        objects.add(new Wall(195, 90));
        objects.add(new Wall(195, -90));
        objects.add(new Wall(195, 180));
        objects.add(new Wall(198, 45));
        objects.add(new Wall(198, -45));
        objects.add(new Wall(198, 135));
        objects.add(new Wall(198, -135));
        objects.add(new Wall(201, 90));
        objects.add(new Wall(201, -90));
        objects.add(new Wall(201, 180));
        objects.add(new Wall(204, 45));
        objects.add(new Wall(204, -45));
        objects.add(new Wall(204, 135));
        objects.add(new Wall(204, -135));
        objects.add(new Boost(207, 0));
        objects.add(new Wall(207, 90));
        objects.add(new Wall(207, -90));
        objects.add(new Wall(207, 180));
    }
}
