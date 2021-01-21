package com.model.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;

import com.model.Entity;
import com.model.EntityId;
import com.model.Helper;

public class Platform extends Entity {
	private static final long serialVersionUID = 4872306252502273694L;

	public Platform(float x, float y, float width, float height) {
		super(x, y);
		this.width = width;
		this.height = height;
		this.id = EntityId.Platform;
	}
	
	public Platform(Platform platform) {
		super(platform);
	}

	public void update() {
		
	}
	
	public void handleCollision(Player player) {
		if (Helper.boundingBoxCollision(player, this)) {
			player.setVy(0);
			// Above platform. Check if player's lower edge is above the entity's upper edge.
			if (player.getPy() + player.getHeight() <= y) {
				player.setOnGround(true);
				player.setY(y - player.getHeight());
			} 
			// Below platform. Check if player's upper edge is below the entity's lower edge.
			else if (player.getPy() >= y + height) {
				player.setY(y + player.getHeight());
			} 
			// Player hits side of platform and dies.
			else {
				player.reset();
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect((int)x, (int)y, (int)width, (int)height);
	}

	public Entity copy() {
		return new Platform(this);
	}

	public Shape getShape() {
		return (Shape) new Rectangle((int)x, (int)y, (int)width, (int)height);
	}
}
