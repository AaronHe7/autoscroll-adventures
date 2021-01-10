package com.model;

import com.model.entity.Player;

public class Model {
	private Level currentLevel = null;

	public Model() {
		currentLevel = new Level();
		currentLevel.addEntity(new Player(0, 400));
	}

	public Level getCurrentLevel() {
		return currentLevel;
	}
}