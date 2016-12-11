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
		batch.setColor(0.3f, 0.3f, 0.3f, 1);
		batch.draw(textures[5],0,0);
	}

	@Override
	public void dispose () {
		
	}
	
}
