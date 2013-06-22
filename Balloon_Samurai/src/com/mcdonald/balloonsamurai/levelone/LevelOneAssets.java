package com.mcdonald.balloonsamurai.levelone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class LevelOneAssets {
	public static Music music;
	public static Texture background;
	public static TextureRegion backgroundRegion;
	
	public static Texture loadTexture(String file) {
		return new Texture(Gdx.files.internal(file));
	}
	
	public static void load() {
		// set ups music
		music = Gdx.audio.newMusic(Gdx.files.internal("data/music/levelone.mp3"));
		music.setLooping(true);
		music.setVolume(0.1f);
		music.play();
		
		background = loadTexture("data/levelone/background.png");
		backgroundRegion = new TextureRegion(background, 0, 0, 1600, 480);
	}
	
	public static void playSound(Sound sound) {
		sound.play();
	}

	public void dispose() {
		// TODO Auto-generated method stub
		music.dispose();
	}
}
