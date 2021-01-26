package com.model;

import java.awt.Graphics;
import java.awt.Point;

import com.filestream.LevelLoader;
import com.filestream.LevelSaver;
import com.settings.Settings;
import com.view.LevelRenderer;

public class Model {
	private Level currentLevel;
	private LevelEditor levelEditor;
	private LevelRenderer levelRenderer;
	private Menu menu;
	private STATE state = STATE.menu;
	private boolean clicking = false;
	private boolean buffering = false;
	private int tick = 0;
	private int endClickTick = -(int)1e9;
	private int levelId = -1;
	public boolean beaten[] = new boolean[3];
	
	public enum STATE {
		menu(),
		level(),
		levelEditor()
	}

	public Model() {
		for (int i = 0; i < 3; i++) {
			beaten[i] = false;
		}
		beaten[0] = beaten[1] = true;
		menu = new Menu(this);
	}
	
	public void loadLevel(int level) {
		levelId = level;
		LevelLoader ll = new LevelLoader();
		currentLevel = ll.loadLevel("level " + level);
		if (currentLevel == null) {
			currentLevel = new Level();
		}
		levelEditor = new LevelEditor(currentLevel);
		levelRenderer = new LevelRenderer(currentLevel);
		state = STATE.level;
		tick = 0;
	}
	
	public void saveLevel() {
		LevelSaver ls = new LevelSaver();
		ls.saveLevel(getCurrentLevel(), "level " + levelId);
	}

	public Level getCurrentLevel() {
		return currentLevel;
	}
	
	public STATE getState() {
		return state;
	}

	public void update() {
		if (!clicking && tick - endClickTick == Settings.bufferClickTime) {
			buffering = false;
		}

		if (state == STATE.level) {
			tick++;
			currentLevel.update();
			if (clicking || buffering) {
				if (buffering) {
					buffering = false;
				}
				currentLevel.getPlayer().jump();
			}
			if (currentLevel.getWin()) {
				state = STATE.menu;
				beaten[levelId - 1] = true;
			}
		}
	}

	public void render(Graphics g) {
		if (state == STATE.level) {
			levelRenderer.render(g);
		} else if (state == STATE.levelEditor) {
			levelRenderer.render(g);
			levelEditor.render(g);
		} else if (state == STATE.menu) {
			menu.render(g);
		}
	}
	
	public void setState(STATE state) {
		this.state = state;
	}

	public void registerClick(Point p) {
		if (state == STATE.levelEditor) {
			levelEditor.registerClick(p);
		} else if (state == STATE.level && tick > 0) {
			clicking = true;
		} else if (state == STATE.menu) {
			menu.registerClick(p);
		}
	}
	
	public void registerRelease() {
		if (state == STATE.level && clicking) { 
			clicking = false;
			buffering = true;
			endClickTick = tick;
		}
	}

	public LevelEditor getLevelEditor() {
		return levelEditor;
	}
}