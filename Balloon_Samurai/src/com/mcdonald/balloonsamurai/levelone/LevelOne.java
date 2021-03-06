package com.mcdonald.balloonsamurai.levelone;

//import com.badlogic.gdx.Application.ApplicationType;
import org.omg.CORBA.PUBLIC_MEMBER;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.TimeUtils;
import com.mcdonald.balloonsamurai.Assets;
import com.mcdonald.balloonsamurai.mainmenu.MainMenu;
import com.mcdonald.balloonsamurai.overlaptester.OverlapTester;
import com.mcdonald.balloonsamurai.world.World;
import com.mcdonald.balloonsamurai.world.World.WorldListener;
import com.mcdonald.balloonsamurai.world.WorldRenderer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mcdonald.balloonsamurai.gameover.GameOver;

public class LevelOne implements Screen {
	private static final float SCREEN_WIDTH = 800;
	private static final float SCREEN_HEIGHT = 480;
	
	static final int GAME_READY 	= 0;
	static final int GAME_RUNNING 	= 1;
	static final int GAME_PAUSED 	= 2;
	static final int GAME_LEVEL_END = 3;
	static final int GAME_OVER 		= 4;
	
	Game game;
	
	float startX, startY;
	long startTime;
	int state;
	OrthographicCamera guiCamera;
	Vector3 touchPoint;
	SpriteBatch spriteBatch;
	World world;
	WorldListener worldListener;
	WorldRenderer worldRenderer;
	
	Logger logger;
	
	private int score;
	private String scoreName;
	BitmapFont bitmapFontName;
	SpriteBatch batch;
	
	public LevelOne(Game game) {
		this.game = game;
		
		Assets.load();
		LevelOneAssets.load();
		
		state = GAME_READY;
		
		// for score
		score = 0;
		scoreName = "score: 0";
		bitmapFontName = new BitmapFont();
		
		guiCamera = new OrthographicCamera(SCREEN_WIDTH, SCREEN_HEIGHT);
		guiCamera.position.set(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, 0);
		touchPoint = new Vector3();
		spriteBatch = new SpriteBatch();
		batch = new SpriteBatch();
		worldListener = new WorldListener() {
			
			@Override
			public void hit() {
				// TODO Auto-generated method stub
				
			}
		};
		
		world = new World(worldListener);
		worldRenderer = new WorldRenderer(spriteBatch, world);
		
		Gdx.input.setInputProcessor(new LevelOneInput());
		
		logger = new Logger("DEBUG", Logger.INFO);
	
	}
	
	private void update(float deltaTime) {
		if(deltaTime > 0.1f) {
			deltaTime = 0.1f;
		}
		
		switch (state) {
		case GAME_READY:
			updateReady();
			break;
		case GAME_RUNNING:
			updateRunning(deltaTime);
			break;
		case GAME_PAUSED:
//			updatePaused();
			break;
		case GAME_LEVEL_END:
//			updateLevelEnd();
			break;
		case GAME_OVER:
//			updateGameOver();
			break;
		default:
			break;
		}
	}
	
	private void updateReady() {
		if(Gdx.input.justTouched() && state == GAME_READY) {
			state = GAME_RUNNING;
		}
	}
	
	private void updateRunning(float deltaTime) {
		if(Gdx.input.justTouched()) {
			guiCamera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));		
		}
		
		// Use this to determine the device that the application is running on
