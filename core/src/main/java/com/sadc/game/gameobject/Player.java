package com.sadc.game.gameobject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sadc.game.GameConstants;
import com.sadc.game.animation.Animator;

/**
 * Fuck Javadocs.
 *
 * @author f536985 (Tom Farello)
 */
public class Player {

    private static final int CAR_SPRITE_ROWS = 2;
    private static final int CAR_SPRITE_COLUMNS = 2;
    private static final float CAR_FRAME_DURATION = 0.1f;
    private static final int EXPLOSION_SPRITE_ROWS = 2;
    private static final int EXPLOSION_SPRITE_COLUMNS = 3;
    private static final float EXPLOSION_FRAME_DURATION = 0.5f;
//    private static final int WALK_SPRITE_ROWS = 5;
//    private static final int WALK_SPRITE_COLUMNS = 6;
//    private static final float WALK_FRAME_DURATION = 0.07f;

    private Track track;

    private Sound idleSound;
    private Sound firstGearSound;
    private Sound secondGearSound;
    private Sound thirdGearSound;
    private Sound fourthGearSound;
    private Sound fifthGearSound;
    private Sound explosionSound;

    private float distance;
    private long time;
    private float speed;
    private float topSpeed;
    private float acceleration;
    private float angle;
    private float spin;

    private float boost;
    private float dBoost;

    private boolean explosion;
    private int timeout;
    private int invulnerability;
    private int fallOff;

    private int leftKey;
    private int rightKey;
    private int brakeKey;

    private Texture texture;

    private Animator carAnimator;
    private Texture explosionTexture;
    private Animator explosionAnimator;
//    private Texture walkerTexture;
//    private Animator walkerAnimator;

