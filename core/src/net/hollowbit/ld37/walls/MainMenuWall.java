package net.hollowbit.ld37.walls;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import net.hollowbit.ld37.Ld37Game;

public class MainMenuWall extends Wall {

	public MainMenuWall(Vector3 dir) {
		super(dir);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update (float delta) {

	}

	@Override
	protected void render (SpriteBatch batch) {
		batch.setColor(1f, 1f, 0f, 1);
		batch.draw(textures[1],0,0);

		
		//Ld37Game.getGame().getFont().draw(batch, "The Game",0, SIZE);
	}

	@Override
	public void dispose () {

	}

}
