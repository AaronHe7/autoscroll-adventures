package com.filestream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.model.Level;

public class LevelLoader {
	public LevelLoader() {

	}
	public Level loadLevel(String filename) { 
		try {
			FileInputStream fileIn = new FileInputStream("res/" + filename + ".ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			Level level = (Level) in.readObject();
			return level;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
