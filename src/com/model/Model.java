package com.model;

import com.model.entity.Platform;
import com.model.entity.Player;

public class Model {
	private Level currentLevel = null;

	public Model() {
		currentLevel = new Level();
		currentLevel.addEntity(new Player(0, -70, currentLevel));
		currentLevel.addEntity(new Platform(200, -30, 5000, 40));
		currentLevel.addEntity(new Platform(0, 0, 5000, 40));
	}

	public Level getCurrentLevel() {
		return currentLevel;
	}
}