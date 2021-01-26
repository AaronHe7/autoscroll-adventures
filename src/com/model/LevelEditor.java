package com.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import com.model.entity.Spike;
import com.settings.Settings;
import com.view.View;

public class LevelEditor {
	Level level;
	Entity currentEntity;
	int tileSize = Settings.tileSize;
	LevelEditor(Level level) {
		this.level = level;
		//currentEntity = new Portal(0, 0, PlayerType.jump);
		currentEntity = new Spike(0, 0);
	}
	void render(Graphics g) {
		level.setCameraX(level.getPlayer().getX() - 200);
		level.setCameraY(level.getPlayer().getY() - View.HEIGHT/2 + Settings.tileSize/2);
		Graphics2D g2d = (Graphics2D) g;
		float cx = level.getCameraX();
		float cy = level.getCameraY();
		g2d.translate(-cx, -cy);
		// Draw grid lines
		g.setColor(Color.gray);
		for (int i = -1000; i < 1000; i++) {
			// Horizontal
			g.drawLine((int)-1e9, i * tileSize, (int)1e9, i * tileSize);
			// Vertical
			g.drawLine(i * tileSize, (int)-1e9, i * tileSize, (int)1e9);
		}
		g2d.translate(cx, cy);
	}
	void registerClick(Point p) {
		p.translate((int)level.getCameraX(), (int)level.getCameraY());
		p.setLocation(p.x - Math.floorMod(p.x, tileSize), p.y - Math.floorMod(p.y, tileSize));
		currentEntity.setX(p.x);
		currentEntity.setY(p.y);
		level.addEntity(currentEntity.copy());
	}
	public void setEntity(Entity e) {
		currentEntity = e;
	}
}
