package com.mcdonald.balloonsamurai.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class GameObject {
	
	public final Vector2 position;
	public final Rectangle objectBounds;
	
	public GameObject(float x, float y, float width, float height) {
		this.position = new Vector2(x, y);
		this.objectBounds = new Rectangle(x - width / 2, y - height / 2, width, height);
	}

}
