package com.model;

import com.model.entity.Platform;
import com.model.entity.Player;
import com.model.entity.Spike;

public class Model {
	private Level currentLevel = null;

	public Model() {
		currentLevel = new Level();
		currentLevel.addEntity(new Player(0, -70, currentLevel));
		currentLevel.addEntity(new Platform(-50, 50, 5000, 40));
		currentLevel.addEntity(new Platform(-50, -250, 1000000000, 40));
		currentLevel.addEntity(new Spike(400, 0));
		currentLevel.addEntity(new Spike(450, 0));
	}

	public Level getCurrentLevel() {
		return currentLevel;
	}
}