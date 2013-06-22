package com.mcdonald.balloonsamurai.gameobjects;

import com.mcdonald.balloonsamurai.world.World;
import com.mcdonald.balloonsamurai.world.WorldRenderer;
import com.mcdonald.balloonsamurai.world.World.WorldListener;

public class Enemy extends DynamicGameObject{

	World world;
	WorldListener worldListener;
	WorldRenderer worldRenderer;
	
	public static final float 	Enemy_WIDTH 			= 1.0f;
	public static final float 	Enemy_HEIGHT 			= 1.0f;
	public float	Enemy_StartXCoord 					= 0.0f;
	public float	Enemy_StartYCoord 					= 0.0f;
	
	public enum EnemyState {
		IDLE, SEEK, ATTACK, HIDE
	}
	public EnemyState enemyState = EnemyState.IDLE;

	public Enemy(float x, float y) {
		super(x, y, Enemy_WIDTH, Enemy_HEIGHT);
//		state = HERO_STATE_FALL;
	}
	
	public void update(float deltaTime) {
		float movement = 0.0f;
		if(position.y > 10)
		{
			movement = -21.5f;
		}
		else
		{
			movement = 21.5f;
		}
		
		velocity.add(World.gravity.x * deltaTime, (float)(World.gravity.y + movement) * deltaTime);
		position.add(0, velocity.y * deltaTime);
		objectBounds.x = position.x;
		objectBounds.y = position.y;
		//velocity.y *= 1;
		// grab initial position of enemy
		if(Enemy_StartXCoord == 0.0f && Enemy_StartYCoord == 0.0f)
		{
			//Enemy_StartXCoord = enemy.position.x;
			//Enemy_StartYCoord = enemy.position.y;
		}
		
		switch(enemyState)
		{
			case IDLE:
			{
				//IdelState ();
			}
			break;
			case SEEK:
			{
				SeekState ();
			}
			break;
			case ATTACK:
			{
				AttackState ();
			}
			break;
			case HIDE:
			{
				HideState ();
			}
			break;
			
		}

	}
	
	public void IdelState ()
	{
	
		//	velocity.y *= 2.0f;
	
		if(position.y > 10)
		{
			velocity.y *= -2.0f;
		}
		else if(position.y < 10)
		{
			velocity.y *= 2.0f;
		}
	}
	public void AttackState ()
	{
		
	}
	public void SeekState ()
	{
		
	}
	public void HideState ()
	{
		
	}
}
