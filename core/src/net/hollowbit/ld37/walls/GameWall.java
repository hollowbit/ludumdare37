package net.hollowbit.ld37.walls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import minigames.ld_manager;
import net.hollowbit.ld37.Ld37Game;
import net.hollowbit.ld37.screens.RoomScreen;
import net.hollowbit.ld37.screens.State;
import ui.ld_button;

public class GameWall extends Wall {
	
	private ld_button menuButton;
	private ld_manager ldManager;
	
	public GameWall(Vector3 dir, RoomScreen roomScreen) {
		super(dir, roomScreen);
		ldManager = new ld_manager(roomScreen);
	}
	
	@Override
	public void update (float delta, boolean currentWall) {
		menuButton = new ld_button("MENU", SIZE / 2, SIZE - 10, 3);
		ldManager.update(delta, !currentWall);//Game gets paused if not on current wall
	}

	@Override
	protected void render (SpriteBatch batch) {
		batch.draw(Ld37Game.getGame().getAssetManager().get("tv_a.png", Texture.class), 0, 0);
		batch.setColor(1f, 1f, 1f, 1);
		ldManager.render(batch);
		menuButton.render(batch);
	}

	@Override
	public void dispose () {

	}
	
	public void getNextGame () {
		ldManager.getNextGame();
	}

	@Override
	public void handleInput() {
		if (Gdx.input.justTouched()) {
			Vector3 mousePos = getMouseInput();
			if (menuButton.checkMouse(mousePos))
				roomScreen.setState(State.MAIN);
		}
		
		ldManager.handleInput();
	}

}
