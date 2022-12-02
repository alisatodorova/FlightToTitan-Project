package com.dke.simulator.screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.dke.simulator.Main;
import com.dke.simulator.SolarSystem;
import com.dke.simulator.SpaceObject;
import com.dke.simulator.Vector3d;

public class SolarSystemScreen implements Screen {
	public static final float WIDTH = 960;
	public static final float HEIGHT = 600;

	private OrthographicCamera camera;

	private SpriteBatch batch;

	private SolarSystem solarSystem;

	private SpaceObject toFollow;

	private Stage stage;
    private Game game;
	
	public SolarSystemScreen(Game aGame) {
		game = aGame;
        stage = new Stage(new ScreenViewport());

		/*
        Label title = new Label("Welcome to the Solar System!", Main.skin, "default");
        title.setAlignment(Align.center);
        title.setY(Gdx.graphics.getHeight()*2/3);
        title.setWidth(Gdx.graphics.getWidth());
        stage.addActor(title);
		
        TextButton xButton = new TextButton("X", Main.skin);
        xButton.setWidth(Gdx.graphics.getWidth()/12);
        xButton.setHeight(Gdx.graphics.getHeight()/12);
        xButton.setPosition(Gdx.graphics.getWidth()-xButton.getWidth()*3, Gdx.graphics.getHeight()-xButton.getHeight()*9);
        stage.addActor(xButton);

        TextButton yButton = new TextButton("Y", Main.skin);
        yButton.setWidth(Gdx.graphics.getWidth()/12);
        yButton.setHeight(Gdx.graphics.getHeight()/12);
        yButton.setPosition(Gdx.graphics.getWidth()-yButton.getWidth()*2, Gdx.graphics.getHeight()-yButton.getHeight()*9);
        stage.addActor(yButton);


        TextButton zButton = new TextButton("Z", Main.skin);
        zButton.setWidth(Gdx.graphics.getWidth()/12);
        zButton.setHeight(Gdx.graphics.getHeight()/12);
        zButton.setPosition(Gdx.graphics.getWidth()-yButton.getWidth()*1, Gdx.graphics.getHeight()-zButton.getHeight()*9);
        stage.addActor(zButton);
		*/


        TextButton pauseButton = new TextButton("Pause", Main.skin);
        pauseButton.setWidth(Gdx.graphics.getWidth()/6);
        pauseButton.setPosition(Gdx.graphics.getWidth()-pauseButton.getWidth(),Gdx.graphics.getHeight()/6);
        pauseButton.addListener(new InputListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                //stop the screen(code)
                //Gdx.app.exit(); (for test)
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				solarSystem.pause(true);
                return true;
            }
        });
        stage.addActor(pauseButton);


        TextButton continueButton = new TextButton("Continue", Main.skin);
        continueButton.setWidth(Gdx.graphics.getWidth()/6);
        continueButton.setPosition(Gdx.graphics.getWidth()-continueButton.getWidth(),Gdx.graphics.getHeight()/10);
        continueButton.addListener(new InputListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                //continue the screen(code)
                //Gdx.app.exit(); (for test)
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
				solarSystem.pause(false);
                return true;
            }
        });
        stage.addActor(continueButton);


        TextButton backButton = new TextButton("Menu", Main.skin);
        backButton.setWidth(Gdx.graphics.getWidth()/6);
        backButton.setPosition(Gdx.graphics.getWidth()-continueButton.getWidth(),Gdx.graphics.getHeight()/29);
        backButton.addListener(new InputListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                game.setScreen(new HomeScreen(game));
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
        });
        stage.addActor(backButton);

		// PLANETS

		// Initial code here
		batch = new SpriteBatch();
		camera = new OrthographicCamera(WIDTH, HEIGHT);
		solarSystem = new SolarSystem();
		toFollow = null;

		SpaceObject sun = new SpaceObject("Sun", 2, "FD0D1B",
			1.988500e30, 696340,
			new Vector3d(-6.806783239281648e+08, 1.080005533878725e+09, 6.564012751690170e+06),
			new Vector3d(-1.420511669610689e+01, -4.954714716629277e+00, 3.994237625449041e-01)
		);
		solarSystem.add(sun);

		SpaceObject mercury = new SpaceObject("Mercury", 1, "7A7A87",
			3.302e23, 2439.7,
			new Vector3d(6.047855986424127e+06, -6.801800047868888e+10, -5.702742359714534e+09),
			new Vector3d(3.892585189044652e+04, 2.978342247012996e+03, -3.327964151414740e+03)
		);
		solarSystem.add(mercury);

		SpaceObject venus = new SpaceObject("Venus", 1, "FCA929",
			4.8685e24, 6051.8,
			new Vector3d(-9.435345478592035e+10, 5.350359551033670e+10, 6.131453014410347e+09),
			new Vector3d(-1.726404287724406e+04, -3.073432518238123e+04, 5.741783385280979e-04)
		);
		solarSystem.add(venus);
		
		SpaceObject earth = new SpaceObject("Earth", 1, "0F28FF",
			5.97219e24, 6371e3,
			new Vector3d(-1.471922101663588e+11, -2.860995816266412e+10, 8.278183193596080e+06),
			new Vector3d(5.427193405797901e+03, -2.931056622265021e+04, 6.575428158157592e-01)
		);
		solarSystem.add(earth);
		
		/*
		solarSystem.addProbe(
			earth,
			150000,
			100,
			new Vector3d(0, 6371000, 0),
			new Vector3d(0, 0, 0)
		);*/
		

		SpaceObject moon = new SpaceObject("Moon", 0.5, "FFFFFF",
			7.349e22, 6371e3,
			new Vector3d(-1.472343904597218e+11, -2.822578361503422e+10, 1.052790970065631e+07),
			new Vector3d(4.433121605215677e+03, -2.948453614110320e+04, 8.896598225322805e+01)
		);
		solarSystem.add(moon);

		SpaceObject mars = new SpaceObject("Mars", 1, "C05438",
			5.97219e24, 3389.5,
			new Vector3d(-3.615638921529161e+10, -2.167633037046744e+11, -3.687670305939779e+09),
			new Vector3d(2.481551975121696e+04, -1.816368005464070e+03, -6.467321619018108e+02)
		);
		solarSystem.add(mars);

		SpaceObject jupiter = new SpaceObject("Jupiter", 1, "FFFFFF",
			1.89813e27, 3389.5,
			new Vector3d(1.781303138592153e+11, -7.551118436250277e+11, -8.532838524802327e+08),
			new Vector3d(1.255852555185220e+04, 3.622680192790968e+03, -2.958620380112444e+02)
		);
		solarSystem.add(jupiter);

		SpaceObject saturn = new SpaceObject("Saturn", 1, "FFFFFF",
			5.6834e26, 3389.5,
			new Vector3d(6.328646641500651e+11, -1.358172804527507e+12, -1.578520137930810e+09),
			new Vector3d(8.220842186554890e+03, 4.052137378979608e+03, -3.976224719266916e+02)
		);
		solarSystem.add(saturn);

		
		SpaceObject titan = new SpaceObject("Titan", 0.5, "FFFFFF",
			1.34553e23, 2575.5e3,
			new Vector3d(6.332873118527889e+11, -1.357175556995868e+12, -2.134637041453660e+09),
			new Vector3d(3.056877965721629e+03, 6.125612956428791e+03, -9.523587380845593e+02)
		);
		solarSystem.add(titan);

		SpaceObject uranus = new SpaceObject("Uranus", 1, "FFFFFF",
			8.6813e25, 3389.5,
			new Vector3d(2.395195786685187e+12, 1.744450959214586e+12, -2.455116324031639e+10),
			new Vector3d(-4.059468635313243e+03, 5.187467354884825e+03, 7.182516236837899e+01)
		);
		solarSystem.add(uranus);

		SpaceObject neptune = new SpaceObject("Neptune", 1, "FFFFFF",
			1.02413e26, 3389.5,
			new Vector3d(4.382692942729203e+12, -9.093501655486243e+11, -8.227728929479486e+10),
			new Vector3d(1.068410720964204e+03, 5.354959501569486e+03, -1.343918199987533e+02)
		);
		solarSystem.add(neptune);
	}

	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
        stage.draw();
		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0));

		// Solar system logic
		batch.setProjectionMatrix(camera.combined);
		solarSystem.render(batch);

		if(toFollow != null) {
			followObject();
		}
		camera.update();
	}
	
	@Override
	public void dispose () {
		// Dispose here
		batch.dispose();
		stage.dispose();
	}

	public void followObject() {
		Vector3d pos = toFollow.position(true);
		camera.position.x = (float)pos.getX();
		camera.position.y = (float)pos.getY();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(new InputAdapter() {
			@Override
			public boolean touchDragged(int screenX, int screenY, int pointer) {
				float x = Gdx.input.getDeltaX();
				float y = Gdx.input.getDeltaY();

				camera.translate(-x, y);
				return true;
			}
			
			@Override
			public boolean keyDown(int keycode) {
				if(keycode == Keys.ESCAPE) {
					toFollow = null;
					//camera.zoom = 1;
					camera.position.x = 0;
					camera.position.y = 0;
					camera.position.z = 0;
				}
				return false;
			}

			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				Vector3 mousePos = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
				SpaceObject clickedObj = solarSystem.getClicked(mousePos.x, mousePos.y);

				if(clickedObj != null) {
					toFollow = clickedObj;
					//camera.zoom = (float)0.5;
				}
				return false;
			}
		});
		Gdx.input.setInputProcessor(multiplexer);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
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
}
