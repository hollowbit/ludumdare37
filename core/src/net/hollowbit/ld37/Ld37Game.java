package net.hollowbit.ld37;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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
	BitmapFont fontHd;
	
	@Override
	public void create () {
		game = this;
		batch = new SpriteBatch();
		
		assetManager = new AssetManager();
		this.loadAssets();
		assetManager.finishLoading();
		assetManager.get("alpha_wave_m0nster.wav", Music.class).play();
		assetManager.get("alpha_wave_m0nster.wav", Music.class).setLooping(true);

		font = new BitmapFont(Gdx.files.internal("main.fnt"));
		fontHd = new BitmapFont(Gdx.files.internal("hd.fnt"));
		font.setColor(new Color(1f,1f,0f,1f));
		font.getData().setScale(0.5f);
		this.setScreen(new RoomScreen(batch));
	}

	@Override
	public void render () {
		super.render();
		
		if (!MUSIC_ON)
			assetManager.get("alpha_wave_m0nster.wav", Music.class).pause();
		else
			assetManager.get("alpha_wave_m0nster.wav", Music.class).play();
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
	
	public BitmapFont getFontHd () {
		return fontHd;
	}
	
	private void loadAssets () {
		//Images
		assetManager.load("blank.png", Texture.class);
		assetManager.load("water.png", Texture.class);
		assetManager.load("ui_box.png", Texture.class);
		assetManager.load("real_bubble.png", Texture.class);
		assetManager.load("fish.png", Texture.class);
		
		  //Walls
		  assetManager.load("tv_a.png", Texture.class);
		  assetManager.load("menu.png", Texture.class);
		  assetManager.load("options.png", Texture.class);
		  assetManager.load("purp_b.png", Texture.class);
		  
		  //Nose Game
		  assetManager.load("games/nose.png", Texture.class);
		  assetManager.load("games/finger.png", Texture.class);
		  
		//Sounds
		assetManager.load("alpha_wave_m0nster.wav", Music.class);
		assetManager.load("games/hit.wav", Sound.class);//How to load a sound effect
		assetManager.load("games/miss.wav", Sound.class);
	}
	
	public void playSfx (String location) {
		if (SFX_ON)
			assetManager.get("alpha_wave_m0nster.wav", Music.class).play();
	}
	
	
	private static Ld37Game game;
	public static Ld37Game getGame() {
		return game;
	}
	
}
