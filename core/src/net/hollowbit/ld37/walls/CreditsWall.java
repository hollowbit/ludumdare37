package net.hollowbit.ld37.walls;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class CreditsWall extends Wall {

	public CreditsWall(Vector3 dir) {
		super(dir);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update (float delta) {
		
	}

	@Override
	protected void render (SpriteBatch batch) {
		batch.setColor(1f, 1f, 0f, 1);
		batch.draw(textures[5],
                0,
                0,
                32,
                32,
                64,
                64,
                1,
                1,
                90,
                0,
                0,
                64,
                64,
                true, false);
	}

	@Override
	public void dispose () {
		
	}
	
}
