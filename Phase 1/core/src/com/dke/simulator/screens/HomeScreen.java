package com.dke.simulator.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.dke.simulator.Main;

public class HomeScreen implements Screen {
	private Stage stage;
    private Game game;

    public HomeScreen(Game aGame){
		game = aGame;
		stage = new Stage(new ScreenViewport());

		Label title = new Label("You are about to enter the Solar System!\n Are you ready for liftoff?", Main.skin, "default");
		title.setAlignment(Align.center);
		title.setY(Gdx.graphics.getHeight()*2/3);
		title.setWidth(Gdx.graphics.getWidth());
		stage.addActor(title);

		TextButton launchButton = new TextButton("Launch", Main.skin);
		launchButton.setWidth(Gdx.graphics.getWidth()/2);
		launchButton.setPosition(Gdx.graphics.getWidth()/2-launchButton.getWidth()/2,Gdx.graphics.getHeight()/2-launchButton.getHeight()/2);
		launchButton.addListener(new InputListener() {
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(new SolarSystemScreen(game));
			}
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});
		stage.addActor(launchButton);

		TextButton exitButton = new TextButton("Exit", Main.skin);
		exitButton.setWidth(Gdx.graphics.getWidth()/2);
		exitButton.setPosition(Gdx.graphics.getWidth()/2-exitButton.getWidth()/2,Gdx.graphics.getHeight()/3-exitButton.getHeight()/3);
		exitButton.addListener(new InputListener() {
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.exit();
			}
			@Override
				public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});
		stage.addActor(exitButton);
	}

	@Override
	public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		stage.dispose();
	}
}
