package minigames.games;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;

import minigames.ld_minibase;
import minigames.ld_timer;
import net.hollowbit.ld37.Ld37Game;

public class MathGame extends ld_minibase {
	
	Texture nose = new Texture("games/nose.png");
	ld_timer timer;
	int X, Y, A;
	char[] oprts = new char[]{'x','/','-','+'};
	char op;
	public MathGame (GameEndHandler... gameEndHandlers) {
		super(gameEndHandlers);
		timer = new ld_timer(3);
		
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
		case '/':
			A=X/Y;
			break;
		case '-':
			A=X-Y;
			break;
		default:
			A=X+Y;
			break;
		}
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
			endGame(false);
			timer = new ld_timer(3);
		}
	}

	@Override
	protected void renPlay (SpriteBatch batch) {
		batch.draw(nose, X_OFFSET, Y_OFFSET);
	}

	@Override
	protected void renTut (SpriteBatch batch) {
	
	}

	@Override
	public void handleInput (boolean isZPressed, boolean isXPressed, boolean isZJustPressed, boolean isXJustPressed) {
		//Handle input here
	}

}