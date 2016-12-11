package net.hollowbit.ld37.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.IntAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

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
import ui.ld_button;

public class RoomScreen extends ScreenAdapter {

	public static final float MOVE_SPEED = 0.6f;
	private static final int MAX_CURRENT_WALL = 4 - 1;//Player can only view first 4 walls
	
	private Wall[] walls;
	
	private SpriteBatch batch;
	
	private PerspectiveCamera cam;
	private OrthographicCamera cam2d;
	private ModelBatch mBatch;
	private State gameState = State.MAIN; 
	private int currentWall = 0;//Starts in main menu wall
	
	private float x = 0, y = 0, z = 0f;
	private float rot = 0f;
	private ModelBuilder modelBuilder;

	private boolean enabledCredits = false;
	private ld_button play = new ld_button(250,687,500,250);
	private ld_button options = new ld_button(250,406,500,250);
	private ld_button quit;
	private boolean quitButt;
    private float waterHeight = 0;
	public RoomScreen (SpriteBatch batch, boolean quitButt) {
		this.quitButt = quitButt;
	    if (this.quitButt) quit = new ld_button(250,125,500,250);
	   
		this.batch = batch;
		
		//Load components required for wall management
		mBatch = new ModelBatch();
		
		cam = new PerspectiveCamera(90, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.near = 0.1f;
		cam.far = 0;
		cam.position.set(x, y, z);
		cam.lookAt(0, 0, 0);
		cam.up.set(Vector3.Y);
		cam.update();
		
		cam2d = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam2d.translate(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		cam2d.update();
		
		modelBuilder = new ModelBuilder();
		
		//Initialize walls
		walls = new Wall[6];
		walls[0] = new MainMenuWall(new Vector3(-1,0,0), quitButt);
		walls[1] = new GameWall(new Vector3(1,0,0));
		walls[4] = new OptionsWall(new Vector3(-1,0,0));
		walls[5] = new CreditsWall(new Vector3(1,0,0));
		
		walls[2] = new FloorWall(new Vector3(0,-1,0));//Width and height are both width because 3d!
		walls[3] = new CeilingWall(new Vector3(0,-1,0));// ^
		
		//CameraInputController camController = new CameraInputController(cam);
		//Gdx.input.setInputProcessor(camController);
	}

	@Override
	public void show () {
		
		super.show();
	}
	public boolean rotating = false;
	@Override
	public void render (float delta) {
		super.render(delta);
		
		if (!rotating) getInput();
		
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
		
		waterHeight += delta * 0.02;
		Model water = modelBuilder.createBox(4f, 4f * waterHeight, 4f, new Material(IntAttribute.createCullFace(0), ColorAttribute.createDiffuse(0.2f, 0.4f, 1.7f, 0.5f),  TextureAttribute.createDiffuse(Ld37Game.getGame().getAssetManager().get("water.png", Texture.class)), new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA)), attr);
		ModelInstance waterInstance = new ModelInstance(water);
		waterInstance.transform.setToTranslation(0, (4f * waterHeight / 2) - 2, 0);
		
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		mBatch.begin(cam);
		mBatch.render(boxInstance);
		mBatch.render(waterInstance);
		mBatch.end();
		
		
		
		//Draw 2d overlay
		batch.setProjectionMatrix(cam2d.combined);
		batch.begin();
		
		Ld37Game.getGame().getFont().draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, 20);
		Ld37Game.getGame().getFont().draw(batch, "X: " + x + "  Y: " + y + "  Z: " + z, 10, 40);
		batch.end();
		switch (gameState){
		case MAIN:
			rotateToMenu(delta);
			break;
		case OPTNS:
			rotateToOptions(delta);
			break;
		case GAME:
			rotateToGame(delta);
			break;
		case CREDITS:
			rotateToCredits(delta);
			break;
		}
		 
			 
	}
	private void getInput() {
		float mx =Gdx.input.getX();
		float my =Gdx.graphics.getHeight()-Gdx.input.getY();
		Vector2 mouseVec = new Vector2(mx,my);
		if(gameState == State.MAIN  && Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
			if (play.checkMouse(mouseVec)) gameState=State.GAME;
			if (options.checkMouse(mouseVec)) gameState=State.OPTNS;
			if (quitButt && quit.checkMouse(mouseVec)) Gdx.app.exit();
			
		}
		//Old system
		/* if(gameState!=State.MAIN && Gdx.input.isKeyPressed(Input.Keys.Z)){
			 rotating=true;
			 gameState=State.MAIN;
		 }
		 if(gameState!=State.OPTNS && Gdx.input.isKeyPressed(Input.Keys.X)){
			 rotating=true;
			 gameState=State.OPTNS;
		 }
		 if(gameState!=State.GAME && Gdx.input.isKeyPressed(Input.Keys.C)){
			 rotating=true;
			 gameState=State.GAME;
		 }
		 if((enabledCredits) && gameState!=State.CREDITS && Gdx.input.isKeyPressed(Input.Keys.V)){
			 rotating=true;
			 gameState=State.CREDITS;
		 }*/
		
	}

	private void rotateToCredits(float delta) {
		if(rot < 270f){
			rotateCam(250f*delta);
		} else if (rot > 271f){
			rotateCam(-250f*delta);
		} else rotating = false;
		
	}

	private void rotateToMenu(float delta) {
		if(rot < 0f){
			rotateCam(250f*delta);
		} else if (rot > 1f){
			rotateCam(-250f*delta);
		} else rotating = false;
		
		
	}

	private void rotateToGame(float delta) {
		if(rot < 180f){
			rotateCam(250f*delta);
		} else if (rot > 181f){
			rotateCam(-250f*delta);
		} else rotating = false;
		
		
	}
	public void rotateToOptions(float delta){
		if(rot < 90f){
			rotateCam(250f*delta);
		} else if (rot > 91f){
			rotateCam(-250f*delta);
		} else rotating = false;
		 
	}
	public void rotateCam(float a){
		cam.rotate(cam.up,a);
		cam.update();
		cam.normalizeUp();
		rot+=a;
		System.out.println(rot);
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
