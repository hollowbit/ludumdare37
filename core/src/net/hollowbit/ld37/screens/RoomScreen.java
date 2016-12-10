package net.hollowbit.ld37.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.IntAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

import net.hollowbit.ld37.Ld37Game;
import net.hollowbit.ld37.walls.CeilingWall;
import net.hollowbit.ld37.walls.CreditsWall;
import net.hollowbit.ld37.walls.FloorWall;
import net.hollowbit.ld37.walls.GameWall;
import net.hollowbit.ld37.walls.MainMenuWall;
import net.hollowbit.ld37.walls.OptionsWall;
import net.hollowbit.ld37.walls.Wall;

public class RoomScreen extends ScreenAdapter {

	public static final float MOVE_SPEED = 0.6f;
	private static final int MAX_CURRENT_WALL = 4 - 1;//Player can only view first 4 walls
	
	private Wall[] walls;
	
	private SpriteBatch batch;
	
	private PerspectiveCamera cam;
	private OrthographicCamera cam2d;
	private ModelBatch mBatch;
	
	private int currentWall = 0;//Starts in main menu wall
	
	private float x = 0, y = 0, z = 0f;
	
	private ModelBuilder modelBuilder;
	
	public RoomScreen (SpriteBatch batch) {
		this.batch = batch;
		
		//Load components required for wall management
		mBatch = new ModelBatch();
		
		cam = new PerspectiveCamera(100, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.near = 0.1f;
		cam.far = 0;
		cam.position.set(x, y, z);
		cam.lookAt(0, 0, 0);
		cam.update();
		
		cam2d = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam2d.translate(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		cam2d.update();
		
		modelBuilder = new ModelBuilder();
		
		//Initialize walls
		walls = new Wall[6];
		walls[0] = new MainMenuWall();
		walls[1] = new GameWall();
		walls[2] = new OptionsWall();
		walls[3] = new CreditsWall();
		
		walls[4] = new FloorWall();//Width and height are both width because 3d!
		walls[5] = new CeilingWall();// ^
		
		CameraInputController camController = new CameraInputController(cam);
		Gdx.input.setInputProcessor(camController);
	}
	
	@Override
	public void show () {
		
		super.show();
	}
	
	@Override
	public void render (float delta) {
		super.render(delta);
		
		for (Wall wall : walls) {
			if (wall == null)
				continue;
			wall.update(delta);
		}
		
		int attr = VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates;
		modelBuilder.begin();
		
		modelBuilder.part("box", GL20.GL_TRIANGLES, attr, new Material(IntAttribute.createCullFace(0), TextureAttribute.createDiffuse(walls[0].getTexture(batch))))
		.rect(-2f,-2f,-2f, -2f,2f,-2f,  2f,2f,-2, 2f,-2f,-2f, 0,0,-1);
		modelBuilder.part("box", GL20.GL_TRIANGLES, attr, new Material(IntAttribute.createCullFace(0), TextureAttribute.createDiffuse(walls[1].getTexture(batch))))
		.rect(-2f,2f,2f, -2f,-2f,2f,  2f,-2f,2f, 2f,2f,2f, 0,0,1);
		modelBuilder.part("box", GL20.GL_TRIANGLES, attr, new Material(IntAttribute.createCullFace(0), TextureAttribute.createDiffuse(walls[2].getTexture(batch))))
		.rect(-2f,-2f,2f, -2f,-2f,-2f,  2f,-2f,-2f, 2f,-2f,2f, 0,-1,0);
		modelBuilder.part("box", GL20.GL_TRIANGLES, attr, new Material(IntAttribute.createCullFace(0), TextureAttribute.createDiffuse(walls[3].getTexture(batch))))
		.rect(-2f,2f,-2f, -2f,2f,2f,  2f,2f,2f, 2f,2f,-2f, 0,1,0);
		modelBuilder.part("box", GL20.GL_TRIANGLES, attr, new Material(IntAttribute.createCullFace(0), TextureAttribute.createDiffuse(walls[4].getTexture(batch))))
		.rect(-2f,-2f,2f, -2f,2f,2f,  -2f,2f,-2f, -2f,-2f,-2f, -1,0,0);
		modelBuilder.part("box", GL20.GL_TRIANGLES, attr, new Material(IntAttribute.createCullFace(0), TextureAttribute.createDiffuse(walls[5].getTexture(batch))))
		.rect(2f,-2f,-2f, 2f,2f,-2f,  2f,2f,2f, 2f,-2f,2f, 1,0,0);
		Model box = modelBuilder.end();
		ModelInstance boxInstance = new ModelInstance(box);
		
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		mBatch.begin(cam);
		mBatch.render(boxInstance);
		mBatch.end();
		
		//Draw 2d overlay
		batch.setProjectionMatrix(cam2d.combined);
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
