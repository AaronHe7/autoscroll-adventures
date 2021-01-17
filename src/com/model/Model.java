package com.model;

import java.awt.Graphics;
import java.awt.Point;

import com.filestream.LevelLoader;
import com.view.LevelRenderer;

public class Model {
	private Level currentLevel = null;
	private LevelEditor levelEditor = null;
	private LevelRenderer levelRenderer = null;
	private STATE state = STATE.levelEditor;
	public int tick = 0;
	public enum STATE {
		menu(),
		level(),
		levelEditor()
	}

	public Model() {
		LevelLoader ll = new LevelLoader();
		currentLevel = ll.loadLevel("level 1");
		//currentLevel = new Level();
		levelEditor = new LevelEditor(currentLevel);
		levelRenderer = new LevelRenderer(currentLevel);
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
		tick = (tick + 1) % (int)1e9;
		if (state == STATE.levelEditor && tick % 200 == 0) {
		}
	}

	public void render(Graphics g) {
		if (state == STATE.level) {
			levelRenderer.render(g);
		} else if (state == STATE.levelEditor) {
			levelRenderer.render(g);
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