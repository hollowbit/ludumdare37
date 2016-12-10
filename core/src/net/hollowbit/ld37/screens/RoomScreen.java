package net.hollowbit.ld37.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.decals.CameraGroupStrategy;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Vector3;

import net.hollowbit.ld37.Ld37Game;
import net.hollowbit.ld37.walls.CeilingWall;
import net.hollowbit.ld37.walls.FloorWall;
import net.hollowbit.ld37.walls.GameWall;
import net.hollowbit.ld37.walls.MainMenuWall;
import net.hollowbit.ld37.walls.OptionsWall;
import net.hollowbit.ld37.walls.Wall;

public class RoomScreen extends ScreenAdapter {

	public static final float MOVE_SPEED = 0.6f;
	private static final int MAX_CURRENT_WALL = 4 - 1;//Player can only view first 4 walls
	private static final float ROTATION_SPEED = 0.2f;
	
	private Wall[] walls;
	
	private SpriteBatch batch;
	private FrameBuffer fbo;
	
	private PerspectiveCamera cam;
	private DecalBatch dBatch;
	private Decal decal;
	
	private Texture blank;
	private int currentWall = 0;//Starts in main menu wall
	
	private float x = 0, y = 0, z = 0f;
	private int mouseX = 0, mouseY = 0;
	
	public RoomScreen (SpriteBatch batch) {
		this.batch = batch;
		
		//Load components required for wall management
		//fbo = new FrameBuffer(Format.RGBA8888, Wall.WIDTH, Wall.HEIGHT, false);
		
		cam = new PerspectiveCamera(45, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.near = 1;
		cam.far = 0;
		cam.position.set(x, y, z);
		cam.update();
		
		CameraGroupStrategy cameraGroupStrategy = new CameraGroupStrategy(cam);
		dBatch = new DecalBatch(cameraGroupStrategy);
		
		//Load textures
		blank = Ld37Game.getGame().getAssetManager().get("blank.png", Texture.class);
		
		//Initialize walls
		walls = new Wall[6];
		walls[0] = new MainMenuWall(new Vector3(1f, 0.75f, -1.8f), new Vector3(0, 0, 0), Wall.WIDTH, Wall.HEIGHT);
		walls[1] = new GameWall(new Vector3(1f, 0.75f, 0f), new Vector3(-90, 0, 0), Wall.WIDTH, Wall.HEIGHT);
		walls[2] = new OptionsWall(new Vector3(-1, 0.75f, -1.8f), new Vector3(90, 0, 0), Wall.WIDTH, Wall.HEIGHT);
		//walls[3] = new CreditsWall(new Vector3(0, 0f, 0f), new Vector3(0, 0, 0), Wall.WIDTH, Wall.HEIGHT);
		
		walls[4] = new FloorWall(new Vector3(1, -0.5f, 0), new Vector3(0, 90, 0), Wall.WIDTH, Wall.WIDTH);//Width and height are both width because 3d!
		walls[5] = new CeilingWall(new Vector3(0, 0, 0), new Vector3(0, 0, 0), Wall.WIDTH, Wall.WIDTH);// ^
		
		//Input
		Gdx.input.setInputProcessor(new InputAdapter() {
			@Override
			public boolean mouseMoved(int screenX, int screenY) {
				 int magX = Math.abs(mouseX - screenX);
				    int magY = Math.abs(mouseY - screenY);

				    if (mouseX > screenX) {
				        cam.rotate(Vector3.Y, 1 * magX * ROTATION_SPEED);
				        cam.update();
				    }

				    if (mouseX < screenX) {
				        cam.rotate(Vector3.Y, -1 * magX * ROTATION_SPEED);
				        cam.update();
				    }

				    if (mouseY < screenY) {
				        if (cam.direction.y > -0.965)
				            cam.rotate(cam.direction.cpy().crs(Vector3.Y), -1 * magY * ROTATION_SPEED);
				        cam.update();
				    }

				    if (mouseY > screenY) {

				        if (cam.direction.y < 0.965)
				            cam.rotate(cam.direction.cpy().crs(Vector3.Y), 1 * magY * ROTATION_SPEED);
				        cam.update();
				    }

				    mouseX = screenX;
				    mouseY = screenY;

				    return false;
			}
		});
	}
	
	@Override
	public void show () {
		
		super.show();
	}
	
	@Override
	public void render (float delta) {
		super.render(delta);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);
		
		if (Gdx.input.isKeyPressed(Keys.UP)) {
			z -= MOVE_SPEED * delta;
		}
		
		if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			z += MOVE_SPEED * delta;
		}
		
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			x -= MOVE_SPEED * delta;
		}
		
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			x += MOVE_SPEED * delta;
		}
		
		if (Gdx.input.isKeyPressed(Keys.SPACE)) {
			y += MOVE_SPEED * delta;
		}

		if (Gdx.input.isKeyPressed(Keys.CONTROL_LEFT)) {
			y -= MOVE_SPEED * delta;
		}
		
		
		/*Vector3 pos = new Vector3(1.0f, 1.125f, -1.8f);
		Vector3 rot = new Vector3(0, 90, 0);
		walls[5].setPosition(pos);
		walls[5].setRotation(rot);*/
		
		Vector3 pos = new Vector3(1.0f, 0.75f, 0f);
		Vector3 rot = new Vector3(0, -90, 0);
		walls[5].setPosition(pos);
		walls[5].setRotation(rot);
		
		cam.position.set(x, y, z);
		cam.update();
		
		//Create FBOs for each wall and add them to decal to be drawn
		for (Wall wall : walls) {
			if ( wall == null)
				continue;
			
			wall.update(delta);
			
			fbo = new FrameBuffer(Format.RGBA8888, wall.getWidth(), wall.getHeight(), false);
			fbo.begin();
			batch.begin();
			batch.setColor(1, 1, 1, 1);
			batch.draw(blank, 0, 0, wall.getWidth(), wall.getHeight());
			wall.render(batch);
			batch.end();
			fbo.end();
			
			TextureRegion fboTexture = new TextureRegion(fbo.getColorBufferTexture());
			fboTexture.flip(false, true);
			
			decal = Decal.newDecal(fboTexture, true);
			Vector3 position = wall.getPosition();
			decal.setPosition(position.x, position.y, position.z);
			Vector3 rotation = wall.getRotation();
			decal.setRotation(rotation.x, rotation.y, rotation.z);
			decal.setScale(0.01f);
			
			dBatch.add(decal);
		}
		dBatch.flush();
		
		//Draw 2d overlay
		batch.begin();
		Ld37Game.getGame().getFont().draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, 20);
		Ld37Game.getGame().getFont().draw(batch, "X: " + x + "  Y: " + y + "  Z: " + z, 10, 40);
		batch.end();
	}
	
	@Override
	public void dispose () {
		for (Wall wall : walls) {
			wall.dispose();
		}
		dBatch.dispose();
		fbo.dispose();
		super.dispose();
	}
	
	/**
	 * Move to the wall to the left
	 */
	public void wallLeft () {
		currentWall--;
		if (currentWall < 0) 
			currentWall = MAX_CURRENT_WALL;
	}
	
	/**
	 * Move to the wall to the right
	 */
	public void wallRight () {
		currentWall++;
		if (currentWall > MAX_CURRENT_WALL)
			currentWall = 0;
	}
	
}
