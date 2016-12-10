package net.hollowbit.ld37.walls;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FloorWall extends Wall {

	@Override
	public void update (float delta) {
	}

	@Override
	protected void render (SpriteBatch batch) {
		batch.setColor(0.5f, 0, 0.25f, 1);
		batch.draw(blank, 0, 0, SIZE, SIZE);
	}

	@Override
	public void dispose () {

	}

}
