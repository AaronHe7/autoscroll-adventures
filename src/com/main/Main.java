package com.main;

import com.controller.Controller;
import com.controller.KeyInput;
import com.controller.MouseInput;
import com.model.Model;
import com.view.View;

public class Main {
	public static void main(String[] args) {
		/* MVC scheme. 
		 View displays model.
		 Controller updates model.
		 The user sees the view and interacts with the controller.
		 */
		Model model = new Model();
		Controller controller = new Controller(model);
		View view = new View(model);
		new MouseInput(controller, view);
		new KeyInput(controller, view);
	}
}
