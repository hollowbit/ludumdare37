package net.hollowbit.ld37.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
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

public class MainMenuScreen extends ScreenAdapter {
	
	public static final float MOVE_SPEED = 0.3f;
	
	SpriteBatch batch;
	FrameBuffer fbo;
	DecalBatch decalBatch;
	PerspectiveCamera cam;
	Texture texture;
	Texture blank;
	Decal decal;
	Decal decal2;
	
	float x = 0, y = 0, z = 5;
	
	public MainMenuScreen (SpriteBatch batch) {
		this.batch = batch;
		fbo = new FrameBuffer(Format.RGBA8888, 300, 300, false);
		texture = new Texture("badlogic.jpg");
		blank = new Texture("blank.png");
		
		cam = new PerspectiveCamera(45, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.near = 1;
		cam.far = 300;
		cam.position.set(x, y, z);
		
		CameraGroupStrategy cameraGroupStrategy = new CameraGroupStrategy(cam);
		decalBatch = new DecalBatch(cameraGroupStrategy);
	}
	
	@Override
	public void render (float delta) {
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
		
		fbo.begin();
		batch.begin();
		batch.draw(blank, 0, 0, 300, 300);
		batch.draw(texture, 40, 40);
		batch.end();
		fbo.end();
		
		TextureRegion fboTexture = new TextureRegion(fbo.getColorBufferTexture());
		fboTexture.flip(false, true);
		
		decal = Decal.newDecal(fboTexture, true);
		decal.setPosition(0, 0, 0);
		decal.setScale(0.01f);
		
		/*decal2 = Decal.newDecal(fboTexture, true);
		decal2.setPosition(0.5f, 0.3f, 0.2f);
		decal2.setScale(0.01f);
		decal2.setRotation(0, 15, 20);*/

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);
		cam.position.set(x, y, z);
		cam.update();
		
		decalBatch.add(decal);
		//decalBatch.add(decal2);
		//decal.lookAt(cam.position, cam.up);
		decalBatch.flush();
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}
	
}