package net.hollowbit.ld37.walls;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import net.hollowbit.ld37.Ld37Game;
import net.hollowbit.ld37.screens.RoomScreen;

public class CeilingWall extends Wall {

	public CeilingWall(Vector3 dir, RoomScreen roomScreen) {
		super(dir, roomScreen);
	}

	@Override
	public void update (float delta) {

	}

	@Override
	protected void render (SpriteBatch batch) {
		batch.draw(Ld37Game.getGame().getAssetManager().get("purp_b.png", Texture.class), 0, 0);
	}

	@Override
	public void dispose () {

	}

	@Override
	public void handleInput() {}

}
