package com.controller;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.filestream.LevelSaver;
import com.model.Model;

public class Controller {
	Model model;
	
	public Controller(Model model) {
		this.model = model;
	}
	
	public void registerMouseInput(MouseEvent e) {
		Point p = e.getPoint();
		model.registerClick(p);
	}
	
	public void registerMouseRelease(MouseEvent e) {
		model.registerRelease();
	}

	public void registerKeyInput(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_Q) {
			// Swap between editing and playing level
			if (model.getState() == Model.STATE.level) {
				model.setState(Model.STATE.levelEditor);
			} else if (model.getState() == Model.STATE.levelEditor) {
				model.setState(Model.STATE.level);
			}
		} else if (key == KeyEvent.VK_R) {
			model.getCurrentLevel().resetCopy();
		} else if (key == KeyEvent.VK_U) { 
			// temp
			model.getCurrentLevel().removeLastEntity();
		} else if (key == KeyEvent.VK_ESCAPE) {
			LevelSaver ls = new LevelSaver();
			ls.saveLevel(model.getCurrentLevel(), "level 1");
			System.exit(1);
		}
	}

	public void registerKeyRelease(KeyEvent e) {
	}
}
