package com.sadc.game.gameobject.trackobject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sadc.game.gameobject.Player;

/**
 * @author f536985 (Tom Farello)
 */
public abstract class TrackObject {

    private boolean active;
    private float distance;
    private float angle;
    private float width;
    private Texture texture;

    public void update(float delta) { }

    public void dispose() {
        texture.dispose();
    }

    public abstract void update(float delta, Player player);

    public abstract void draw(float delta, float playerDistance, SpriteBatch spriteBatch);

    public boolean collide(Player player) {
        float angleDiff = Math.abs(angle - player.getAngle()) % 360;
        return active && (Math.abs(distance - player.getDistance()) < player.getSpeed() / 50f &&
                (angleDiff < width || angleDiff > 360 - width));
    }

    protected boolean isActive() {
        return active;
    }
    protected void setActive(boolean active) {
        this.active = active;
    }
    protected float getDistance() {
        return distance;
    }
    protected void setDistance(float distance) {
        this.distance = distance;
    }
    protected float getAngle() {
        return angle;
    }
    protected void setAngle(float angle) {
        this.angle = angle;
    }
    protected float getWidth() {
        return width;
    }
    protected void setWidth(float width) {
        this.width = width;
    }
    protected Texture getTexture() {
        return texture;
    }
    protected void setTexture(Texture texture) {
        this.texture = texture;
    }

}
