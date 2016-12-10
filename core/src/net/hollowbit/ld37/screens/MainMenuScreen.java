package net.hollowbit.ld37.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainMenuScreen extends ScreenAdapter {
	
	SpriteBatch batch;
	Texture texture;
	
	public MainMenuScreen (SpriteBatch batch) {
		this.batch = batch;
		texture = new Texture("badlogic.jpg");
	}
	
	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		batch.draw(texture, 50, 50);
		batch.end();
		super.render(delta);
	}
	
	@Override
	public void dispose () {
		texture.dispose();
		super.dispose();
	}
	
}
