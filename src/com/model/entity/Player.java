package com.model.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import com.model.Entity;
import com.model.EntityId;
import com.model.Level;

public class Player extends Entity {
	Level level;
	// Previous x and y
	float spawnX, spawnY;
	float px, py;
	
	public Player(float x, float y, Level level) {
		super(x, y);
		spawnX = x;
		spawnY = y;
		this.level = level;
		this.width = 50;
		this.height = 50;
		this.id = EntityId.Player;
		vx = 4;
	}

	public void update() {
		px = x;
		py = y;
		x += vx + ax/2;
		vx += ax;
		y += vy + ay/2;
		vy += ay;
		doCollision();
	}

	private void doCollision() {
		for (Entity e : level.getEntities()) {
			boolean collision = getShape().intersects((Rectangle2D) e.getShape());
			if (e.getId() == EntityId.Platform) {
				if (collision) {
					vy = 0;
					// Above platform. Check if player's lower edge is above the entity's upper edge.
					if (py + height <= e.getY()) {
						y = e.getY() - height;
					} 
					// Below platform. Check if player's upper edge is below the entity's lower edge.
					else if (py >= e.getY() + e.getHeight()) {
						y = e.getY();
					} 
					// Player hits side of platform and dies.
					else {
						x = spawnX;
						y = spawnY;
					}
				}
			}
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, (int)width, (int)height);
	}

	public Shape getShape() {
		return (Shape) new Rectangle((int)x, (int)y, (int)width, (int)height);
	}
}
