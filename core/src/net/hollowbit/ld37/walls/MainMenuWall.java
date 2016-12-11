package net.hollowbit.ld37.walls;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import net.hollowbit.ld37.Ld37Game;
import ui.ld_button;

public class MainMenuWall extends Wall {
	public ld_button play = new ld_button(new Texture("ui_box.png"), 16, 8,32,16, 4);
	public ld_button options = new ld_button(new Texture("ui_box.png"), 16, 26,32,16, 4);
	public ld_button quit = new ld_button(new Texture("ui_box.png"), 16, 44,32,16, 4);
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
		batch.setColor(1f, 1f, 1f, 1);
		play.draw(batch);
		options.draw(batch);
		quit.draw(batch);
		batch.setColor(1f, 1f, 0f, 1);
		Ld37Game.getGame().getFont().draw(batch, "PLAY",23, 55);
		Ld37Game.getGame().getFont().draw(batch, "STTGS",20, 37);
		Ld37Game.getGame().getFont().draw(batch, "QUIT",23, 19);
	}

	@Override
	public void dispose () {

	}

}
