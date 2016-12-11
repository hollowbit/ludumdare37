package net.hollowbit.ld37;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.hollowbit.ld37.screens.RoomScreen;

public class Ld37Game extends Game {
	
	private boolean quitButt;
	public Ld37Game(boolean quitButt){
		this.quitButt = quitButt;
	}
	SpriteBatch batch;
	AssetManager assetManager;
	BitmapFont font;
	
	@Override
	public void create () {
		game = this;
		batch = new SpriteBatch();
		
		assetManager = new AssetManager();
		this.loadAssets();
		assetManager.finishLoading();

		font = new BitmapFont();
		font.setColor(new Color(1f,1f,0f,1f));
		font.getData().setScale(0.5f);
		this.setScreen(new RoomScreen(batch,quitButt));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
		batch.dispose();
		assetManager.dispose();
	}
	
	public AssetManager getAssetManager () {
		return assetManager;
	}
	
	public BitmapFont getFont () {
		return font;
	}
	
	private void loadAssets () {
		//Images
		assetManager.load("badlogic.jpg", Texture.class);
		assetManager.load("blank.png", Texture.class);
	}
	
	
	private static Ld37Game game;
	public static Ld37Game getGame() {
		return game;
	}
	
}
