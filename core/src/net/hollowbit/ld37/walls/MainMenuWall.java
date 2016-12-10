package net.hollowbit.ld37.walls;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class MainMenuWall extends Wall {

	public MainMenuWall (Vector3 position, Vector3 rotation, int width, int height) {
		super(position, rotation, width, height);
	}

	@Override
	public void update (float delta) {

	}

	@Override
	protected void render (SpriteBatch batch) {
		batch.setColor(0, 0, 1f, 1);
		batch.draw(blank, 0, 0, width, height);
	}

	@Override
	public void dispose () {

	}

}
