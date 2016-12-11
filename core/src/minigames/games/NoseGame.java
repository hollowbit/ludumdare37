package minigames.games;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;

import minigames.ld_minibase;
import minigames.ld_timer;

public class NoseGame extends ld_minibase {
	
	Texture nose = new Texture("games/nose.png");
	ld_timer timer;
	
	public NoseGame (GameEndHandler... gameEndHandlers) {
		super(gameEndHandlers);
		timer = new ld_timer(3);
	}
	
	@Override
	public void start () {
		//Code to run at game start
	}

	@Override
	protected void upPlay (float delta) {
		//Update play here
		//To end a game, call endGame(won);
		timer.count(delta);
		if (timer.done) {
			endGame(false);
		}
	}

	@Override
	protected void renPlay (SpriteBatch batch) {
		batch.draw(nose, X_OFFSET, Y_OFFSET);
	}

	@Override
	protected void renTut (SpriteBatch batch) {
		//Render tutorial stuff here
	}

	@Override
	public void handleInput (boolean isZPressed, boolean isXPressed, boolean isZJustPressed, boolean isXJustPressed) {
		//Handle input here
	}

}