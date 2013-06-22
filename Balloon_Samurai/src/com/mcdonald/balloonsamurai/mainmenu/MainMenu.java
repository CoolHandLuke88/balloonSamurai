package com.mcdonald.balloonsamurai.mainmenu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.mcdonald.balloonsamurai.levelone.LevelOne;

public class MainMenu implements Screen {
	// enum for button selection in menu
	public enum MenuButtonSelected {
		DEFAULT, NEWGAME, CONTINUE, EXIT
	}
	private static final float SCREEN_WIDTH = 800;
	private static final float SCREEN_HEIGHT = 480;
	public MenuButtonSelected menuButtonSelected = MenuButtonSelected.DEFAULT;
	Game game;
	Stage stage;
	TextureAtlas atlas;
	Texture background;
	TextureRegion backgroundRegion;
	Skin skin;
	SpriteBatch batch;
	Button bgImage;
	Button startButton;
	Button continueButton;
	Button exitButton;
	Music music;
	OrthographicCamera guiCamera;
	
	public MainMenu(Game game) {
		this.game = game;
		
		guiCamera = new OrthographicCamera(SCREEN_WIDTH, SCREEN_HEIGHT);
		guiCamera.position.set(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, 0);
		
	}
	
	public static Texture loadTexture(String file) {
		return new Texture(Gdx.files.internal(file));
	}
	
	public void draw(float deltaTime) {
		GLCommon glCommon = Gdx.gl;
		glCommon.glClearColor(0.0f, 0.0f, 0.0f, 1.0f); // red green blue alpha
		glCommon.glClear(GL20.GL_COLOR_BUFFER_BIT);
		guiCamera.update();
	}

	@Override
	public void render(float delta) {
		draw(delta);
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		
		batch.begin();
		stage.draw();
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		if(stage == null)
			stage = new Stage(width, height, true);
		stage.clear();
		Gdx.input.setInputProcessor(stage);
		
		// setup UI for menu
		// Background image, used button
		// TODO: Fix to use actual background image instead of button
		TextButtonStyle bgStyle = new TextButtonStyle();
		bgStyle.up = skin.getDrawable("bg-menu");
		bgImage = new Button(bgStyle);
		bgImage.setWidth(680);
		bgImage.setHeight(600);
		bgImage.setX(Gdx.graphics.getWidth() * 0.5f - bgImage.getWidth() * 0.5f);
		bgImage.setY(Gdx.graphics.getHeight() * 0.5f - bgImage.getHeight() * 0.5f);
		stage.addActor(bgImage);
		
		// The start Button
		TextButtonStyle startButtonStyle = new TextButtonStyle();
		startButtonStyle.up = skin.getDrawable("btnstartmenu");
		startButton = new Button(startButtonStyle);
		startButton.setWidth(100);
		startButton.setHeight(32);
		startButton.setX(Gdx.graphics.getWidth() * 0.5f - startButton.getWidth() * 0.5f);
		startButton.setY(Gdx.graphics.getHeight() * 0.22f - startButton.getHeight() * 0.22f);
		stage.addActor(startButton);
		
		// Continue Button
		TextButtonStyle continueButtonStyle = new TextButtonStyle();
		continueButtonStyle.up = skin.getDrawable("btncontinuemenu");
		continueButton = new Button(continueButtonStyle);
		continueButton.setWidth(100);
		continueButton.setHeight(32);
		continueButton.setX(Gdx.graphics.getWidth() * 0.5f - continueButton.getWidth() * 0.5f);
		continueButton.setY(Gdx.graphics.getHeight() * 0.12f - continueButton.getHeight() * 0.12f);
		stage.addActor(continueButton);
		
		// Exit Button
		TextButtonStyle exitButtonStyle = new TextButtonStyle();
		exitButtonStyle.up = skin.getDrawable("btnexitmenu");
		exitButton = new Button(exitButtonStyle);
		exitButton.setWidth(100);
		exitButton.setHeight(32);
		exitButton.setX(Gdx.graphics.getWidth() * 0.5f - exitButton.getWidth() * 0.5f);
		exitButton.setY(Gdx.graphics.getHeight() * 0.02f - exitButton.getHeight() * 0.02f);
		stage.addActor(exitButton);
		
		// setup interaction for menu buttons
		// start button
		startButton.addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
				return true;
			}
			
			public void touchUp (InputEvent event, float x, float y, int pointer, int button){
				System.out.println("start button clicked!!!!");
				game.setScreen(new LevelOne(game));
			}
		});
		// continue button
		continueButton.addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
				return true;
			}
			
			public void touchUp (InputEvent event, float x, float y, int pointer, int button){
				System.out.println("continue button clicked!!!!");
			}
		});
		// exit button
		// continue button
		exitButton.addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
				return true;
			}
			
			public void touchUp (InputEvent event, float x, float y, int pointer, int button){
				System.out.println("exit button clicked!!!!");
			}
		});
	}

	@Override
	public void show() {
		// set ups music
		music = Gdx.audio.newMusic(Gdx.files.internal("data/music/BGMenuMusic.mp3"));
		music.setLooping(true);
		music.setVolume(0.6f);
		music.play();

		// setup UI
		batch = new SpriteBatch();
		atlas = new TextureAtlas("data/mainmenu/menuassets.pack");
		skin = new Skin();
		skin.addRegions(atlas);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		dispose();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		batch.dispose();
		atlas.dispose();
		skin.dispose();
		skin.addRegions(atlas);
		stage.dispose();
		music.dispose();
	}

}
