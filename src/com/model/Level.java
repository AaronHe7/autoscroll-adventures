package com.model;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

import com.model.entity.Platform;
import com.model.entity.Player;
import com.settings.Settings;
import com.view.View;

public class Level implements Serializable {
	private static final long serialVersionUID = 5534080263939263275L;
	
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private ArrayList<Entity> entitiesCopy = new ArrayList<Entity>();
	private Player player;
	private float endX = 0;
	// The top-left location of the camera
	float cameraX, cameraY;
	private boolean win = false;

	public int attempts = 0;
	
	public Level() {
		addEntity(new Player(0, -Settings.tileSize, this));
		addEntity(new Platform(-1000, 0, 16000, 1000));
		updateEntitiesCopy();
		cameraX = player.getX() - 200;
		cameraY = player.getY() - View.HEIGHT/2 + Settings.tileSize/2;
	}

	public void addEntity(Entity entity) {
		if (entity.id == EntityId.Player) {
			assert(entities.size() == 0);
		}
		entities.add(entity);
		if (entity.getWidth() + entity.getX() > endX) {
			endX = entity.getWidth() + entity.getX();
		}
	}

	public void removeEntity(Entity entity) {
		entities.remove(entity);
	}
	
	public void update() {
		updateEntitiesCopy();
		for (Entity entity : entitiesCopy) {
			entity.update();
		}
		cameraX = player.getX() - 200;
		cameraY = player.getY() - View.HEIGHT/2 + Settings.tileSize/2;
	}

	
	public void updateEntitiesCopy() {
		float px = 0, py = 0;
		if (player != null) {
			px = player.getX();
			py = player.getY();
		}
		if (entitiesCopy.size() > entities.size()) {
			resetCopy();
		}
		for (int i = entitiesCopy.size(); i < entities.size(); i++) {
			entitiesCopy.add(entities.get(i).copy());
		}
		player = (Player) entitiesCopy.get(0);
		if (player != null) {
			player.setX(px);
			player.setY(py);
		}
	}

	public ArrayList<Entity> getEntitiesCopy() {
		updateEntitiesCopy();
		return entitiesCopy;
	}

	public void resetCopy() {
		entitiesCopy = new ArrayList<Entity>();
		for (int i = 0; i < entities.size(); i++) {
			entitiesCopy.add(entities.get(i).copy());
		}
	}

	public void removeLastEntity() {
		if (entities.size() > 1) {
			entities.remove(entities.size() - 1);
		}
	}
	
	public Player getPlayer() {
		updateEntitiesCopy();
		player = (Player) entitiesCopy.get(0);
		return player;
	}
	
	public float getEndX() {
		return endX;
	}
	
	public void setEndX(float x) {
		endX = x;
	}
	
	public float getCameraX() {
		return cameraX;
	}

	public float getCameraY() {
		return cameraY;
	}
	
	public boolean getWin() {
		return win;
	}

	public void setWin(boolean b) {
		win = b;
	}

	public void setCameraX(float f) {
		cameraX = f;
	}
	public void setCameraY(float f) {
		cameraY = f;
	}
}
