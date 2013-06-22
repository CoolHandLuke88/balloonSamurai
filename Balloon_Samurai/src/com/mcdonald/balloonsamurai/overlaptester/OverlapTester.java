package com.mcdonald.balloonsamurai.overlaptester;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class OverlapTester {
	
	public static boolean overlapRectangles(Rectangle rectangle1, Rectangle rectangle2) {
		if(rectangle1.x < rectangle2.x + rectangle2.width && rectangle1.x + rectangle1.width > rectangle2.x && rectangle1.y < rectangle2.y + rectangle2.height && rectangle1.y + rectangle1.height > rectangle2.y) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean pointInRectangle(Rectangle rectangle, Vector2 point) {
		return rectangle.x <= point.x && rectangle.width >= point.x && rectangle.y <= point.y && rectangle.y + rectangle.height >= point.y;
	}
	
	public static boolean pointInRectangle(Rectangle rectangle, float x, float y) {
		return rectangle.x <= x && rectangle.x + rectangle.width >= x && rectangle.y <= y && rectangle.y + rectangle.height >= y;
	}

}
