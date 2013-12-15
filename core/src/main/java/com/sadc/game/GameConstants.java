package com.sadc.game;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;

public class GameConstants {
    public static final int SCREEN_HEIGHT = 480;
    public static final int SCREEN_WIDTH = 640;

    public static final float MAX_SPIN = 4f;
    public static final float TORQUE = 0.5f;
    public static final float FRICTION = 0.15f;
    public static final float BOOST = 1.05f;
    public static final float D_BOOST = 0.1f;
    public static final float D_D_BOOST = -0.005f;

    public static final int ESCAPE_KEY = Keys.ESCAPE;
    public static final int P1_UP = Keys.UP;
    public static final int P1_LEFT = Keys.LEFT;
    public static final int P1_DOWN = Keys.DOWN;
    public static final int P1_RIGHT = Keys.RIGHT;
    public static final int P1_A = Keys.CONTROL_LEFT;
    public static final int P1_B = Keys.ENTER;
    public static final int P1_C = Keys.SPACE;
    public static final int P1_X = Keys.SHIFT_LEFT;
    public static final int P1_Y = Keys.Z;
    public static final int P1_Z = Keys.X;
    public static final int P1_COIN = Keys.NUM_5;
    public static final int P1_START = Keys.NUM_1;
    public static final int P1_EXTRA_BUTTON = Keys.C;
    public static final int P2_UP = Keys.R;
    public static final int P2_LEFT = Keys.D;
    public static final int P2_DOWN = Keys.F;
    public static final int P2_RIGHT = Keys.G;
    public static final int P2_A = Keys.A;
    public static final int P2_B = Keys.S;
    public static final int P2_C = Keys.Q;
    public static final int P2_X = Keys.W;
    public static final int P2_Y = Keys.Y;
    public static final int P2_Z = Keys.Z;
    public static final int P2_COIN = Keys.NUM_6;
    public static final int P2_START = Keys.NUM_2;
    public static final int P2_EXTRA_BUTTON_1 = Keys.J;
    public static final int P2_EXTRA_BUTTON_2 = Keys.L;
    public static boolean OLD_P1_ENTER_PRESSED = false;
    public static boolean OLD_P2_ENTER_PRESSED = false;
    public static boolean OLD_P1_LEFT_PRESSED = false;
    public static boolean OLD_P1_RIGHT_PRESSED = false;
    public static final String LONDON_FONT = "boston.ttf";
    public static final int MENU_FONT_SIZE = 20;
    public static boolean OLD_ESCAPE_PRESSED = false;
    public static Sound currentMusic;
    public static final float MUSIC_VOLUME = 0.1f;
    public static final int NUMBER_OF_TRACKS = 1;
    public static final String TRACK_1_LEADERBOARD = "leaderboard.txt";
    public static final String TRACK_1_NAME = "Track 1";
    public static final String[] INITIAL_CHARACTER_ARRAY= {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N"
            , "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    public static final int CAR1_SPRITE_ROWS = 2;
    public static final int CAR1_SPRITE_COLUMNS = 2;
    public static final float CAR1_FRAME_DURATION = 0.1f;
    public static int CURRENT_SELECTED_CAR;
}
