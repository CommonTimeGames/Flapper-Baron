package com.commontimegames.flapper.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.commontimegames.flapper.FlapperGame;
import com.commontimegames.flapper.core.Constants;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.width = Constants.GAME_WIDTH;
        config.height = Constants.GAME_HEIGHT;
        config.title = "Flapper Baron";

		new LwjglApplication(new FlapperGame(), config);
	}
}
