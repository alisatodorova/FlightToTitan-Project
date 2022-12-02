package com.dke.simulator;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.dke.simulator.screens.HomeScreen;

public class Main extends Game {
	static public Skin skin;

	public void create () {
		skin = new Skin(Gdx.files.internal("neon/skin/neon-ui.json"));
		this.setScreen(new HomeScreen(this));
	}


	public void render () {
		super.render();
	}
	

	public void dispose () {
		
	}
}
