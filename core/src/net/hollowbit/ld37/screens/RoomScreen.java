package net.hollowbit.ld37.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.decals.CameraGroupStrategy;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

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
	private static final float ROTATION_SPEED = 0.2f;
	
	private Wall[] walls;
	
	private SpriteBatch batch;
	
	private PerspectiveCamera cam;
	private ModelBatch mBatch;
	
	private Texture blank;
	private int currentWall = 0;//Starts in main menu wall
	
	private float x = 0, y = 0, z = 0f;
	private int mouseX = 0, mouseY = 0;
	
	private ModelBuilder modelBuilder;
	
	public RoomScreen (SpriteBatch batch) {
		this.batch = batch;
		
		//Load components required for wall management
		mBatch = new ModelBatch();
		
		cam = new PerspectiveCamera(45, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.near = 1;
		cam.far = 0;
		cam.position.set(x, y, z);
		cam.update();
		
		modelBuilder = new ModelBuilder();
		
		//Load textures
		blank = Ld37Game.getGame().getAssetManager().get("blank.png", Texture.class);
		
		//Initialize walls
		walls = new Wall[6];
		walls[0] = new MainMenuWall(new Vector3(1f, 0.75f, -1.8f), new Vector3(0, 0, 0), Wall.WIDTH, Wall.HEIGHT);
		walls[1] = new GameWall(new Vector3(1f, 0.75f, 0f), new Vector3(-90, 0, 0), Wall.WIDTH, Wall.HEIGHT);
		walls[2] = new OptionsWall(new Vector3(-1, 0.75f, -1.8f), new Vector3(90, 0, 0), Wall.WIDTH, Wall.HEIGHT);
		walls[3] = new CreditsWall(new Vector3(0, 0f, 0f), new Vector3(0, 0, 0), Wall.WIDTH, Wall.HEIGHT);
		
		walls[4] = new FloorWall(new Vector3(1, -0.75f, 0), new Vector3(0, 90, 0), Wall.WIDTH, Wall.WIDTH);//Width and height are both width because 3d!
		walls[5] = new CeilingWall(new Vector3(0, 0, 0), new Vector3(0, 0, 0), Wall.WIDTH, Wall.WIDTH);// ^
	}
	
	@Override
	public void show () {
		
		super.show();
	}
	
	@Override
	public void render (float delta) {
		super.render(delta);
		
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
		
		if (Gdx.input.isKeyPressed(Keys.A)) {
			cam.rotate(Vector3.Y, 40f * delta);
		}
		
		if (Gdx.input.isKeyPressed(Keys.D)) {
			cam.rotate(Vector3.Y, -40f * delta);
		}
		
		if (Gdx.input.isKeyPressed(Keys.W)) {
			cam.rotate(Vector3.X, 40f * delta);
		}
		
		if (Gdx.input.isKeyPressed(Keys.S)) {
			cam.rotate(Vector3.X, -40f * delta);
		}
		
		if (Gdx.input.isKeyPressed(Keys.Q)) {
			cam.rotate(Vector3.Z, 40f * delta);
		}
		
		if (Gdx.input.isKeyPressed(Keys.E)) {
			cam.rotate(Vector3.Z, -40f * delta);
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
		
		for (Wall wall : walls) {
			if (wall == null)
				continue;
			wall.update(delta);
		}
		
		int attr = VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates;
		modelBuilder.begin();
		MeshPartBuilder mpb = modelBuilder.part("box", GL20.GL_TRIANGLES, attr, new Material(TextureAttribute.createDiffuse(walls[0].getTexture(batch))));
		mpb.rect(-2f,-2f,-2f, -2f,2f,-2f,  2f,2f,-2, 2f,-2f,-2f, 0,0,-1);
		mpb.setUVRange(walls[1].getTexture(batch));
		mpb.rect(-2f,2f,2f, -2f,-2f,2f,  2f,-2f,2f, 2f,2f,2f, 0,0,1);
		mpb.setUVRange(walls[2].getTexture(batch));
		mpb.rect(-2f,-2f,2f, -2f,-2f,-2f,  2f,-2f,-2f, 2f,-2f,2f, 0,-1,0);
		mpb.setUVRange(walls[3].getTexture(batch));
		mpb.rect(-2f,2f,-2f, -2f,2f,2f,  2f,2f,2f, 2f,2f,-2f, 0,1,0);
		mpb.setUVRange(walls[4].getTexture(batch));
		mpb.rect(-2f,-2f,2f, -2f,2f,2f,  -2f,2f,-2f, -2f,-2f,-2f, -1,0,0);
		mpb.setUVRange(walls[5].getTexture(batch));
		mpb.rect(2f,-2f,-2f, 2f,2f,-2f,  2f,2f,2f, 2f,-2f,2f, 1,0,0);
		Model box = modelBuilder.end();
		ModelInstance boxInstance = new ModelInstance(box);
		
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		mBatch.begin(cam);
		mBatch.render(boxInstance);
		mBatch.end();
		
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
