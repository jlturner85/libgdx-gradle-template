package com.sadc.game.gameobject;

import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sadc.game.GameConstants;
import com.sadc.game.animation.Animator;
import com.sadc.game.gameobject.trackobject.Boost;
import com.sadc.game.gameobject.trackobject.MissingTrack;
import com.sadc.game.gameobject.trackobject.TrackObject;
import com.sadc.game.gameobject.trackobject.Train;
import com.sadc.game.gameobject.trackobject.Wall;

/**
 * @author f536985 (Tom Farello)
 */
public class Racer {

    private static final int CAR_SPRITE_ROWS = 2;
    private static final int CAR_SPRITE_COLUMNS = 2;
    private static final float CAR_FRAME_DURATION = 0.1f;
    private static final int EXPLOSION_SPRITE_ROWS = 2;
    private static final int EXPLOSION_SPRITE_COLUMNS = 3;
    private static final float EXPLOSION_FRAME_DURATION = 0.5f;

    private boolean active;
    private boolean triggered;
    private float triggerDistance;
    private int fallOff;
    private int timeout;

    private float distance;
    private float speed;
    private float topSpeed;
    private float acceleration;
    private float angle;
    private float spin;

    private float boost;
    private float dBoost;

    private Texture texture;
    private Texture explosionTexture;
    private Animator explosionAnimator;
    private Sound explosionSound = Gdx.audio.newSound(Gdx.files.internal("soundeffects/hit.wav"));

    private int action;
    private int nextAction;
    private Random random;

    public Racer(float distance, float angle) {
        timeout = 0;
        active = true;
        this.distance = distance;
        speed = 1;
        topSpeed = 2.5f;
        acceleration = 0.001f;
        this.angle = angle;
        spin = 0;
        boost = 1;
        dBoost = 0;

        triggerDistance = distance - 7;

        texture = new Texture("doubleDecker.png");
        explosionTexture = new Texture("explosion_6frames.png");
        explosionAnimator = new Animator(explosionTexture, EXPLOSION_SPRITE_COLUMNS, EXPLOSION_SPRITE_ROWS, EXPLOSION_FRAME_DURATION);

        action = 0;
        nextAction = 0;
        random = new Random();
    }

    public boolean collide(Player player) {
        float angleDiff = Math.abs(angle - player.getAngle()) % 360;
        return active && (Math.abs(distance - player.getDistance()) < (player.getSpeed() + 1) / 70f &&
                (angleDiff < 15 || angleDiff > 360 - 15));
    }

    public void boost() {
        if (boost <= 1) {
            boost = GameConstants.BOOST;
        }
        dBoost = GameConstants.D_BOOST;
    }

    public void fallOff() {
        fallOff = 1;
    }

    public void crash(float playerDistance) {
        timeout = 120;
        float soundVolume = (float)Math.pow(2 , playerDistance - (distance));
        if (soundVolume > 1) {
            soundVolume = 1f / (soundVolume * soundVolume);
        }
        explosionSound.play(soundVolume);
        active = false;
    }

    public void dispose() {
        texture.dispose();
        explosionTexture.dispose();
        explosionAnimator.dispose();
        explosionSound.dispose();
    }

    public void update(float delta, Player player, List<TrackObject> objects) {
        if (triggered) {
            if (timeout > 0) {
                timeout--;
            }
            if (fallOff > 0) {
                speed -= acceleration * 10;
                distance += speed / 60f;
                fallOff++;
                if (speed < 0) {
                    speed = 0;
                    angle = 0;
                }

                if (spin > 0) {
                    spin -= GameConstants.FRICTION;
                    if (spin < 0) {
                        spin = 0;
                    }
                } else if (spin < 0) {
                    spin += GameConstants.FRICTION;
                    if (spin > 0) {
                        spin = 0;
                    }
                }

                angle += spin;
                while (angle > 180) angle -= 360;
                while (angle < -180) angle += 360;
            } else if (active) {
                nextAction--;
                speed += acceleration * Math.max(1, speed / topSpeed) * ((topSpeed * boost) - speed);
                distance += speed / 60f;

                dBoost += GameConstants.D_D_BOOST;
                if (boost > 1) {
                    boost += dBoost;
                    if (boost < 1) {
                        boost = 1;
                        dBoost = 0;
                    }
                }

                if (action == -1) {
                    spin -= GameConstants.TORQUE / 3f;
                    if (spin < -GameConstants.MAX_SPIN / 2f) spin = -GameConstants.MAX_SPIN / 2f;
                }
                if (action == 1) {
                    spin += GameConstants.TORQUE / 3f;
                    if (spin > GameConstants.MAX_SPIN / 2f) spin = GameConstants.MAX_SPIN / 2f;
                }
                if (action == 0) {
                    if (spin > 0) {
                        spin -= GameConstants.FRICTION;
                        if (spin < 0) {
                            spin = 0;
                        }
                    } else if (spin < 0) {
                        spin += GameConstants.FRICTION;
                        if (spin > 0) {
                            spin = 0;
                        }
                    }
                }

                angle += spin;
                while (angle > 180) angle -= 360;
                while (angle < -180) angle += 360;

                if (nextAction <= 0) {
                    action = random.nextInt(3) - 1;
                    nextAction = random.nextInt(40) + 20;
                }

                for (TrackObject o : objects) {
                    if (o.collide(this)) {
                        if (o instanceof Boost) {
                            boost();
                        } else if (o instanceof MissingTrack) {
                            fallOff();
                        } else if (o instanceof Wall || o instanceof Train) {
                            crash(player.getDistance());
                        }
                    }
                }
                if (collide(player)) {
                    crash(player.getDistance());
                    player.crash();
                }
            }
        } else if (player.getDistance() >= triggerDistance) {
            triggered = true;
        }
    }

    public void draw(float delta, float playerDistance, SpriteBatch spriteBatch) {
        float drawDistance = (float)Math.pow(2 , playerDistance - (distance));
        GameUtils.setColorByDrawDistance(drawDistance, spriteBatch);
        if (timeout > 0) {
            explosionAnimator.draw(spriteBatch, GameConstants.SCREEN_WIDTH / 2 - 25, 15,
                    25, GameConstants.SCREEN_HEIGHT / 2 - 15, 50, 50, drawDistance, drawDistance, angle);
        } else if (active) {
            spriteBatch.draw(texture, GameConstants.SCREEN_WIDTH / 2 - 25, 15 - (float)Math.pow(fallOff, 1.55),
                    25, GameConstants.SCREEN_HEIGHT / 2 - 15 + (float)Math.pow(fallOff, 1.55), 50, 50,
                    drawDistance * (float)Math.pow(0.975f, fallOff), drawDistance * (float)Math.pow(0.975f, fallOff), angle, 0, 0, 50, 50, false, false);
        }
    }

    public float getDistance() {
        return this.distance;
    }
    public float getSpeed() {
        return this.speed;
    }
    public float getAngle() {
        return this.angle;
    }

}
