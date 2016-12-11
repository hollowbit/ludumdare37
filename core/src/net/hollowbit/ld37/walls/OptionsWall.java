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
	}

	@Override
	public void dispose () {

	}

	@Override
	public void handleInput() {
		if (Gdx.input.justTouched()) {
			Vector3 mousePos = getMouseInput();
			System.out.println(mousePos);
			if (menuButton.checkMouse(mousePos))
				roomScreen.setState(State.MAIN);
		}
	}

}
