package com.view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import com.model.Model;
import com.settings.Settings;


public class View extends Canvas implements Runnable {
	private static final long serialVersionUID = -6520493287321791823L;
	
	private Thread thread;
	private Model model;
	private boolean running = false;
	private static final int HEIGHT = 600, WIDTH = HEIGHT * 16/9;

	public View(Model model) {
		new Window(WIDTH, HEIGHT, "Autoscroll Adventures", this);
		this.model = model;
	}
	

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// You don't need to click on screen to gain input
		this.requestFocus();
		// Standard game loop
		long lastTime = System.nanoTime();
		double amountOfTicks = Settings.FPS;
		double ns = 1000000000/amountOfTicks;
		double delta =  0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime)/ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				delta--;
			}
			if (running) {
				render();
			}
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void update() {
		model.getCurrentLevel().update();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		model.getCurrentLevel().render(g);
		
		g.dispose();
		bs.show();
	}
}
