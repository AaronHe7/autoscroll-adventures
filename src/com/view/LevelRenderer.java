package com.view;

import java.awt.Graphics;
import java.awt.Graphics2D;

import com.model.Entity;
import com.model.Level;

public class LevelRenderer {
	Level level;
	Graphics g;
	
	public LevelRenderer(Level level) {
		this.level = level;
	}
	
	// Temporary
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(-level.getCameraX(), -level.getCameraY());
		for (Entity e : level.getEntitiesCopy()) {
			e.render(g);
		}
		g2d.translate(0, 0);
	}
}
