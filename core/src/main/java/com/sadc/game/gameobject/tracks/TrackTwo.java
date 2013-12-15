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
public class TrackTwo extends Track {

    public TrackTwo(GameplayScreen screen) {
        super(screen);
        setTimer(4500);
        setTrackName("Paddington");
        setTunnel1(new Texture("tunnel1.png"));
        setTunnel2(new Texture("tunnel2.png"));
        setTunnelFrames(8);

        List<TrackObject> objects = getObjects();
        List<Racer> racers = getRacers();

        objects.add(new Checkpoint(175, 2400));
        objects.add(new Checkpoint(350, 2400));
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
        objects.add(new Wall(60.5f, 45));
        objects.add(new Wall(60.5f, -45));
        objects.add(new Wall(61, 45));
        objects.add(new Wall(61, -45));
        objects.add(new Wall(61.5f, 45));
        objects.add(new Wall(61.5f, -45));
        objects.add(new Wall(62, 45));
        objects.add(new Wall(62, -45));

        objects.add(new Boost(70, 45));
        objects.add(new Boost(70, -45));
        objects.add(new Boost(70, 135));
        objects.add(new Boost(70, -135));
        racers.add(new Racer(71, 0));
        objects.add(new Boost(80, 0));
        objects.add(new Boost(80, 180));
        racers.add(new Racer(81, 90));
        objects.add(new Boost(85, 30));
        objects.add(new Boost(85, -30));
        objects.add(new Wall(90, 180));
        objects.add(new Wall(90, 160));
        objects.add(new Wall(90, 140));
        objects.add(new Wall(90, 120));
        objects.add(new Wall(90, 100));
        objects.add(new Wall(90, -160));
        objects.add(new Wall(90, -140));
        objects.add(new Wall(90, -120));
        objects.add(new Wall(90, -100));

        objects.add(new Boost(100, 0));
        objects.add(new Boost(102.5f, 30));
        objects.add(new Boost(102.5f, -30));
        objects.add(new Wall(107.5f, 0));
        objects.add(new Boost(110, 180));
        objects.add(new Boost(112.5f, 150));
        objects.add(new Boost(112.5f, -150));
        objects.add(new Wall(117.5f, 180));

        racers.add(new Racer(120, -150));
        objects.add(new Boost(123, 90));
        objects.add(new Boost(126, 60));

        objects.add(new Wall(131, 0));
        objects.add(new Wall(132.5f, 20));
        objects.add(new Wall(132.5f, -20));
        objects.add(new Boost(133.5f, 90));
        objects.add(new Boost(133.5f, -90));
        objects.add(new Wall(134, 40));
        objects.add(new Wall(134, -40));
        objects.add(new Wall(135.5f, 60));
        objects.add(new Wall(135.5f, -60));
        objects.add(new Wall(137, 80));
        objects.add(new Wall(137, -80));
        objects.add(new Wall(138.5f, 100));
        objects.add(new Wall(138.5f, -100));
        objects.add(new Boost(140, 180));

        objects.add(new Train(150, 3));

        objects.add(new Boost(160, 180));
        objects.add(new Boost(163, 135));
        objects.add(new Boost(166, 90));
        objects.add(new Boost(169, 45));
        objects.add(new Boost(172, 0));

        // checkpoint

        objects.add(new Boost(180, 0));
        objects.add(new Wall(181.5f, -60));
        objects.add(new Wall(183, -30));
        objects.add(new Wall(184.5f, 0));
        objects.add(new Wall(186, 30));
        objects.add(new Wall(187.5f, 60));

        objects.add(new Boost(192.5f, 0));
        objects.add(new Wall(194, 60));
        objects.add(new Wall(195.5f, 30));
        objects.add(new Wall(197, 0));
        objects.add(new Wall(198.5f, -30));
        objects.add(new Wall(200, -60));

        objects.add(new Boost(205, 180));
        objects.add(new Train(212.5f, 3));

        racers.add(new Racer(220, 0));
        objects.add(new Boost(220, 180));
        objects.add(new Wall(225, 90));
        objects.add(new Boost(230, -60));
        objects.add(new Wall(235, -120));
        racers.add(new Racer(240, 0));
        objects.add(new Boost(240, 90));
        objects.add(new Wall(245, 60));
        objects.add(new Boost(250, 30));
        objects.add(new Wall(265, 0));
        objects.add(new Boost(260, -45));

        racers.add(new Racer(271, -90));
        objects.add(new Boost(270, -90));
        objects.add(new Boost(273, -45));
        objects.add(new Boost(276, 0));
        objects.add(new Wall(279, -90));
        objects.add(new Boost(279, 45));
        objects.add(new Boost(282, 90));
        objects.add(new Wall(288, 90));
        objects.add(new Boost(288, 180));

        objects.add(new Train(295, 4));
        objects.add(new Wall(297.5f, -105));
        objects.add(new Wall(300.5f, -145));

        objects.add(new Wall(305, -120));
        objects.add(new Wall(306, -135));
        objects.add(new Wall(307, -150));
        objects.add(new Wall(308, -165));
        objects.add(new Wall(309, 180));
        objects.add(new Wall(310, 165));
        objects.add(new Wall(311, 150));
        objects.add(new Boost(314, 120));

        objects.add(new Wall(320, 90));
        objects.add(new Boost(320, -90));

        racers.add(new Racer(325, 120));
        racers.add(new Racer(327.5f, -120));
        objects.add(new Boost(330, 90));
        objects.add(new Boost(330, -90));
        objects.add(new Wall(335, 0));
        objects.add(new Wall(335, 180));
        racers.add(new Racer(340, 60));
        racers.add(new Racer(342.5f, -60));
        objects.add(new Boost(346, 0));

        // checkpoint

        objects.add(new Boost(355, 30));
        objects.add(new Boost(355, -120));
        objects.add(new Wall(357.5f, 30));
        objects.add(new Wall(357.5f, -120));

        objects.add(new Boost(363, 180));
        objects.add(new Wall(363, 90));
        objects.add(new Wall(363, -90));
        objects.add(new Wall(363.5f, 105));
        objects.add(new Wall(363.5f, -105));
        objects.add(new Wall(364, 120));
        objects.add(new Wall(364, -120));
        objects.add(new Wall(364.5f, 135));
        objects.add(new Wall(364.5f, -135));
        objects.add(new Wall(365, 150));
        objects.add(new Wall(365, -150));
        objects.add(new Boost(365, 180));

        racers.add(new Racer(370, 180));
        objects.add(new Wall(373, 150));
        objects.add(new Wall(374, 120));
        objects.add(new Wall(375, 90));
        objects.add(new Wall(376, 60));
        objects.add(new Boost(376, 0));
        objects.add(new Wall(377, 30));
        objects.add(new Wall(378, 0));
        objects.add(new Wall(379, -30));
        objects.add(new Wall(380, -60));
        objects.add(new Wall(381, -90));
        objects.add(new Wall(382, -120));
        objects.add(new Wall(383, -150));
        objects.add(new Wall(384, 180));

        racers.add(new Racer(391, 45));
        objects.add(new Boost(400, 120));
        objects.add(new Boost(403, -60));
        objects.add(new Boost(406, 90));
        racers.add(new Racer(406, 0));
        objects.add(new Wall(409, -150));
        objects.add(new Boost(409, -105));
        objects.add(new Boost(412, 105));
        objects.add(new Wall(418, 105));
        objects.add(new Boost(418, 180));

        objects.add(new Boost(422, -90));
        racers.add(new Racer(425, 90));
        objects.add(new Boost(425, -45));
        objects.add(new Boost(425, -135));
        objects.add(new Boost(425, -45));
        objects.add(new Boost(428, 0));
        objects.add(new Boost(428, 180));
        objects.add(new Wall(428, -90));
        objects.add(new Wall(431, -45));
        objects.add(new Wall(431, -135));
        objects.add(new Wall(434, 0));
        objects.add(new Wall(434, 90));

        racers.add(new Racer(440, 180));
        objects.add(new Boost(440, 0));
        objects.add(new Wall(445, -90));
        objects.add(new Boost(450, 120));
        objects.add(new Wall(455, 60));
        racers.add(new Racer(460, 180));
        objects.add(new Boost(460, -90));
        objects.add(new Wall(465, -120));
        objects.add(new Boost(470, -150));
        objects.add(new Wall(475, 180));
        objects.add(new Boost(480, 135));

        objects.add(new Boost(485, 0));
        objects.add(new Boost(485, 90));
        objects.add(new Boost(485, 180));
        objects.add(new Boost(485, -90));
        racers.add(new Racer(485, 120));
        racers.add(new Racer(485, -60));
        objects.add(new Boost(490, 135));
        racers.add(new Racer(492.5f, 30));
        objects.add(new Boost(495, -45));
        objects.add(new Boost(500, 0));

        objects.add(new Boost(505, 120));
        objects.add(new Boost(505, -120));
        objects.add(new Train(515, 5));

        objects.add(new Wall(524, 180));
        objects.add(new Wall(526, 135));
        objects.add(new Wall(526, -135));
        objects.add(new Wall(528, -90));
        objects.add(new Wall(528, 90));

        objects.add(new MissingTrack(530, 100, MissingTrack.TOP));
        racers.add(new Racer(530, 80));
        racers.add(new Racer(530, -80));
        objects.add(new Boost(530, 0));
        objects.add(new Wall(537, 75));
        objects.add(new Wall(537, -75));
        objects.add(new Boost(545, 60));
        objects.add(new Wall(550, 30));
        objects.add(new Boost(545, 60));
        objects.add(new Wall(552.5f, 60));
        objects.add(new Wall(552.5f, -60));
        objects.add(new Wall(557, 0));
        objects.add(new Boost(562, -45));
        objects.add(new Boost(564, 0));
        objects.add(new Boost(566, 45));
        objects.add(new Wall(568, -30));
        objects.add(new Boost(571, 60));
        objects.add(new Boost(573, 0));
        objects.add(new Boost(575, -60));

        objects.add(new Wall(585, -75));
        objects.add(new Wall(585, -45));
        objects.add(new Wall(585, -15));
        objects.add(new Boost(585, 30));
        objects.add(new Wall(595, 75));
        objects.add(new Wall(595, 45));
        objects.add(new Wall(595, 15));
        objects.add(new Boost(595, -30));
    }

}
