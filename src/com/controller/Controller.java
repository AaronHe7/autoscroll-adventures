package com.controller;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.model.Model;
import com.model.entity.Platform;
import com.model.entity.PlayerType;
import com.model.entity.Portal;
import com.model.entity.Spike;

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
	
	private boolean editing = false;

	public void registerKeyInput(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_UP || key == KeyEvent.VK_SPACE) {
			model.registerClick(new Point(-10000, -10000));
		}
		if (model.getState() == Model.STATE.level) {
			if (key == KeyEvent.VK_ESCAPE) {
				model.setState(Model.STATE.menu);
			}
		}
		if (editing) {
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
			model.getCurrentLevel().removeLastEntity();
		} else if (key == KeyEvent.VK_ESCAPE) {
			model.setState(Model.STATE.menu);
			model.saveLevel();
		} else if (key == KeyEvent.VK_S) {
			model.saveLevel();
		}
			if (key == KeyEvent.VK_Z) {
				model.getLevelEditor().setEntity(new Spike(0, 0));
			}
			if (key == KeyEvent.VK_X) {
				model.getLevelEditor().setEntity(new Platform(0, 0, 50, 50));
			}
			if (key == KeyEvent.VK_C) {
				model.getLevelEditor().setEntity(new Portal(0, 0, PlayerType.fly));
			}
			if (key == KeyEvent.VK_V) {
				model.getLevelEditor().setEntity(new Portal(0, 0, PlayerType.jump));
			}
			if (key == KeyEvent.VK_LEFT) {
				model.getCurrentLevel().getPlayer().setX(model.getCurrentLevel().getPlayer().getX() - 200);
			}
			if (key == KeyEvent.VK_RIGHT) {
				model.getCurrentLevel().getPlayer().setX(model.getCurrentLevel().getPlayer().getX() + 200);
			}
			if (key == KeyEvent.VK_UP) {
				model.getCurrentLevel().getPlayer().setY(model.getCurrentLevel().getPlayer().getY() - 200);
			}
			if (key == KeyEvent.VK_DOWN) {
				model.getCurrentLevel().getPlayer().setY(model.getCurrentLevel().getPlayer().getY() + 200);
			}
		}
	}

	public void registerKeyRelease(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_UP || key == KeyEvent.VK_SPACE) {
			model.registerRelease();
		}
	}
}
