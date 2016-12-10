package net.hollowbit.ld37.walls;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.hollowbit.ld37.Ld37Game;

public class MainMenuWall extends Wall {

	@Override
	public void update (float delta) {

	}

	@Override
	protected void render (SpriteBatch batch) {
		batch.setColor(0, 0, 1f, 1);
		batch.draw(blank, 0, 0, SIZE, SIZE);
		
		Ld37Game.getGame().getFont().draw(batch, "The Game", 40, 10);
	}

	@Override
	public void dispose () {

	}

}
