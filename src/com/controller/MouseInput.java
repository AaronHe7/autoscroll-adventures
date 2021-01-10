package com.controller;

import java.awt.Canvas;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {
	Controller controller;
	public MouseInput(Controller controller, Canvas canvas) {
		this.controller = controller;
		canvas.addMouseListener(this);
	}
	
	public void mousePressed(MouseEvent e) {
		controller.registerMouseClick();
	}

	public void mouseReleased(MouseEvent e) {
		controller.registerMouseRelease();
	}
}
