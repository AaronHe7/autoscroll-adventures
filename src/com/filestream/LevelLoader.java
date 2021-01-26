package com.filestream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.model.Level;

public class LevelLoader {
	public LevelLoader() {

	}
	public Level loadLevel(String filename) { 
		try {
			File f = new File("res/" + filename + ".ser");
			if (!f.exists()) {
				return null;
			}
			FileInputStream fileIn = new FileInputStream("res/" + filename + ".ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			Level level = (Level) in.readObject();
			level.getPlayer().reset();
			return level;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