//		ApplicationType applicationType = Gdx.app.getType();
		
		if(Gdx.input.isKeyPressed(Keys.SPACE)) {
			world.hero.moveHero();
		}
		
		if(world.hero.position.x <= 1) {
			world.hero.velocity.x *= -1;
			//System.out.println("colided with other object!!!!");
		} else if(world.hero.position.x >= (World.WORLD_WIDTH - 2)) { 
			world.hero.velocity.x *= -1;
			//System.out.println("colided with other object!!!!");
		}else if(world.hero.position.y <= 0) {
			world.hero.velocity.y *= -1;
			//System.out.println("colided with other object!!!!");
		} else if(world.hero.position.y >= World.WORLD_HEIGHT) {
			world.hero.velocity.y *= -1;
			//System.out.println("colided with other object!!!!");
		}
	
		Rectangle herobubble = world.hero.objectBounds;
		Rectangle enemyBubble = world.enemy.objectBounds;
		Rectangle enemyOneBubble =	world.enemyOne.objectBounds;
		Rectangle enemyTwoBubble =	world.enemyTwo.objectBounds;
		Rectangle enemyThreeBubble =	world.enemyThree.objectBounds;
		Rectangle enemyFourBubble =	world.enemyFour.objectBounds;
		Rectangle enemyFiveBubble =	world.enemyFive.objectBounds;
		
		
		if(OverlapTester.overlapRectangles(herobubble, enemyBubble)      || 
		   OverlapTester.overlapRectangles(herobubble, enemyOneBubble)   ||
		   OverlapTester.overlapRectangles(herobubble, enemyTwoBubble)   ||
		   OverlapTester.overlapRectangles(herobubble, enemyThreeBubble) ||
		   OverlapTester.overlapRectangles(herobubble, enemyFourBubble)  ||
		   OverlapTester.overlapRectangles(herobubble, enemyFiveBubble))
		{
			System.out.println("overlapRectangles : colided with other object!!!!");
			
			score++;
			scoreName = "score: " + score;
			if(score >= 100)
			{
				LevelOneAssets.music.dispose();
				game.setScreen(new GameOver(game));
			}
		}
		
		 /*float newX = world.hero.position.x;
		  boolean ranIntoBlocks = false;
		  for (Block b : world.getBlocks()) {
		   if (Intersector.overlapRectangles(bounds,b.getBounds())) {
		    ranIntoBlocks = true;
		    break;  //we found a collision, no need to check the rest of the blocks
		   }
		  }
		                //step 3
		  if ( newX < 0 || newX > 10 - bounds.getWidth() || ranIntoBlocks) {
		   position.add(velocity.tmp().mul(-delta)); //undo the changes
		   updateBounds();
		  }*/
		
		world.update(deltaTime); // change over time, accel
		
		
	}
	
	public void draw(float deltaTime) {
		GLCommon glCommon = Gdx.gl;
		glCommon.glClear(GL20.GL_COLOR_BUFFER_BIT);
		glCommon.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
		
		worldRenderer.render();
		
		guiCamera.update();
		spriteBatch.setProjectionMatrix(guiCamera.combined);
		spriteBatch.enableBlending();
		spriteBatch.begin();
		spriteBatch.end();
		
		batch.begin(); 
		bitmapFontName.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		bitmapFontName.draw(batch, scoreName, 10, Gdx.graphics.getHeight()-10); 
		batch.end();
	}

	@Override
	public void render(float delta) {
		update(delta);
		draw(delta);		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
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
		
	}
	
	public class LevelOneInput implements InputProcessor {

		@Override
		public boolean keyDown(int keycode) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean keyUp(int keycode) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean keyTyped(char character) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean touchDown(int screenX, int screenY, int pointer,
				int button) {
			// TODO Auto-generated method stub
			startX = screenX;
			startY = screenY;
			startTime = TimeUtils.millis();
			
			return false;
		}

		@Override
		public boolean touchUp(int screenX, int screenY, int pointer, int button) {
			// TODO Auto-generated method stub
			
			float distanceX = (screenX - startX);
			float distanceY = (screenY - startY);
			String directionX = "right";
			String directionY = "down";
			
			if(distanceX < 0) {
				distanceX *= -1;
				directionX = "left";
			}
			
			if(distanceY < 0) {
				distanceY *= -1;
				directionY = "up";
			}
			
			long time = TimeUtils.millis() - startTime;
			
			float velocityX = (distanceX / time) * 10;
			float velocityY = (distanceY / time) * 10;
			
			world.hero.moveHero(velocityX, velocityY, directionX, directionY);
			
			return false;
		}

		@Override
		public boolean touchDragged(int screenX, int screenY, int pointer) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean mouseMoved(int screenX, int screenY) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean scrolled(int amount) {
			// TODO Auto-generated method stub
			return false;
		}
		
	}

}
