package com.sadc.game.gameobject;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sadc.game.GameConstants;
import com.sadc.game.gameobject.trackobject.Boost;
import com.sadc.game.gameobject.trackobject.Checkpoint;
import com.sadc.game.gameobject.trackobject.TrackObject;
import com.sadc.game.gameobject.trackobject.Train;
import com.sadc.game.gameobject.trackobject.Wall;
import com.sadc.game.screen.GameplayScreen;

/**
 * Don't tell me when to write Javadocs
 *
 * @author f536985 (Tom Farello)
 */
public class Track {

    private static final int TOTAL_FRAMES = 8;

    private GameplayScreen screen;

    private int timer;
    private long time;
    private int bonusFrames;
    private String trackName;

    private List<TrackObject> objects;
    private Player player;

    private final Texture tunnel1;
    private final Texture tunnel2;

    public Track(GameplayScreen screen) {
        this.screen = screen;

        tunnel1 = new Texture("tunnel1.png");
        tunnel2 = new Texture("tunnel2.png");

        player = new Player(1, this);

        //debug
        timer = 3600;
        objects = new ArrayList<TrackObject>();
        for (int i = 1; i < 100; i++) {
            if (i % 2 == 0) {
                Boost b = new Boost(i * 8, ((i * 30) % 360) - 180);
                objects.add(b);
            } else {
                Wall w = new Wall(i * 8, ((i * 30) % 360) - 180);
                objects.add(w);
            }
        }
        /*for (int i = 1; i < 10; i++) {
            Train t = new Train(i * 25, 4);
            objects.add(t);
        }*/
        Checkpoint checkpoint = new Checkpoint(20, 1800);
        Checkpoint finish = new Checkpoint(35);
        objects.add(checkpoint);
        objects.add(finish);
        trackName = "Test Track";
    }

    public void bonusTime(int bonusFrames) {
        this.bonusFrames = bonusFrames;
    }

    public void finish() {
        screen.finish(time, trackName);
    }

    public void dispose() {
        tunnel1.dispose();
        tunnel2.dispose();
        player.dispose();
        for (TrackObject o : objects) {
            o.dispose();
        }
    }

    public void update(float delta) {
        if (bonusFrames > 0) {
            int frames = Math.min(bonusFrames, 20);
            timer += frames;
            bonusFrames -= frames;
        }
        timer--;
        time++;
        player.update(delta);
        for (TrackObject o : objects) {
            o.update(delta, player);
        }
    }

    public void draw(float delta, SpriteBatch spriteBatch) {
        float distance = player.getDistance();
        float trackDistance = 1 + (distance % 0.087f);
        int frame = TOTAL_FRAMES - 1 - (int) ((distance / 0.087f) % TOTAL_FRAMES);
        float scale = 2.117920011581067f;
        for (int i = 0; i < 50; i++) {
            Texture texture;
            if (i % TOTAL_FRAMES == frame) {
                texture = tunnel2;
            } else {
                texture = tunnel1;
            }
            float drawDistance = trackDistance * scale;
            GameUtils.setColorByDrawDistance(drawDistance, spriteBatch);
            spriteBatch.draw(texture, GameConstants.SCREEN_WIDTH / 2 - 250, GameConstants.SCREEN_HEIGHT / 2 - 250,
                    250, 250, 500, 500, drawDistance, drawDistance, 0, 0, 0, 500, 500, false, false);
            scale *= 0.92f;
        }
        for (TrackObject o : objects) {
            o.draw(delta, player.getDistance(), spriteBatch);
        }
        player.draw(delta, spriteBatch);
    }

    public Player getPlayer() {
        return player;
    }
    public int getTimer() {
        return timer;
    }
    public int getBonusFrames() {
        return bonusFrames;
    }

}
