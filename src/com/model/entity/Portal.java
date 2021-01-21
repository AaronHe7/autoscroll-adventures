package com.model.entity;

import java.awt.Color;
import java.awt.Graphics;

import com.model.Entity;
import com.model.Helper;

public class Portal extends Entity {
	private static final long serialVersionUID = 4674110574245015662L;

	PlayerType transform;
	public Portal(int x, int y, PlayerType transform) {
		super(x, y);
		this.width = 50;
		this.height = 100;
		this.transform = transform;
	}
	public Portal(Portal portal) {
		super(portal);
		this.transform = portal.transform;
	}
	public void update() {
	}
	
	public void handleCollision(Player player) {
		if (Helper.boundingBoxCollision(player, this)) {
			player.setType(transform);
		}
	}
	
	public void render(Graphics g) {
		if (transform == PlayerType.jump) {
			g.setColor(Color.red);
		} else if (transform == PlayerType.fly) {
			g.setColor(Color.blue);
		}
		g.fillRect((int)x, (int)y, (int)width, (int)height);
	}

	public Entity copy() {
		return new Portal(this);
	}
}
