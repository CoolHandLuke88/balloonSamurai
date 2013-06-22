package com.mcdonald.balloonsamurai.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mcdonald.balloonsamurai.gameobjects.Hero;
import com.mcdonald.balloonsamurai.gameobjects.Enemy;
public class World {
	
	public interface WorldListener {
		public void hit();
	}
	
	public static final float WORLD_WIDTH  			 = 25 * 2;
	public static final float WORLD_HEIGHT 			 = 15;
	public static final int   WORLD_STATE_RUNNING 	 = 0;
	public static final int   WOROLD_STATE_GAME_OVER = 1;
	public static final Vector2 gravity = new Vector2(0, -12);
	
	public final Hero hero;
	public final WorldListener listener;
	public Enemy enemy;
	public  Enemy enemyOne;
	public  Enemy enemyTwo;
	public  Enemy enemyThree;
	public  Enemy enemyFour;
	public  Enemy enemyFive;
	
	public int state;
	
	public World(WorldListener listener) {
		this.hero = new Hero(12, 14);
		this.enemy = new Enemy(10, 10);
		this.enemyOne = new Enemy(20,10);
		this.enemyTwo = new Enemy(30,10);
		this.enemyThree = new Enemy(40,10);
		this.enemyFour = new Enemy(25,10);
		this.enemyFive = new Enemy(35,10);
		this.listener = listener;
		generateLevel();
		
		this.state = WORLD_STATE_RUNNING;
	}
	
	private void generateLevel() {
		float maxFling = Hero.HERO_MOVE_VELOCITY * Hero.HERO_MOVE_VELOCITY / (2 * -gravity.y);
	}
	
	public void update(float deltaTime) {
		updateHero(deltaTime);
		updateEnemy(deltaTime);
		checkGameOver();
	}
	
	private void updateHero(float deltaTime) {
		hero.update(deltaTime);
	}
	
	private void updateEnemy(float deltaTime){
		enemy.update(deltaTime);
		enemyOne.update(deltaTime);
		enemyTwo.update(deltaTime);
		enemyThree.update(deltaTime);
		enemyFour.update(deltaTime);
		enemyFive.update(deltaTime);
	}
	
	private void checkGameOver() {
		if(hero.position.x < 0 || hero.position.y < 0) {
			state = WOROLD_STATE_GAME_OVER;
		}
	}

}
