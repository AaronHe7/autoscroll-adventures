package com.model.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;

import com.model.Entity;
import com.model.EntityId;

public class Platform extends Entity {
	public Platform(float x, float y, float width, float height) {
		super(x, y);
		this.width = width;
		this.height = height;
		this.id = EntityId.Platform;
	}

	public void update() {
		
	}

	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect((int)x, (int)y, (int)width, (int)height);
	}

	public Shape getShape() {
		return (Shape) new Rectangle((int)x, (int)y, (int)width, (int)height);
	}
}
