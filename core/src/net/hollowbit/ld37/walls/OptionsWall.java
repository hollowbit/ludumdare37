package net.hollowbit.ld37.walls;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import net.hollowbit.ld37.Ld37Game;
import net.hollowbit.ld37.screens.RoomScreen;
import net.hollowbit.ld37.screens.State;
import ui.ld_button;

public class OptionsWall extends Wall {
	
	private static int FISHIE_SPEED = 10;
	private static int FISHIE_Y_MAX = 40;
	private static int FISHIE_Y_MIN = 20;
	
	ld_button menuButton;
	ld_button sfxMuteButton;
	ld_button musicMuteButton;
	
	float fishieX = SIZE, fishieY;
	Random ran = new Random();
	
	public OptionsWall(Vector3 dir, RoomScreen roomScreen) {
		super(dir, roomScreen);
		menuButton = new ld_button("MENU", SIZE / 2, SIZE - 10, 3);
		fishieY = ran.nextInt((FISHIE_Y_MAX - FISHIE_Y_MIN + 1)) + FISHIE_Y_MIN;
	}

	@Override
	public void update (float delta, boolean currentWall) {
		fishieX -= FISHIE_SPEED * delta;
		if (fishieX < 0) {
			fishieX = SIZE; 
			fishieY = ran.nextInt((FISHIE_Y_MAX - FISHIE_Y_MIN + 1)) + FISHIE_Y_MIN;
		}
		
	}

	@Override
	protected void render (SpriteBatch batch) {
		batch.setColor(0, 87f / 255, 132f / 255, 1);
		batch.draw(Ld37Game.getGame().getAssetManager().get("blank.png", Texture.class), 0, 0, SIZE, SIZE);
		batch.setColor(1, 1, 1, 1);
		batch.draw(Ld37Game.getGame().getAssetManager().get("fish.png", Texture.class), fishieX, fishieY);
		batch.draw(Ld37Game.getGame().getAssetManager().get("options.png", Texture.class), 0, 0);
		menuButton.render(batch);
	
		sfxMuteButton = new ld_button("SFX " + (Ld37Game.SFX_ON ? "ON" : "OFF"), SIZE / 4, SIZE / 2 + 5, 3);
		sfxMuteButton.render(batch);
		
		musicMuteButton = new ld_button("MUSIC " + (Ld37Game.MUSIC_ON ? "ON" : "OFF"), SIZE - SIZE / 3, SIZE / 2 - 20, 3);
		musicMuteButton.render(batch);
	}

	@Override
	public void dispose () {

	}

	@Override
	public void handleInput() {
		if (Gdx.input.justTouched()) {
			Vector3 mousePos = getMouseInput();
			if (menuButton.checkMouse(mousePos))
				roomScreen.setState(State.MAIN);
			if (sfxMuteButton.checkMouse(mousePos))
				Ld37Game.SFX_ON = !Ld37Game.SFX_ON;
			if (musicMuteButton.checkMouse(mousePos))
				Ld37Game.MUSIC_ON = !Ld37Game.MUSIC_ON;
		}
	}

}
