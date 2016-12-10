package net.hollowbit.ld37.walls;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class FloorWall extends Wall {
	
	public FloorWall (Vector3 position, Vector3 rotation, int width, int height) {
		super(position, rotation, width, height);
	}

	@Override
	public void update (float delta) {
	}

	@Override
	public void render (SpriteBatch batch) {
		batch.setColor(0.5f, 0, 0.25f, 1);
		batch.draw(blank, 0, 0, width, height);
	}

	@Override
	public void dispose () {

	}

}