    public Player(int playerNum, Track track, int selectedCar) {
        this.track = track;

        distance = 0;
        speed = 0;
        topSpeed = 4 + selectedCar / 2f;
        acceleration = 0.003f - (0.0005f * selectedCar);
        angle = 0;
        spin = 0;
        boost = 1;
        dBoost = 0;

        if (playerNum == 1) {
            leftKey = GameConstants.P1_LEFT;
            rightKey = GameConstants.P1_RIGHT;
            brakeKey = GameConstants.P1_B;
        } else if (playerNum == 2) {
            leftKey = GameConstants.P2_LEFT;
            rightKey = GameConstants.P2_RIGHT;
            brakeKey = GameConstants.P2_B;
        }
        /*idleSound = Gdx.audio.newSound(Gdx.files.internal("soundeffects/car/loop_0.wav"));
        firstGearSound = Gdx.audio.newSound(Gdx.files.internal("soundeffects/car/loop_1_0.wav"));
        secondGearSound = Gdx.audio.newSound(Gdx.files.internal("soundeffects/car/loop_2_0.wav"));
        thirdGearSound = Gdx.audio.newSound(Gdx.files.internal("soundeffects/car/loop_3_0.wav"));
        fourthGearSound = Gdx.audio.newSound(Gdx.files.internal("soundeffects/car/loop_4_0.wav"));*/
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("soundeffects/hit.wav"));
        texture = new Texture("car" + (selectedCar + 1) + "_4frame.png");
        explosionTexture = new Texture("explosion_6frames.png");
        carAnimator = new Animator(texture, this.CAR_SPRITE_COLUMNS, this.CAR_SPRITE_ROWS, this.CAR_FRAME_DURATION);
        explosionAnimator = new Animator(explosionTexture, EXPLOSION_SPRITE_COLUMNS, EXPLOSION_SPRITE_ROWS, EXPLOSION_FRAME_DURATION);
//        walkerTexture = new Texture("animation_sheet.png");
//        walkerAnimator = new Animator(walkerTexture, WALK_SPRITE_COLUMNS, WALK_SPRITE_ROWS, WALK_FRAME_DURATION);
    }

    public void boost() {
        if (boost <= 1) {
            boost = GameConstants.BOOST;
        }
        dBoost = GameConstants.D_BOOST;
    }

    public void fallOff() {
        if (timeout <= 0) {
            fallOff = 1;
            timeout = 120;
            invulnerability = 240;
        }
    }

    public void crash() {
        crash(120);
    }

    public void crash(int timeout) {
        if (invulnerability <= 0) {
            if (this.timeout <= 0) {
                this.timeout = timeout;
                invulnerability = timeout + 120;
                speed = 0;
                explosion = true;
            }
            explosionSound.play();
        }
    }

    public void bonusTime(int bonusFrames) {
        track.bonusTime(bonusFrames);
    }

    public void finish() {
        track.finish();
    }

    public void changeBackground(Texture texture) {
        track.changeBackground(texture);
    }

    public void dispose() {
        texture.dispose();
//        walkerTexture.dispose();
        /*idleSound.dispose();
        firstGearSound.dispose();
        secondGearSound.dispose();
        thirdGearSound.dispose();
        fourthGearSound.dispose(); */
        explosionSound.dispose();
    }

    public void update(float delta) {
        time++;
        if (invulnerability > 0) {
            invulnerability--;
        }
        if (fallOff > 0) {
            speed -= acceleration * 20;
            if (speed < 0) {
                speed = 0;
            }
            distance += speed / 60f;
            fallOff++;

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
        } else if (timeout <= 0) {
            if (Gdx.input.isKeyPressed(brakeKey)) {
                speed -= acceleration * 35;
                if (speed < 0) {
                    speed = 0;
                }
            } else {
                speed += acceleration * Math.max(1, speed / topSpeed) * ((topSpeed * boost) - speed);
            }
            distance += speed / 60f;

            dBoost += GameConstants.D_D_BOOST;
            if (boost > 1) {
                boost += dBoost;
                if (boost < 1) {
                    boost = 1;
                    dBoost = 0;
                }
            }

            boolean left = Gdx.input.isKeyPressed(leftKey);
            boolean right = Gdx.input.isKeyPressed(rightKey);

            if (left) {
                spin -= GameConstants.TORQUE;
                if (spin < -GameConstants.MAX_SPIN) spin = -GameConstants.MAX_SPIN;
            }
            if (right) {
                spin += GameConstants.TORQUE;
                if (spin > GameConstants.MAX_SPIN) spin = GameConstants.MAX_SPIN;
            }
            if (!left && !right) {
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
        }
        if (timeout > 0) {
            timeout--;
            if (timeout == 0) {
                speed = 0;
                fallOff = 0;
                angle = 0;
                spin = 0;
                boost = 1;
                dBoost = 0;
                explosion = false;
            }
        }
    }

    public void draw (float delta, SpriteBatch spriteBatch) {
        if (explosion) {
            GameUtils.setColorByDrawDistance(1, spriteBatch);
            explosionAnimator.draw(spriteBatch, GameConstants.SCREEN_WIDTH / 2 - 25, 15,
                    25, GameConstants.SCREEN_HEIGHT / 2 - 15, 50, 50, 1, 1, angle);
        } else {
            GameUtils.setColorByDrawDistance(1, spriteBatch);
            if (fallOff > 0)
                System.out.println(fallOff);
            carAnimator.draw(spriteBatch, GameConstants.SCREEN_WIDTH / 2 - 25, 15 - (float)Math.pow(fallOff, 1.55),
                    25, (GameConstants.SCREEN_HEIGHT / 2) - 15 + (float)Math.pow(fallOff, 1.55), 50, 50, (float)Math.pow(0.975f, fallOff), (float)Math.pow(0.975f, fallOff), angle);//, 0, 0, 50, 50, false, false);
//            walkerAnimator.draw(spriteBatch, 50,50);
//            spriteBatch.draw(texture, GameConstants.SCREEN_WIDTH / 2 - 25, 15,
//                    25, GameConstants.SCREEN_HEIGHT / 2 - 15, 50, 50, 1, 1, angle, 0, 0, 50, 50, false, false);
        }
    }

    public float getDistance() {
        return this.distance;
    }
    public long getTime() {
        return this.time;
    }
    public float getSpeed() {
        return this.speed;
    }
    public float getAngle() {
        return this.angle;
    }
}
