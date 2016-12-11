package net.hollowbit.ld37.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import minigames.ld_manager;
import net.hollowbit.ld37.Ld37Game;


public class TestScreen extends ScreenAdapter {

	private static final int SIZE = 64;
	
	private SpriteBatch batch;
	
	private Texture blank;
	private ld_manager testGame = new ld_manager(null);
	
	OrthographicCamera cam;
	
	public TestScreen (SpriteBatch batch) {
		blank = Ld37Game.getGame().getAssetManager().get("tv_a.png", Texture.class);
		this.batch = batch;
		cam = new OrthographicCamera(SIZE, SIZE);
		
		cam.translate(SIZE / 2, SIZE / 2);
		cam.update();
	}
	
	@Override
	public void show () {
		
		super.show();
	}
	
	
	@Override
	public void render (float delta) {
		testGame.update(delta, false);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		batch.draw(blank, 0, 0, SIZE, SIZE);
		testGame.render(this.batch);
		batch.end();
		super.render(delta);
		
	
	}
	
	@Override
	public void dispose () {
		blank.dispose();
		super.dispose();
	}
	
	
}
