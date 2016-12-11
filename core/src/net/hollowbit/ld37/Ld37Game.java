package net.hollowbit.ld37;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.hollowbit.ld37.screens.RoomScreen;

public class Ld37Game extends Game {
	
	public static boolean SFX_ON = true;
	public static boolean MUSIC_ON = true;
	
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
		this.setScreen(new RoomScreen(batch));
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
		assetManager.load("water.png", Texture.class);
		assetManager.load("ui_box.png", Texture.class);
		
		  //Walls
		  assetManager.load("tv_a.png", Texture.class);
		  assetManager.load("menu.png", Texture.class);
		  assetManager.load("options.png", Texture.class);
		  assetManager.load("purp_b.png", Texture.class);
		  
		  //Nose Game
		  assetManager.load("games/nose.png", Texture.class);
		  assetManager.load("games/finger.png", Texture.class);
		  
		//Sounds
	}
	
	
	private static Ld37Game game;
	public static Ld37Game getGame() {
		return game;
	}
	
}
