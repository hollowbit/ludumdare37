package net.hollowbit.ld37.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
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
import com.badlogic.gdx.math.Vector3;

import minigames.ld_minibase;
import net.hollowbit.ld37.Ld37Game;
import net.hollowbit.ld37.walls.CeilingWall;
import net.hollowbit.ld37.walls.CreditsWall;
import net.hollowbit.ld37.walls.FloorWall;
import net.hollowbit.ld37.walls.GameWall;
import net.hollowbit.ld37.walls.MainMenuWall;
import net.hollowbit.ld37.walls.OptionsWall;
import net.hollowbit.ld37.walls.Wall;

public class RoomScreen extends ScreenAdapter implements ld_minibase.GameEndHandler {

	public static final float MOVE_SPEED = 0.6f;
	private static final int MAX_CURRENT_WALL = 4 - 1;//Player can only view first 4 walls
	private static final int CAM_ROTATE_SPEED = 120;
	private static final float WATER_HEIGHT_CHANGE = 0.1f;
	private static final float WATER_MOVE_SPEED = 0.05f;
	private static final int GAMES_TO_WIN = 10;
	private static final float DEATH_WATER_LEVEL = 0.6f;
	
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
    private float waterHeight = 0;
    private float waterHeightGoal = 0;
    
	private boolean rotating = false;
	
	private int score = 0;
    private float gameOverOpacity = 0;
    private boolean gameover = false;
	
	public RoomScreen (SpriteBatch batch) {
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
		walls[0] = new MainMenuWall(new Vector3(-1,0,0), this);
		walls[1] = new GameWall(new Vector3(1,0,0), this);
		walls[4] = new OptionsWall(new Vector3(-1,0,0), this);
		walls[5] = new CreditsWall(new Vector3(-1,0,0), this);
		
		walls[2] = new FloorWall(new Vector3(0,-1,0), this);//Width and height are both width because 3d!
		walls[3] = new CeilingWall(new Vector3(0,-1,0), this);// ^
		
		//CameraInputController camController = new CameraInputController(cam);
		//Gdx.input.setInputProcessor(camController);
	}

	@Override
	public void show () {
		
		super.show();
	}
	
	@Override
	public void render (float delta) {
		super.render(delta);
		
		for (int i = 0; i < walls.length; i++) {
			if (i == currentWall && !rotating)
				walls[i].handleInput();
			walls[i].update(delta, i == currentWall);
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
		
		if (waterHeight < waterHeightGoal) {
			waterHeight += WATER_MOVE_SPEED * delta;
			if (waterHeight > waterHeightGoal)
				waterHeight = waterHeightGoal;
		}
		
		if (waterHeight > waterHeightGoal) {
			waterHeight -= WATER_MOVE_SPEED * delta;
			if (waterHeight < waterHeightGoal)
				waterHeight = waterHeightGoal;
		}
		
		if (waterHeight >= DEATH_WATER_LEVEL)
			gameover = true;
		
		if (score >= GAMES_TO_WIN)
			setState(State.CREDITS);
		
		Model water = modelBuilder.createBox(4f, 4f * waterHeight, 4f, new Material(IntAttribute.createCullFace(0), ColorAttribute.createDiffuse(0.2f, 0.4f, 1.7f, 0.5f),  TextureAttribute.createDiffuse(Ld37Game.getGame().getAssetManager().get("water.png", Texture.class)), new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA)), attr);
		ModelInstance waterInstance = new ModelInstance(water);
		waterInstance.transform.setToTranslation(0, (2.01f * waterHeight / 2) - 2, 0);
		
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		mBatch.begin(cam);
		mBatch.render(boxInstance);
		mBatch.render(waterInstance);
		mBatch.end();
		
		
		
		//Draw 2d overlay
		batch.setProjectionMatrix(cam2d.combined);
		batch.begin();
		
		if (score < GAMES_TO_WIN)
			Ld37Game.getGame().getFontHd().draw(batch, (GAMES_TO_WIN - score) + " trials left.", 10, 40);

		if (gameover) {
			batch.setColor(1, 0, 0, gameOverOpacity);
			batch.draw(Ld37Game.getGame().getAssetManager().get("blank.png", Texture.class), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			batch.setColor(1, 1, 1, 1);
			if (gameOverOpacity < 1)
				gameOverOpacity += delta;
			
			if (gameOverOpacity >= 1) {
				setState(State.MAIN);
				gameOverOpacity = 1;
				GlyphLayout gameoverLayout = new GlyphLayout(Ld37Game.getGame().getFontHd(), "GAME OVER");
				Ld37Game.getGame().getFontHd().draw(batch, gameoverLayout, Gdx.graphics.getWidth() / 2 - gameoverLayout.width / 2, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() * 0.2f);
				
				GlyphLayout trialsLeftLayout = new GlyphLayout(Ld37Game.getGame().getFontHd(), (GAMES_TO_WIN - score) + " trials left.");
				Ld37Game.getGame().getFontHd().draw(batch, trialsLeftLayout, Gdx.graphics.getWidth() / 2 - trialsLeftLayout.width / 2, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() * 0.35f);
				
				GlyphLayout retryLayout = new GlyphLayout(Ld37Game.getGame().getFontHd(), "CLICK to Retry Life");
				Ld37Game.getGame().getFontHd().draw(batch, retryLayout, Gdx.graphics.getWidth() / 2 - retryLayout.width / 2, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() * 0.50f);
				
				if (Gdx.input.justTouched())
					reset();
			}
		}
		
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

	private void rotateToCredits(float delta) {
		if(rot < 270f){
			rotateCam(CAM_ROTATE_SPEED * delta);
		} else if (rot > 271f){
			rotateCam(-CAM_ROTATE_SPEED * delta);
		} else rotating = false;
		
	}

	private void rotateToMenu(float delta) {
		if(rot < 0f){
			rotateCam(CAM_ROTATE_SPEED * delta);
		} else if (rot > 1f){
			rotateCam(-CAM_ROTATE_SPEED * delta);
		} else rotating = false;
		
		
	}

	private void rotateToGame(float delta) {
		if(rot < 180f){
			rotateCam(CAM_ROTATE_SPEED * delta);
		} else if (rot > 181f){
			rotateCam(-CAM_ROTATE_SPEED * delta);
		} else rotating = false;
		
		
	}
	
	private void rotateToOptions(float delta){
		if(rot < 90f){
			rotateCam(CAM_ROTATE_SPEED * delta);
		} else if (rot > 91f){
			rotateCam(-CAM_ROTATE_SPEED * delta);
		} else rotating = false;
		 
	}
	
	private void rotateCam(float a){
		cam.rotate(cam.up,a);
		cam.update();
		cam.normalizeUp();
		rot+=a;
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
	
	public void setState (State state) {
		if (this.gameState != state) {
			this.rotating = true;
			this.gameState = state;
			this.currentWall = state.getWallNo();
		}
	}

	@Override
	public void handleGameEnd (boolean won) {
		if (won) {
			waterHeightGoal -= WATER_HEIGHT_CHANGE;
			score++;
		} else {
			waterHeightGoal += WATER_HEIGHT_CHANGE;
			score = 0;
		}
		
		if (waterHeightGoal < 0)
			waterHeightGoal = 0;
	}
	
	public void reset () {
		waterHeightGoal = 0;
		score = 0;
	    gameOverOpacity = 0;
	    gameover = false;
	}
	
	public boolean isRotating () {
		return rotating;
	}
	
}
