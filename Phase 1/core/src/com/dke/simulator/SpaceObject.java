package com.dke.simulator;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.physics.box2d.Shape;

public class SpaceObject {

	// Object data
	private String name;
	private double scale;
	private String color;
	private double mass;
	private double radius;
	private Vector3d position;
	private Vector3d velocity;
	
	private BitmapFont label;
	private ShapeRenderer shapeRenderer;

	public static final int SHAPE_SIZE = 7;
	private static final double NORM_FACTOR = 1e9;

	public SpaceObject(String name, double scale, String color, double mass, double radius, Vector3d position, Vector3d velocity) {
		this.name = name;
		this.scale = scale;
		this.color = color;
		this.mass = mass;
		this.radius = radius;
		this.position = position;
		this.velocity = velocity;

		label = new BitmapFont();
		label.setColor(Color.WHITE);

		shapeRenderer = new ShapeRenderer();
	}

	public void move(double x, double y, double z) {
		position = position.add(new Vector3d(x, y, z));
	}

	public void render(SpriteBatch batch) {
		// Render the object
		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		shapeRenderer.begin(ShapeType.Filled);

		shapeRenderer.setColor(Color.valueOf(color));
		float x = (float)(position.getX()/NORM_FACTOR);
		float y = (float)(position.getY()/NORM_FACTOR);
		shapeRenderer.circle(x, y, (float)size());

		shapeRenderer.end();

		// Render the label
		batch.begin();
		final GlyphLayout layout = new GlyphLayout(label, name);
		label.draw(batch, name, x-(layout.width/2), y-10-(float)(5*scale));
		batch.end();
	}

	public Vector3d position(boolean normalize) {
		if(normalize)
			return position.mul(1/NORM_FACTOR);
		else
			return position;
	}

	public void setPosition(Vector3d v) {
		position = v;
	}

	public Vector3d velocity() {
		return velocity;
	}

	public void setVelocity(Vector3d v) {
		velocity = v;
	}

	public double mass() {
		return mass;
	}

	public String name() {
		return name;
	}

	public double size() {
		return SHAPE_SIZE*scale;
	}
}
