package net.hollowbit.ld37.walls;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import minigames.game_jump;
import minigames.game_nose;

public class GameWall extends Wall {
	public GameWall(Vector3 dir) {
		super(dir);
		// TODO Auto-generated constructor stub
	}

	private int TEX_R = 64;
	private int R = SIZE/TEX_R;
	private game_jump testGame = new game_jump(TEX_R,R*6,R*7,R*52,R*52);
	//private game_nose nosePick = new game_nose(0,0,64,64);
	@Override
	public void update (float delta) {
		testGame.update(delta);
	}

	@Override
	protected void render (SpriteBatch batch) {
		batch.setColor(0.3f, 0.3f, 0.3f, 1);
		batch.draw(textures[0],0,0);
		batch.setColor(1f, 1f, 1f, 1);
		//nosePick.readKeys();
		//nosePick.render(batch);
		testGame.readKeys();
		testGame.render(batch);
	}

	@Override
	public void dispose () {

	}

}
