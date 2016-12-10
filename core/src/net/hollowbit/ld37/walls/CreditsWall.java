package net.hollowbit.ld37.walls;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class CreditsWall extends Wall {

	public CreditsWall (Vector3 position, Vector3 rotation, int width, int height) {
		super(position, rotation, width, height);
	}

	@Override
	public void update (float delta) {
		
	}

	@Override
	protected void render (SpriteBatch batch) {
		batch.setColor(0f, 1, 0.5f, 1);
		batch.draw(blank, 0, 0, width, height);
	}

	@Override
	public void dispose () {
		
	}
	
}
