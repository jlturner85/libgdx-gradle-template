package com.sadc.game.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * @author f536985 (Tom Farello)
 */
public class GameUtil {

    public static float setColorByDrawDistance(float drawDistance, SpriteBatch spriteBatch) {
        float color = 1;
        if (drawDistance < 0.23f) {
            color = (drawDistance - 0.03f) * 5;
            if (color < 0) {
                color = 0;
            }
        }
        spriteBatch.setColor(color, color, color, 1);
        //spriteBatch.setColor(color, color, color, 1);
        return color;
    }

}
