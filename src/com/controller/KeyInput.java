package com.controller;

import java.awt.Canvas;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	Controller controller;
	public KeyInput(Controller controller, Canvas canvas) {
		this.controller = controller;
		canvas.addKeyListener(this);
	}
	
	public void keyPressed(KeyEvent e) {
		controller.registerKeyInput(e);
	}
	
	public void keyReleased(KeyEvent e) {
		controller.registerKeyRelease(e);
	}
}
