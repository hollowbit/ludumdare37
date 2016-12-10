package net.hollowbit.ld37.walls;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import net.hollowbit.ld37.Ld37Game;

public abstract class Wall {
	
	public static final int WIDTH = 400;
	public static final int HEIGHT = 300;
	
	protected Vector3 position;
	protected Vector3 rotation;
	protected int width;
	protected int height;
	
	protected Texture blank;
	
	public Wall (Vector3 position, Vector3 rotation, int width, int height) { 
		this.position = position;
		this.rotation = rotation;
		this.width = width;
		this.height = height;
		
		blank = Ld37Game.getGame().getAssetManager().get("blank.png", Texture.class);
	}
	
	public abstract void update (float delta);
	public abstract void render (SpriteBatch batch);
	public abstract void dispose ();
	
	public Vector3 getPosition () {
		return this.position;
	}
	
	public Vector3 getRotation () {
		return this.rotation;
	}
	
	public void setPosition (Vector3 position) {
		this.position = position;
	}
	
	public void setRotation (Vector3 rotation) {
		this.rotation = rotation;
	}
	
	public int getWidth () {
		return width;
	}
	
	public int getHeight () {
		return height;
	}
	
}
