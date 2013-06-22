package com.mcdonald.balloonsamurai;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	
	public static Texture heroAtlas;
	public static Texture enemyAtlas;
	public static TextureRegion hero;
	public static TextureRegion enemy;
	
	public static Texture loadTexture(String file) {
		return new Texture(Gdx.files.internal(file));
	}
	
	public static void load() {
		// load hero assets
		heroAtlas = loadTexture("data/gameobjects/samurloon2.png");
		hero = new TextureRegion(heroAtlas, 0, 0, 32, 32);
		
		// load enemy assets
		enemyAtlas = loadTexture("data/gameobjects/samurai_balloon.png");
		enemy = new TextureRegion(enemyAtlas, 0, 0, 32, 32); 
	}
	
	public static void playSound(Sound sound) {
		sound.play();
	}

}
