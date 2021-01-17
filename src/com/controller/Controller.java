package com.controller;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

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
		}
	}

	public void registerKeyRelease(KeyEvent e) {
		
	}
}
