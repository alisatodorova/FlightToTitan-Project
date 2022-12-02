package com.dke.simulator.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.dke.simulator.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = "Solar System Simulator";
		config.useGL30 = false;
		config.width = 960;
		config.height = 600;
		// config.samples = 4;

		new LwjglApplication(new Main(), config);
	}
}
