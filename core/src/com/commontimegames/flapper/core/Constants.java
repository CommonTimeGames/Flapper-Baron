package com.commontimegames.flapper.core;

/**
 * Created by c14726 on 5/1/14.
 */
public class Constants {
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 480;

    public static final float WORLD_TO_SCREEN = 20;
    public static final float SCREEN_TO_WORLD = 1/ WORLD_TO_SCREEN;

    public static final float WORLD_WIDTH = GAME_WIDTH * SCREEN_TO_WORLD;
    public static final float WORLD_HEIGHT = GAME_HEIGHT * SCREEN_TO_WORLD;

    public static final float WORLD_CENTER_X = WORLD_WIDTH * 0.5f;
    public static final float WORLD_CENTER_Y = WORLD_HEIGHT * 0.5f;

    public static final double SCROLL_HEIGHT_THRESHOLD = 0.50;
    public static final float WORLD_SCROLL_SPEED = 0.1f;
    public static final float CONTENT_OFFSET = 6f;

    public static final float BOX_GRAVITY = 20;
    public static final float BOX_STEP_TIME = 1/45f;

    public static final String GROUND_NAME = "ground";

}
