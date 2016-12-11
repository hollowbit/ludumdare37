package net.hollowbit.ld37.walls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import net.hollowbit.ld37.Ld37Game;
import net.hollowbit.ld37.screens.RoomScreen;

public abstract class Wall {
	
	public static final int FBO_SIZE = 1000;
	public static final int SIZE = 64;
	
	protected StretchViewport viewport;
	protected OrthographicCamera cam;
	protected OrthographicCamera camInput;
	
	protected FrameBuffer fbo;
	protected enum W {Game,Menu,Options,Ceiling,Floor,Credits};
	protected Texture blank;
	protected RoomScreen roomScreen;
	protected Vector3 dir;
	
	public Wall (Vector3 dir, RoomScreen roomScreen) { 
		this.dir = dir;
		this.roomScreen = roomScreen;
		fbo = new FrameBuffer(Format.RGBA8888, FBO_SIZE, FBO_SIZE, false);
		cam = new OrthographicCamera(SIZE, SIZE);
		
		cam.translate(SIZE / 2, SIZE / 2);
		cam.up.set(dir);
		cam.update();
		
		camInput = new OrthographicCamera(SIZE, SIZE);
		
		camInput.translate(SIZE / 2, SIZE / 2);
		camInput.update();
		
		blank = Ld37Game.getGame().getAssetManager().get("blank.png", Texture.class);
	}
	
	public abstract void update (float delta, boolean currentWall);
	
	/**
	 * Only called if this is the current screen
	 */
	public abstract void handleInput ();
	protected abstract void render (SpriteBatch batch);
	public abstract void dispose ();
	
	/**
	 * Returns the texture for the current frame
	 * @param batch
	 * @return
	 */
	public TextureRegion getTexture (SpriteBatch batch) {
		fbo.begin();
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		batch.setColor(0.5f, 0.5f, 0.5f, 1);
		render(batch);
		batch.end();
		fbo.end();
		
		TextureRegion fboTexture = new TextureRegion(fbo.getColorBufferTexture());
		return fboTexture;
	}
	
	/**
	 * Returns coordinate clicked on wall, in the 64x64 region
	 * @return
	 */
	protected Vector3 getMouseInput () {
		return camInput.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
	}
	
}
