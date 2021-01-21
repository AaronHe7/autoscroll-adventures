package com.model;

public class Helper {
	public static boolean boundingBoxCollision(Entity entity1, Entity entity2) {
		return entity1.x <= entity2.getX() + entity2.getWidth() && entity1.x + entity1.width >= entity2.getX() && entity1.y <= entity2.getY() + entity2.getHeight() && entity1.y + entity1.width >= entity2.getY();
	}
}
