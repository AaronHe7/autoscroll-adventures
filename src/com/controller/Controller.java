package com.controller;

import com.model.Model;

public class Controller {
	Model model;
	
	public Controller(Model model) {
		this.model = model;
	}
	
	public void registerMouseClick() {
		model.getCurrentLevel().jump();
	}
	
	public void registerMouseRelease() {
		
	}
}
