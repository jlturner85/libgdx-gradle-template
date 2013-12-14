package com.sadc.game.gameobject.trackobject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sadc.game.GameConstants;
import com.sadc.game.gameobject.Player;

/**
 * @author f536985 (Tom Farello)
 */
public class Boost extends TrackObject {

    public Boost(float distance, float angle) {
        setActive(true);
        setDistance(distance);
        setAngle(angle);
        setWidth(15);
        setTexture(new Texture("boostpanel.png"));
    }

    public void update(float delta, Player player) {
        if (collide(player)) {
            player.boost();
            setActive(false);
        }
    }

    public void draw(float delta, float playerDistance, SpriteBatch spriteBatch) {
        float drawDistance = (float)Math.pow(2 , playerDistance - (getDistance()));
        spriteBatch.draw(getTexture(), GameConstants.SCREEN_WIDTH / 2 - 30, 15,
                30, GameConstants.SCREEN_HEIGHT / 2 - 30, 60, 60, drawDistance, drawDistance, getAngle(), 0, 0, 60, 60, false, false);
    }

}
