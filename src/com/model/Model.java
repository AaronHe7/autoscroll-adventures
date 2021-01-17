package com.model;

import java.awt.Graphics;
import java.awt.Point;

import com.model.entity.Platform;
import com.model.entity.Spike;

public class Model {
	private Level currentLevel = null;
	private LevelEditor levelEditor = null;
	private STATE state = STATE.levelEditor;
	public enum STATE {
		menu(),
		level(),
		levelEditor()
	}

	public Model() {
		currentLevel = new Level();
		levelEditor = new LevelEditor(currentLevel);
	}

	public Level getCurrentLevel() {
		return currentLevel;
	}
	
	public STATE getState() {
		return state;
	}

	public void update() {
		if (state == STATE.level) {
			currentLevel.update();
		}
	}

	public void render(Graphics g) {
		if (state == STATE.level) {
			currentLevel.render(g);
		} else if (state == STATE.levelEditor) {
			currentLevel.render(g);
			levelEditor.render(g);
		}
	}
	
	public void setState(STATE state) {
		this.state = state;
	}

	public void registerClick(Point p) {
		if (state == STATE.levelEditor) {
			levelEditor.registerClick(p);
		} else {
			currentLevel.jump();
		}
	}
}