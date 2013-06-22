package com.mcdonald.balloonsamurai.world;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mcdonald.balloonsamurai.Assets;
import com.mcdonald.balloonsamurai.gameobjects.Hero;
import com.mcdonald.balloonsamurai.levelone.LevelOneAssets;
import com.sun.swing.internal.plaf.basic.resources.basic;

public class WorldRenderer {
	
	static final float FRUSTUM_WIDTH = 25;
	static final float FRUSTUM_HEIGHT = 15;
	World world;
	OrthographicCamera guiCamera;
	SpriteBatch spriteBatch;
	
	ShapeRenderer shapeRenderer;

	public WorldRenderer(SpriteBatch spriteBatch, World world) {
		this.world = world;
		this.guiCamera = new OrthographicCamera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
		this.guiCamera.position.set(FRUSTUM_WIDTH / 2 + 1, FRUSTUM_HEIGHT / 2, 0);
		this.spriteBatch = spriteBatch;
		
		shapeRenderer = new ShapeRenderer();
	}
	
	public void render() {
		if(world.hero.position.x > guiCamera.position.x && guiCamera.position.x < (FRUSTUM_WIDTH + (FRUSTUM_WIDTH / 2) - 1)) {
			guiCamera.position.x = world.hero.position.x;
		} else if(world.hero.position.x < guiCamera.position.x && guiCamera.position.x > (FRUSTUM_WIDTH / 2) + 1) {
			guiCamera.position.x = world.hero.position.x;
		}
		
		guiCamera.update();
		spriteBatch.setProjectionMatrix(guiCamera.combined);
		renderBackground();
		renderObjects();
	}
	
	public void renderBackground() {
		spriteBatch.disableBlending();
		spriteBatch.begin();
		spriteBatch.draw(LevelOneAssets.backgroundRegion, 0, 0, 25 * 2, 15);
		spriteBatch.end();
	}
	
	public void renderObjects() {
		spriteBatch.enableBlending();
		spriteBatch.begin();
		renderHero();
		renderEnemy();
		spriteBatch.end();
		
		shapeRenderer.setProjectionMatrix(guiCamera.combined);
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(Color.ORANGE);
		shapeRenderer.rect(	world.hero.objectBounds.x, 
							world.hero.objectBounds.y, 
							world.hero.objectBounds.width, 
							world.hero.objectBounds.height);
		shapeRenderer.end();
		
		shapeRenderer.setProjectionMatrix(guiCamera.combined);
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(Color.RED);
		shapeRenderer.rect(	world.enemy.objectBounds.x, 
							world.enemy.objectBounds.y, 
							world.enemy.objectBounds.width, 
							world.enemy.objectBounds.height);
		shapeRenderer.end();
		
		shapeRenderer.setProjectionMatrix(guiCamera.combined);
		
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(Color.RED);
		shapeRenderer.rect(	world.enemyOne.objectBounds.x, 
							world.enemyOne.objectBounds.y, 
							world.enemyOne.objectBounds.width, 
							world.enemyOne.objectBounds.height);
		shapeRenderer.end();
		
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(Color.RED);
		shapeRenderer.rect(	world.enemyTwo.objectBounds.x, 
							world.enemyTwo.objectBounds.y, 
							world.enemyTwo.objectBounds.width, 
							world.enemyTwo.objectBounds.height);
		shapeRenderer.end();
		
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(Color.RED);
		shapeRenderer.rect(	world.enemyThree.objectBounds.x, 
							world.enemyThree.objectBounds.y, 
							world.enemyThree.objectBounds.width, 
							world.enemyThree.objectBounds.height);
		shapeRenderer.end();
		
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(Color.RED);
		shapeRenderer.rect(	world.enemyFour.objectBounds.x, 
							world.enemyFour.objectBounds.y, 
							world.enemyFour.objectBounds.width, 
							world.enemyFour.objectBounds.height);
		shapeRenderer.end();
		
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(Color.RED);
		shapeRenderer.rect(	world.enemyFive.objectBounds.x, 
							world.enemyFive.objectBounds.y, 
							world.enemyFive.objectBounds.width, 
							world.enemyFive.objectBounds.height);
		shapeRenderer.end();
	}
	
	private void renderHero() {
		TextureRegion hero = Assets.hero;
		
		spriteBatch.draw(hero, world.hero.position.x, world.hero.position.y, 1, 1);
	}
	
	private void renderEnemy() {
		TextureRegion enemy = Assets.enemy;
		
		spriteBatch.draw(enemy, world.enemy.position.x, world.enemy.position.y, 1, 1);
		spriteBatch.draw(enemy, world.enemyOne.position.x, world.enemyOne.position.y, 1, 1);
		spriteBatch.draw(enemy, world.enemyTwo.position.x, world.enemyTwo.position.y, 1, 1);
		spriteBatch.draw(enemy, world.enemyThree.position.x, world.enemyThree.position.y, 1, 1);
		spriteBatch.draw(enemy, world.enemyFour.position.x, world.enemyFour.position.y, 1, 1);
		spriteBatch.draw(enemy, world.enemyFive.position.x, world.enemyFive.position.y, 1, 1);
	}
	
}
