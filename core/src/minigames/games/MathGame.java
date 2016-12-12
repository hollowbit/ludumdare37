package minigames.games;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

import minigames.ld_minibase;
import minigames.ld_timer;
import net.hollowbit.ld37.Ld37Game;

public class MathGame extends ld_minibase {
	
	Texture nose = new Texture("games/nose.png");
	ld_timer timer;
	int X, Y, A, F; //X, Y, Answer, and Possible Answer
	char[] oprts = new char[]{'x','-','+'};
	char op;
	GlyphLayout tutLayout;
	GlyphLayout mathLayout1;
	GlyphLayout mathLayout2;
	boolean isSelected = false;
	public MathGame (GameEndHandler... gameEndHandlers) {
		super(gameEndHandlers);
		timer = new ld_timer(3);
		tutLayout = new GlyphLayout(Ld37Game.getGame().getFont(), "Press Z to solve the operation", Color.ORANGE, WIDTH, Align.left, true);
	}
	
	private void getNewOperation() {
		Random randomGenerator = new Random();
		X = randomGenerator.nextInt(12)+4;
		Y = randomGenerator.nextInt(12)+4;
		op = oprts[randomGenerator.nextInt(3)];
		switch (op){
		case 'x':
			A=X*Y;
			break;
		case '-':
			A=X-Y;
			break;
		default:
			A=X+Y;
			break;
		}
		mathLayout1 = new GlyphLayout(Ld37Game.getGame().getFont(), X + " " + op + " " + Y, Color.ORANGE, WIDTH, Align.left, true);
		F = randomGenerator.nextInt(2);
		if (F == 0)
			F = A;
		else
			F = A + randomGenerator.nextInt(5)+1;
		mathLayout2 = new GlyphLayout(Ld37Game.getGame().getFont(),Integer.toString(F), Color.ORANGE, WIDTH, Align.left, true);
	}

	@Override
	public void start () {
		getNewOperation();
	}

	@Override
	protected void upPlay (float delta) {
		//Update play here
		//To end a game, call endGame(won);
		timer.count(delta);
		if (timer.done) {
			if(A == F){
				endGame(isSelected);
			} else{
				if (isSelected)
					endGame(false);
			}
			getNewOperation();
			timer = new ld_timer(3);

		}
	}

	@Override
	protected void renPlay (SpriteBatch batch) {
		Ld37Game.getGame().getFont().draw(batch, mathLayout1, X_OFFSET + WIDTH / 2 - mathLayout1.width / 2, Y_OFFSET + HEIGHT / 2 + mathLayout1.height / 2);
		Ld37Game.getGame().getFont().draw(batch, mathLayout2, X_OFFSET + WIDTH / 2 - mathLayout2.width / 2, Y_OFFSET + HEIGHT / 3 + mathLayout2.height / 2);
	}

	@Override
	protected void renTut (SpriteBatch batch) {
		Ld37Game.getGame().getFont().draw(batch, tutLayout, X_OFFSET + WIDTH / 2 - tutLayout.width / 2, Y_OFFSET + HEIGHT / 2 + tutLayout.height / 2);
	}

	@Override
	public void handleInputPrivate (boolean isZPressed, boolean isXPressed, boolean isZJustPressed, boolean isXJustPressed) {
		if (isZJustPressed) {
			isSelected = true;
			Ld37Game.getGame().playSfx("games/hit.wav");
		}
			
	}

}