package com.model;

import java.awt.Graphics;
import java.awt.Point;

import com.filestream.LevelLoader;
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
	
	public enum STATE {
		menu(),
		level(),
		levelEditor()
	}

	public Model() {
		LevelLoader ll = new LevelLoader();
		//currentLevel = ll.loadLevel("level 1");
//		currentLevel = new Level();
//		levelEditor = new LevelEditor(currentLevel);
//		levelRenderer = new LevelRenderer(currentLevel);
	}

	public Level getCurrentLevel() {
		return currentLevel;
	}
	
	public STATE getState() {
		return state;
	}

	public void update() {
		tick++;
		if (!clicking && tick - endClickTick == Settings.bufferClickTime) {
			buffering = false;
		}

		if (state == STATE.level) {
			currentLevel.update();
			if (clicking || buffering) {
				if (buffering) {
					buffering = false;
				}
				currentLevel.getPlayer().jump();
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
		clicking = true;
		if (state == STATE.levelEditor) {
			levelEditor.registerClick(p);
		}
	}
	
	public void registerRelease() {
		clicking = false;
		buffering = true;
		endClickTick = tick;
	}
}