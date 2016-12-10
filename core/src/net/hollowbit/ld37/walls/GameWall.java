package net.hollowbit.ld37.walls;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import minigames.game_jump;

public class GameWall extends Wall {
	public GameWall(Vector3 dir) {
		super(dir);
		// TODO Auto-generated constructor stub
	}

	private int TEX_R = 64;
	private int R = SIZE/TEX_R;
	private game_jump testGame = new game_jump(SIZE,R*6,R*7,R*52,R*52);
	@Override
	public void update (float delta) {

	}

	@Override
	protected void render (SpriteBatch batch) {
		batch.setColor(1f, 1f, 0f, 1);
		batch.draw(textures[0],0,0);
		testGame.update(0.000001f);
		testGame.readKeys();
		testGame.render(batch);
	}

	@Override
	public void dispose () {

	}

}
