package minigames.games;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

import minigames.ld_minibase;
import minigames.ld_timer;
import net.hollowbit.ld37.Ld37Game;

public class JumpGame extends ld_minibase {

	private static final int FINGER_OFFSET = 9;
	private static final int NOSTRIL_1_OFFSET = 3;
	private static final int NOSTRIL_2_OFFSET = 14;
	private static final int NOSTRIL_WIDTH = 6;
	
	Texture saw = new Texture("games/saw.png");
	Texture dude = new Texture("games/character.png");
	Texture ground = new Texture("games/floor.png");
	ld_timer timer;
	
	float dudeX = X_OFFSET, dudeY = Y_OFFSET + 11;
	float dudeSpeed = 0.2f, sawSpeed;
	float saw1X, saw2X;
	GlyphLayout tutLayout;
	
	boolean stopFinger = false;
	private boolean grounded = true;
	
	public JumpGame (GameEndHandler... gameEndHandlers) {
		super(gameEndHandlers);
		timer = new ld_timer(5);
		

		
		tutLayout = new GlyphLayout(Ld37Game.getGame().getFont(), "Press Z to jump!", Color.ORANGE, WIDTH, Align.left, true);
	}
	
	@Override
	public void start () {
		//Code to run at game start
	}

	@Override
	protected void upPlay (float delta) {
	
		if (dudeY == Y_OFFSET +11){
			grounded = true;
		}else
		dudeY-=dudeSpeed*delta;
		
			
		timer.count(delta);
		if (timer.done) {
			endGame(true);
		}
	}

	@Override
	protected void renPlay (SpriteBatch batch) {
		batch.draw(dude, X_OFFSET + WIDTH / 2 - dude.getWidth() / 2, Y_OFFSET + 11);
		batch.draw(ground, X_OFFSET, Y_OFFSET);
		
		GlyphLayout timerLayout = new GlyphLayout(Ld37Game.getGame().getFont(), String.format("%.1f", timer.maxTime - timer.timer) + "s");
		Ld37Game.getGame().getFont().draw(batch, timerLayout, X_OFFSET + WIDTH / 2 - timerLayout.width / 2, Y_OFFSET);
	}

	@Override
	protected void renTut (SpriteBatch batch) {
		Ld37Game.getGame().getFont().draw(batch, tutLayout, X_OFFSET + WIDTH / 2 - tutLayout.width / 2, Y_OFFSET + HEIGHT / 2 + tutLayout.height / 2);
	}

	@Override
	public void handleInputPrivate (boolean isZPressed, boolean isXPressed, boolean isZJustPressed, boolean isXJustPressed) {
		if (isZJustPressed){
			dudeSpeed+=12;
			dudeY+=1;
			Ld37Game.getGame().playSfx("hit.wav");
			grounded = false;
		}
	}

}