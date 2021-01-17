package com.model;

import java.awt.Graphics;
import java.awt.Shape;

public abstract class Entity {
	protected float x, y, width, height;
	// Velocities
	protected float vx = 0, vy = 0;
	// Accelerations
	protected float ax = 0, ay = 0;
	protected EntityId id;
	
	public Entity(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Entity(Entity e) {
		this.x = e.x;
		this.y = e.y;
		this.vx = e.vx;
		this.vy = e.vy;
		this.ax = e.ax;
		this.ay = e.ay;
		this.width = e.width;
		this.height = e.height;
		this.id = e.id;
	}
	
	public abstract void update();
	public abstract void render(Graphics g);
	public abstract Entity copy();

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getVx() {
		return vx;
	}

	public void setVx(float vx) {
		this.vx = vx;
	}

	public float getVy() {
		return vy;
	}

	public void setVy(float vy) {
		this.vy = vy;
	}

	public float getAx() {
		return ax;
	}

	public void setAx(float ax) {
		this.ax = ax;
	}

	public float getAy() {
		return ay;
	}

	public void setAy(float ay) {
		this.ay = ay;
	}

	public EntityId getId() {
		return id;
	}

	public void setId(EntityId id) {
		this.id = id;
	}
	
	public Shape getShape() {
		return null;
	}
}
