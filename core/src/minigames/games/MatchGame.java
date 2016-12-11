package minigames.games;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;

import minigames.ld_minibase;
import minigames.ld_timer;
import net.hollowbit.ld37.Ld37Game;

public class MatchGame extends ld_minibase {
	
	Texture match = new Texture("games/match.png");
	Texture box = new Texture("games/match_box.png");
	Texture fire = new Texture("games/fire.png");
	
	float matchX= X_OFFSET+1, matchY = Y_OFFSET + 16;
	boolean lit = false;
	int tryCount = 0;
	ld_timer timer;
	
	GlyphLayout tutLayout;

	public MatchGame (GameEndHandler... gameEndHandlers) {
		super(gameEndHandlers);
		timer = new ld_timer(5);
		tutLayout = new GlyphLayout(Ld37Game.getGame().getFont(), "Press Z to light the match", Color.ORANGE, WIDTH, Align.left, true);
	}
	

	@Override
	public void start () {
		tryCount = 0;
	}

	@Override
	protected void upPlay (float delta) {
		//Update play here
		//To end a game, call endGame(won);
		if (tryCount >= 38)
			lit = true;
		timer.count(delta);
		if (timer.done) {
			endGame(lit);
		}
	}

	@Override
	protected void renPlay (SpriteBatch batch) {
		batch.draw(box, X_OFFSET + WIDTH / 2 - box.getWidth() / 2, Y_OFFSET + HEIGHT / 2 - box.getHeight() / 2 + 8);
		batch.draw(match, matchX+tryCount, matchY);
		if (lit) batch.draw(fire, matchX+tryCount-match.getWidth(),  matchY+match.getHeight()/3);
	}

	@Override
	protected void renTut (SpriteBatch batch) {
		Ld37Game.getGame().getFont().draw(batch, tutLayout, X_OFFSET + WIDTH / 2 - tutLayout.width / 2, Y_OFFSET + HEIGHT / 2 + tutLayout.height / 2);
	}

	@Override
	public void handleInput (boolean isZPressed, boolean isXPressed, boolean isZJustPressed, boolean isXJustPressed) {
		if (isZJustPressed){
			if (tryCount < 38) tryCount++;
			System.out.println(tryCount);
		}
			
	}

}