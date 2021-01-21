package com.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import com.model.entity.PlayerType;
import com.model.entity.Portal;
import com.settings.Settings;

public class LevelEditor {
	Level level;
	Entity currentEntity;
	int tileSize = Settings.tileSize;
	LevelEditor(Level level) {
		this.level = level;
		currentEntity = new Portal(0, 0, PlayerType.fly);
	}
	void render(Graphics g) {
		// Draw grid lines
		g.setColor(Color.gray);
		for (int i = -1000; i < 1000; i++) {
			// Horizontal
			g.drawLine((int)-1e9, i * tileSize, (int)1e9, i * tileSize);
			// Vertical
			g.drawLine(i * tileSize, (int)-1e9, i * tileSize, (int)1e9);
		}
	}
	void registerClick(Point p) {
		p.translate((int)level.getCameraX(), (int)level.getCameraY());
		p.setLocation(p.x - Math.floorMod(p.x, tileSize), p.y - Math.floorMod(p.y, tileSize));
		currentEntity.setX(p.x);
		currentEntity.setY(p.y);
		level.addEntity(currentEntity.copy());
	}
}
