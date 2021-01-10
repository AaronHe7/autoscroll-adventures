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
	private float spawnX, spawnY;
	private float px, py;
	private boolean onGround = false;
	
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
		System.out.println(y);
		doCollision();
		if (onGround) {
			ay = 0;
		} else {
			ay = 0.2f;
		}
	}

	private void doCollision() {
		onGround = false;
		for (Entity e : level.getEntities()) {
			boolean collision = this.x <= e.getX() + e.getWidth() && this.x + this.width >= e.getX() && this.y <= e.getY() + e.getHeight() && this.y + this.width >= e.getY();
			if (e.getId() == EntityId.Platform) {
				if (collision) {
					vy = 0;
					// Above platform. Check if player's lower edge is above the entity's upper edge.
					if (py + height <= e.getY()) {
						onGround = true;
						System.out.println("top");
						y = e.getY() - height;
					} 
					// Below platform. Check if player's upper edge is below the entity's lower edge.
					else if (py >= e.getY() + e.getHeight()) {
						System.out.println("bot");
						y = e.getY() + e.getHeight();
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
	
	public boolean isOnGround() {
		return onGround;
	}
}
