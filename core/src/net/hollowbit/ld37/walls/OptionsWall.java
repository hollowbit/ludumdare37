package net.hollowbit.ld37.walls;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class OptionsWall extends Wall {

	public OptionsWall(Vector3 dir) {
		super(dir);
	}

	@Override
	public void update (float delta) {

	}

	@Override
	protected void render (SpriteBatch batch) {
		batch.setColor(0.3f, 0.3f, 0.3f, 1);
		batch.draw(textures[2],0,0);
	}

	@Override
	public void dispose () {

	}

}
