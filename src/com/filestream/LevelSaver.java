package com.filestream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.model.Level;

public class LevelSaver {
	public void saveLevel(Level level, String name) {
		try {
			FileOutputStream fileOut = new FileOutputStream("res/" + name + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(level);
			out.close();
			fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
