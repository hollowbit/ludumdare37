package net.hollowbit.ld37.walls;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class OptionsWall extends Wall {

	@Override
	public void update (float delta) {

	}

	@Override
	protected void render (SpriteBatch batch) {
		batch.setColor(0.5f, 1, 0f, 1);
		batch.draw(blank, 0, 0, SIZE, SIZE);
	}

	@Override
	public void dispose () {

	}

}
