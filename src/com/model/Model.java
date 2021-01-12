package com.model;

import com.model.entity.Platform;
import com.model.entity.Player;
import com.model.entity.Spike;

public class Model {
	private Level currentLevel = null;

	public Model() {
		currentLevel = new Level();
		currentLevel.addEntity(new Player(0, -70, currentLevel));
		currentLevel.addEntity(new Platform(0, 50, currentLevel.getEndX(), 40));
		currentLevel.addEntity(new Platform(0, -350, currentLevel.getEndX(), 40));
		for (int i = 1; i < 100; i++) {
			currentLevel.addEntity(new Spike(i * 500, 0));
			currentLevel.addEntity(new Spike(i * 500 + 50, 0));
			currentLevel.addEntity(new Spike(i * 500 + 100, 0));
		}
	}

	public Level getCurrentLevel() {
		return currentLevel;
	}
}