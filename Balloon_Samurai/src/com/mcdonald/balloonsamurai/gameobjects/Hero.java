package com.mcdonald.balloonsamurai.gameobjects;

import com.mcdonald.balloonsamurai.world.World;

public class Hero extends DynamicGameObject {
	
	public static final int 	HERO_STATE_FLING 	= 0;
	public static final int 	HERO_STATE_FALL 	= 1;
	public static final float 	HERO_MOVE_VELOCITY 	= 1;
	public static final float 	HERO_WIDTH 			= 1.0f;
	public static final float 	HERO_HEIGHT 		= 1.0f;
	
	int state;
	
	public Hero(float x, float y) {
		super(x, y, HERO_WIDTH, HERO_HEIGHT);
		state = HERO_STATE_FALL;
	}
	
	public void update(float deltaTime) {
		velocity.add(World.gravity.x * deltaTime, (float)(World.gravity.y + 11.5) * deltaTime);
		position.add(velocity.x * deltaTime, velocity.y * deltaTime);
		objectBounds.x = position.x;
		objectBounds.y = position.y;
		
		if(velocity.y > 0) {
			if(state != HERO_STATE_FLING) {
				state = HERO_STATE_FLING;
			}
		}
		
		if(velocity.y < 0) {
			if(state != HERO_STATE_FALL) {
				state = HERO_STATE_FALL;
			}
		}
	}
	
	public void moveHero() {
		velocity.y = HERO_MOVE_VELOCITY;
		state = HERO_STATE_FLING;
	}
	
	public void moveHero(float x, float y, String xDirection, String yDirection) {
		if(xDirection.equals("left")) {
			x *= -1;
		}
		
		if(yDirection.equals("down")) {
			y *= -1;
		}
		
		velocity.x = x;
		velocity.y = y;
	}

}
