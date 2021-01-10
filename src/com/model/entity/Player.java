package com.model.entity;

import java.awt.Color;
import java.awt.Graphics;

import com.model.Entity;
import com.model.EntityId;

public class Player extends Entity {
	// Previous x and y
	float px, py;
	
	public Player(float x, float y) {
		super(x, y);
		this.size = 50;
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
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, (int)size, (int)size);
	}
}
