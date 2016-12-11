package net.hollowbit.ld37.walls;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import net.hollowbit.ld37.Ld37Game;

public abstract class Wall {
	
	public static final int FBO_SIZE = 1000;
	public static final int SIZE = 64;
	
	protected StretchViewport viewport;
	protected OrthographicCamera cam;
	
	protected FrameBuffer fbo;
	protected enum W {Game,Menu,Options,Ceiling,Floor,Credits};
	protected Texture blank;
	protected Texture[] textures = new Texture[]{new Texture("tv_a.png"),new Texture("menu.png"),new Texture("options.png"),new Texture("purp_b.png"),new Texture("purp_b.png"),new Texture("purp_b.png")};
	
	public Wall (Vector3 dir) { 
		fbo = new FrameBuffer(Format.RGBA8888, FBO_SIZE, FBO_SIZE, false);
		cam = new OrthographicCamera(SIZE, SIZE);
		
		cam.translate(SIZE / 2, SIZE / 2);
		cam.up.set(dir);
		cam.update();
		
		blank = Ld37Game.getGame().getAssetManager().get("blank.png", Texture.class);
	}
	
	public abstract void update (float delta);
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
		render(batch);
		batch.end();
		fbo.end();
		
		TextureRegion fboTexture = new TextureRegion(fbo.getColorBufferTexture());
		fboTexture.flip(false, false);
		return fboTexture;
	}
	
}
