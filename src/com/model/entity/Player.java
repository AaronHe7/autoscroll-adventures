package com.model.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;

import com.model.Entity;
import com.model.EntityId;
import com.model.Level;
import com.settings.Settings;

public class Player extends Entity {
	private static final long serialVersionUID = -5313370497235931449L;

	Level level;
	// Previous x and y
	private float spawnX, spawnY;
	private float px, py;
	private boolean onGround = false;
	private PlayerType type = PlayerType.jump;
	
	public Player(float x, float y, Level level) {
		super(x, y);
		spawnX = x;
		spawnY = y;
		this.level = level;
		this.width = this.height = Settings.tileSize;
		this.id = EntityId.Player;
		vx = Settings.scrollSpeed;
	}
	
	public Player(Player player) {
		super(player);
		spawnX = x;
		spawnY = y;
		this.level = player.level;
	}

	public void update() {
		if (x == spawnX && y == spawnY) {
			level.attempts++;
			System.out.println("Attempts: " + level.attempts);
		}
		px = x;
		py = y;
		x += vx + ax/2;
		vx += ax;
		y += vy + ay/2;
		vy += ay;
		if (vy > Settings.terminalVelocity) {
			vy = Settings.terminalVelocity;
		}
		if (onGround) {
			ay = 0;
		} else {
			ay = Settings.gravity;
		}
		doCollision();
	}

	private void doCollision() {
		onGround = false;
		for (Entity e : level.getEntitiesCopy()) {
			// cheats
//			if (e.getId() != EntityId.Spike)
			e.handleCollision(this);
		}
		if (x > level.getEndX()) {
			level.setWin(true);
		}
	}

	public void jump() {
		if (onGround || (type == PlayerType.fly && vy >= 0)) {
			vy = Settings.jumpVelocity;
		}
	}
	
	public void render(Graphics g) {
		if (type == PlayerType.jump) {
			g.setColor(Color.red);
		} else if (type == PlayerType.fly) {
			g.setColor(Color.blue);
		}
		g.fillRect((int)x, (int)y, (int)width, (int)height);
	}

	public float getPy() {
		return py;
	}
	
	public Shape getShape() {
		return new Rectangle((int)x, (int)y, (int)width, (int)height);
	}

	public void setOnGround(boolean onGround) {
		this.onGround = onGround;
	}
	
	public boolean isOnGround() {
		return onGround;
	}
	
	public Entity copy() {
		return new Player(this);
	}
	
	public void reset() {
		px = x = spawnX;
		py = y = spawnY;
		ax = ay = vy = 0;
		type = PlayerType.jump;
	}

	public void setType(PlayerType type) {
		this.type = type;
	}
}
