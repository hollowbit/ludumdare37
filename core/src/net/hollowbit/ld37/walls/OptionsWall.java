package net.hollowbit.ld37.walls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import net.hollowbit.ld37.Ld37Game;
import net.hollowbit.ld37.screens.RoomScreen;
import net.hollowbit.ld37.screens.State;
import ui.ld_button;

public class OptionsWall extends Wall {
	
	ld_button menuButton;
	ld_button sfxMuteButton;
	ld_button musicMuteButton;
	
	public OptionsWall(Vector3 dir, RoomScreen roomScreen) {
		super(dir, roomScreen);
		menuButton = new ld_button("MENU", SIZE / 2, SIZE - 10, 3);
	}

	@Override
	public void update (float delta, boolean currentWall) {
		
	}

	@Override
	protected void render (SpriteBatch batch) {
		batch.draw(Ld37Game.getGame().getAssetManager().get("options.png", Texture.class), 0, 0);
		menuButton.render(batch);
	
		sfxMuteButton = new ld_button("SFX " + (Ld37Game.SFX_ON ? "OFF" : "ON"), SIZE / 2, SIZE / 2 + 5, 3);
		sfxMuteButton.render(batch);
		
		musicMuteButton = new ld_button("MUSIC " + (Ld37Game.MUSIC_ON ? "OFF" : "ON"), SIZE / 2, SIZE / 2 - 10, 3);
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
