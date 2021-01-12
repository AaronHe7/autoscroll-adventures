package com.model;

import java.awt.Graphics;
import java.util.ArrayList;

import com.model.entity.Player;
import com.settings.Settings;
import com.view.LevelRenderer;

public class Level {
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private Player player;
	private LevelRenderer levelRenderer;
	private float endX = 10000;
	
	public Level() {
		levelRenderer = new LevelRenderer(this);
	}

	public void addEntity(Entity entity) {
		if (entity.id == EntityId.Player) {
			this.player = (Player) entity;
		}
		entities.add(entity);
	}

	public void removeEntity(Entity entity) {
		entities.remove(entity);
	}
	
	public void update() {
		player.update();
	}

	public void render(Graphics g) {
		levelRenderer.render(g);
	}
	
	public void jump() {
		if (player.isOnGround()) {
			player.setVy(Settings.jumpVelocity);
		}
	}
	
	public ArrayList<Entity> getEntities() {
		return entities;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public float getEndX() {
		return endX;
	}
	
	public void setEndX(float x) {
		endX = x;
	}
}
