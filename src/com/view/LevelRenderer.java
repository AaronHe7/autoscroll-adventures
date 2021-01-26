package com.view;

import java.awt.Color;
import java.awt.Font;
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
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		float cx = level.getCameraX();
		float cy = level.getCameraY();
		g2d.translate(-cx, -cy);
		for (Entity e : level.getEntitiesCopy()) {
			e.render(g);
		}
		g2d.translate(cx, cy);
		g.setColor(Color.black);
		g.drawRect(View.WIDTH/2 - 300, 20, 600, 20);
		g.fillRect(View.WIDTH/2 - 300, 20, (int) (600 * level.getPlayer().getX()/level.getEndX()), 20);
		g.setFont(new Font("Arial", 1, 30));
		g.drawString((int) (level.getPlayer().getX()*100/level.getEndX()) + "%", View.WIDTH/2 - 370, 40);	
	}
}
