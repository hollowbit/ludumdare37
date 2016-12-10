package net.hollowbit.ld37.walls;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class GameWall extends Wall {

	public GameWall (Vector3 position, Vector3 rotation, int width, int height) {
		super(position, rotation, width, height);
	}

	@Override
	public void update (float delta) {

	}

	@Override
	public void render (SpriteBatch batch) {
		batch.setColor(1f, 0, 0, 1);
		batch.draw(blank, 0, 0, width, height);
	}

	@Override
	public void dispose () {

	}

}
