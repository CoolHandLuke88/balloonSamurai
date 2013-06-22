package com.mcdonald.balloonsamurai;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.FPSLogger;
import com.mcdonald.balloonsamurai.mainmenu.MainMenu;

public class BalloonSamurai extends Game {
	
	FPSLogger fpsLogger;
	
	@Override
	public void create() {
		//setScreen(new LevelOne(this));
		setScreen(new MainMenu(this));
		fpsLogger = new FPSLogger();
	}
	
	@Override
	public void render() {
		super.render();
		fpsLogger.log();
	}
	
	@Override
	public void dispose() {
		super.dispose();
		
		getScreen().dispose();
	}
	
}
