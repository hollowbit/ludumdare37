package net.hollowbit.ld37.walls;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import net.hollowbit.ld37.Ld37Game;
import ui.ld_nine;

public class MainMenuWall extends Wall {
	public ld_nine quit; 
	public ld_nine options = new ld_nine(new Texture("ui_box.png"), 16, 26,32,16, 4);
	public ld_nine play = new ld_nine(new Texture("ui_box.png"), 16, 44,32,16, 4);
	private boolean quitButt;
	public MainMenuWall(Vector3 dir, boolean quitButt) {
		super(dir);
		this.quitButt = quitButt;
		if (quitButt) quit = new ld_nine(new Texture("ui_box.png"), 16, 8,32,16, 4);
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
		if (quitButt) quit.draw(batch);
		batch.setColor(1f, 1f, 0f, 1);
		Ld37Game.getGame().getFont().draw(batch, "PLAY",23, 55);
		Ld37Game.getGame().getFont().draw(batch, "STTGS",20, 37);
		if (quitButt) Ld37Game.getGame().getFont().draw(batch, "QUIT",23, 19);
	}

	@Override
	public void dispose () {

	}

}
