package com.commontimegames.flapper;

import com.badlogic.gdx.Game;
import com.commontimegames.flapper.screens.GameScreen;

public class FlapperGame extends Game {

    GameScreen gameScreen;
	
	@Override
	public void create () {
        gameScreen = new GameScreen();
		setScreen(gameScreen);
	}

	@Override
	public void dispose () {
		gameScreen.dispose();
	}
}
