package com.model.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Shape;

import com.model.Entity;
import com.model.EntityId;
import com.settings.Settings;

public class Spike extends Entity {
	public Spike(float x, float y) {
		super(x, y);
		this.id = EntityId.Spike;
		this.width = Settings.tileSize;
		this.height = Settings.tileSize;
	}
	
	public Spike(Spike spike) {
		super(spike);
	}
	
	public void update() {
	}

	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillPolygon((Polygon) getShape());
	}
	
	public Entity copy() {
		return new Spike(this);
	}

	public Shape getShape() {
		int xPoints[] = {(int)(x + width/2), (int)x, (int)(x + width)};
		int yPoints[] = {(int)y, (int)(y + height), (int)(y + height)};
		return new Polygon(xPoints, yPoints, 3);
	}
}
