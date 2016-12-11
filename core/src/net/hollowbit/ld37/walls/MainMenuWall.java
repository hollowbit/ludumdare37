package net.hollowbit.ld37.walls;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import net.hollowbit.ld37.Ld37Game;
import net.hollowbit.ld37.screens.RoomScreen;
import net.hollowbit.ld37.screens.State;
import ui.ld_button;

public class MainMenuWall extends Wall {
	
	private static final float BUTTON_PADDING = 3;//px
	
	ld_button playButton;
	ld_button sttgsButton;
	ld_button quitButton;
	
	public MainMenuWall (Vector3 dir, RoomScreen roomScreen) {
		super(dir, roomScreen);
		
		playButton = new ld_button("PLAY", SIZE / 2, 40, BUTTON_PADDING);
		sttgsButton = new ld_button("STTGS", SIZE / 2, 25, BUTTON_PADDING);
		
		if (Gdx.app.getType() != ApplicationType.WebGL)
			quitButton = new ld_button("QUIT", SIZE / 2, 10, BUTTON_PADDING);
	}

	@Override
	public void update (float delta) {

	}
	
	@Override
	public void handleInput () {
		if (Gdx.input.justTouched()) {
			Vector3 mousePos = getMouseInput();
			System.out.println("x: " + mousePos.x + "  y: " + mousePos.y);
			if (playButton.checkMouse(mousePos))
				roomScreen.setState(State.GAME);
			else if (sttgsButton.checkMouse(mousePos))
				roomScreen.setState(State.OPTNS);
			else if (quitButton != null && quitButton.checkMouse(mousePos))
				Gdx.app.exit();
		}
	}

	@Override
	protected void render (SpriteBatch batch) {
		batch.draw(Ld37Game.getGame().getAssetManager().get("menu.png", Texture.class), 0, 0);
		
		playButton.render(batch);
		sttgsButton.render(batch);
		if (quitButton != null)
			quitButton.render(batch);
	}

	@Override
	public void dispose () {

	}

}
