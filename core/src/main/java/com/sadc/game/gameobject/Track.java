package com.sadc.game.gameobject;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sadc.game.GameConstants;
import com.sadc.game.gameobject.trackobject.BackgroundChange;
import com.sadc.game.gameobject.trackobject.Boost;
import com.sadc.game.gameobject.trackobject.Checkpoint;
import com.sadc.game.gameobject.trackobject.MissingTrack;
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

    private GameplayScreen screen;

    private int timer;
    private long time;
    private int bonusFrames;
    private String trackName;

    private List<TrackObject> objects;
    private List<Racer> racers;
    private Player player;

    private Texture tunnel1;
    private Texture tunnel2;
    private int tunnelFrames;
    private Texture background;
    private Texture fade;

    public Track(GameplayScreen screen) {
        this.screen = screen;

        background = new Texture("datNightSky.png");
        fade = new Texture("fade.png");

        player = new Player(1, this, GameConstants.CURRENT_SELECTED_CAR);
        objects = new ArrayList<TrackObject>();
        racers = new ArrayList<Racer>();
    }

    public void bonusTime(int bonusFrames) {
        this.bonusFrames = bonusFrames;
    }

    public void finish() {
        screen.finish(time, trackName);
    }

    public void fail() {
        screen.fail(time, trackName);
    }

    public void changeBackground(Texture texture) {
        background = texture;
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
        if (timer <= 0) {
            fail();
        }
        player.update(delta);
        for (TrackObject o : objects) {
            o.update(delta, player);
        }
        for (Racer r : racers) {
            r.update(delta, player, objects);
        }
    }

    public void draw(float delta, SpriteBatch spriteBatch) {
        GameUtils.setColorByDrawDistance(1, spriteBatch);
        spriteBatch.draw(background, 0, 0);
        spriteBatch.draw(fade, GameConstants.SCREEN_WIDTH / 2 - 50, GameConstants.SCREEN_HEIGHT / 2 - 50);

        float distance = player.getDistance();
        float trackDistance = 1 + (distance % 0.125f);
        int frame = tunnelFrames - 1 - (int) ((distance / 0.125f) % tunnelFrames);
        float scale = 1.6f;
        for (int i = 0; i < 50; i++) {
            Texture texture;
            if (i % tunnelFrames == frame) {
                texture = tunnel2;
            } else {
                texture = tunnel1;
            }
            float drawDistance = trackDistance * scale;
            GameUtils.setColorByDrawDistance(drawDistance, spriteBatch);
            int missing = isMissing(distance + (i - 10) / 8f);
            if (missing == 0) {
                spriteBatch.draw(texture, GameConstants.SCREEN_WIDTH / 2 - 250, GameConstants.SCREEN_HEIGHT / 2 - 250,
                        250, 250, 500, 500, drawDistance, drawDistance, 0, 0, 0, 500, 500, false, false);
            }
            if (missing == 1) {
                spriteBatch.draw(texture, GameConstants.SCREEN_WIDTH / 2 - 250, GameConstants.SCREEN_HEIGHT / 2 - 250,
                        250, 250, 500, 250, drawDistance, drawDistance, 0, 0, 250, 500, 250, false, false);
            }
            scale *= 0.92f;
        }
        for (TrackObject o : objects) {
            o.draw(delta, player.getDistance(), spriteBatch);
        }
        for (Racer r : racers) {
            r.draw(delta, player.getDistance(), spriteBatch);
        }
        player.draw(delta, spriteBatch);
    }

    private int isMissing(float distance) {
        for (TrackObject o : objects) {
            if (o instanceof MissingTrack) {
                MissingTrack mt = (MissingTrack)o;
                if (distance >= mt.getDistance() && distance <= mt.getDistance() + mt.getLength()) {
                    return mt.getSide();
                }
            }
        }
        return 0;
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

    protected void setScreen(GameplayScreen screen) {
        this.screen = screen;
    }
    protected void setTimer(int timer) {
        this.timer = timer;
    }
    protected void setTrackName(String trackName) {
        this.trackName = trackName;
    }
    protected List<TrackObject> getObjects() {
        return objects;
    }
    protected List<Racer> getRacers() {
        return racers;
    }
    protected void setPlayer(Player player) {
        this.player = player;
    }
    protected void setTunnel1(Texture tunnel1) {
        this.tunnel1 = tunnel1;
    }
    protected void setTunnel2(Texture tunnel2) {
        this.tunnel2 = tunnel2;
    }
    protected void setTunnelFrames(int tunnelFrames) {
        this.tunnelFrames = tunnelFrames;
    }
}
