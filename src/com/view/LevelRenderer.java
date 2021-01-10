package com.view;

import java.awt.Graphics;
import java.awt.Graphics2D;

import com.model.Entity;
import com.model.Level;

public class LevelRenderer {
	Level level;
	Graphics g;
	// The top-left location of the camera
	float cameraX = 0, cameraY = 0;
	
	public LevelRenderer(Level level) {
		this.level = level;
	}
	
	// Temporary
	public void render(Graphics g) {
		cameraX = this.level.getPlayer().getX();
		cameraY = this.level.getPlayer().getY();
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(-cameraX + 200, -cameraY + 300);
		for (Entity e : level.getEntities()) {
			e.render(g);
		}
		g2d.translate(0, 0);
	}
}
