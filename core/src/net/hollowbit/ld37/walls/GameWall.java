package net.hollowbit.ld37.walls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import minigames.game_jump;
import minigames.ld_minibase;
import net.hollowbit.ld37.Ld37Game;
import net.hollowbit.ld37.screens.RoomScreen;
import net.hollowbit.ld37.screens.State;
import ui.ld_button;

public class GameWall extends Wall {
	
	ld_button menuButton;
	
	public GameWall(Vector3 dir, RoomScreen roomScreen) {
		super(dir, roomScreen);
	}

	private int TEX_R = 64;
	private int R = SIZE/TEX_R;
	private ld_minibase testGame = new game_jump(TEX_R,R*6,R*7,R*52,R*52);
	//private game_nose nosePick = new game_nose(0,0,64,64);
	@Override
	public void update (float delta) {
		testGame.update(delta);
		menuButton = new ld_button("MENU", SIZE / 2, SIZE - 10, 3);
	}

	@Override
	protected void render (SpriteBatch batch) {
		batch.draw(Ld37Game.getGame().getAssetManager().get("tv_a.png", Texture.class), 0, 0);
		batch.setColor(1f, 1f, 1f, 1);
		//nosePick.readKeys();
		//nosePick.render(batch);
		testGame.readKeys();
		testGame.render(batch);
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
