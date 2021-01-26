package com.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import com.view.View;


public class Menu {
	private int levels = 3;
	private Model model;
	
	public Menu(Model model) {
		this.model = model;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.black);
		g.setFont(new Font("Arial", 1, 100));
		g.drawString("Autoscroll Adventures", View.WIDTH/2 - 500, View.HEIGHT/5);	
		g.setFont(new Font("Arial", 100, 50));
		for (int i = 1; i <= levels; i++) {
			g.drawString("Level " + i + " " + (model.beaten[i - 1] ? "(done)" : ""), View.WIDTH/2 - 100, View.HEIGHT * (i + 1)/5);	
		}
	}
	
	public void registerClick(Point p) {
		for (int i = 1; i <= 3; i++) {
			checkButton(i, p);
		}
	}
	private boolean checkButton(int level, Point p) {
		if (p.x >= View.WIDTH/2 - 100 && p.x <= View.WIDTH/2 + 60) {
			if (p.y >= View.HEIGHT * (level + 1)/5 - 50 && p.y <= View.HEIGHT * (level + 1)/5) {
				model.loadLevel(level);
				return true;
			}
		}
		return false;
	}
}
