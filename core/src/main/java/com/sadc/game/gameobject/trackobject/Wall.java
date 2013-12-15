package com.sadc.game.gameobject.trackobject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sadc.game.GameConstants;
import com.sadc.game.gameobject.GameUtils;
import com.sadc.game.gameobject.Player;

/**
 * @author f536985 (Tom Farello)
 */
public class Wall extends TrackObject {

    public Wall(float distance, float angle) {
        setActive(true);
        setDistance(distance);
        setAngle(angle);
        setWidth(22);
        setTexture(new Texture("brickWall.png"));
    }

    @Override
    public void update(float delta, Player player) {
        if (collide(player)) {
            player.crash();
            setActive(false);
        }
    }

    @Override
    public void draw(float delta, float playerDistance, SpriteBatch spriteBatch) {
        float drawDistance = (float)Math.pow(2 , playerDistance - (getDistance()));
        GameUtils.setColorByDrawDistance(drawDistance, spriteBatch);
        spriteBatch.draw(getTexture(), GameConstants.SCREEN_WIDTH / 2 - 50, 15,
                50, GameConstants.SCREEN_HEIGHT / 2 - 15, 100, 70, drawDistance, drawDistance, getAngle(), 0, 0, 100, 70, false, false);
    }
}
