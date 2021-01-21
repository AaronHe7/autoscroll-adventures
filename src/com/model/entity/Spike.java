package com.model.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Area;

import com.model.Entity;
import com.model.EntityId;
import com.model.Helper;
import com.settings.Settings;

public class Spike extends Entity {
	private static final long serialVersionUID = -5607511962172181837L;

	public Spike(float x, float y) {
		super(x, y);
		this.id = EntityId.Spike;
		this.width = Settings.tileSize;
		this.height = Settings.tileSize;
	}
	
	public Spike(Spike spike) {
		super(spike);
	}
	
	public void handleCollision(Player player) {
		if (Helper.boundingBoxCollision(player, this)) {
			Area area1 = new Area(getShape());
			Area area2 = new Area(player.getShape());
			area1.intersect(area2);
			if (!area1.isEmpty()) {
				// Player dies
				player.reset();
			}
		}
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
