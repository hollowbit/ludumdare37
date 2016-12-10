package net.hollowbit.ld37.walls;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameWall extends Wall {

	@Override
	public void update (float delta) {

	}

	@Override
	protected void render (SpriteBatch batch) {
		batch.setColor(1f, 0, 0, 1);
		batch.draw(blank, 0, 0, SIZE, SIZE);
	}

	@Override
	public void dispose () {

	}

}
