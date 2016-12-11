package net.hollowbit.ld37.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import minigames.game_jump;
import minigames.game_nose;
import minigames.ld_manager;


public class TestScreen extends ScreenAdapter {


	
	private SpriteBatch batch;
	
	private Texture blank;
	private int WALL_H  = 1024, WALL_W = 1024;
	private int TEX_R = 64;
	private int R = WALL_H/TEX_R;
	private ld_manager testGame = new ld_manager();
	public TestScreen (SpriteBatch batch) {
		blank = new Texture("tv_a.png");
		this.batch = batch;
	}
	
	@Override
	public void show () {
		
		super.show();
	}
	
	
	@Override
	public void render (float delta) {
		testGame.update(delta);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		batch.draw(blank, 0, 0,WALL_W,WALL_H);
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
