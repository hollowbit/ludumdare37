package minigames.games;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;

import minigames.ld_minibase;
import minigames.ld_timer;
import net.hollowbit.ld37.Ld37Game;

public class KayakGame extends ld_minibase {
	
	Texture finish = new Texture("games/finish_line.png");
	Texture kayak_idle = new Texture("games/kayak_idle.png");
	Texture kayak_left = new Texture("games/kayak_left.png");
	Texture kayak_right = new Texture("games/kayak_right.png");
	int strokes = 28;

	boolean canForce = true;

	ld_timer timer1, timer2;
	
	GlyphLayout tutLayout;
	private Texture kayak1 = kayak_idle;
	private Texture kayak2 = kayak_idle;
	private float kayak2Y;
	private float kayak1Y;
	private float finishY = Y_OFFSET + HEIGHT - finish.getHeight() - 3;
	public KayakGame (GameEndHandler... gameEndHandlers) {
		super(gameEndHandlers);
		timer1 = new ld_timer(0.2f);
		timer2 = new ld_timer(0.3f);
		tutLayout = new GlyphLayout(Ld37Game.getGame().getFont(), "Alternate Z and X to win.", Color.ORANGE, WIDTH, Align.left, true);
	}
	

	@Override
	public void start () {

	}

	@Override
	protected void upPlay (float delta) {
		//Update play here
		//To end a game, call endGame(won);
		if (kayak1Y+kayak1.getHeight() >= finishY)
			endGame(true);
		if (kayak2Y+kayak2.getHeight()  >= finishY)
			endGame(false);
		
		
		timer1.count(delta);
		timer2.count(delta);
		if (timer1.done) {
			timer1 = new ld_timer(0.2f);
			canForce=true;
		}
		
		if (timer2.done) {
			timer2 = new ld_timer(0.3f);
			kayak2Y+=1;
			kayak2 = (kayak2 == kayak_idle)?kayak_left:(kayak2 == kayak_left)?kayak_right:kayak_left;
			Ld37Game.getGame().playSfx("games/miss.wav");
		}
	}

	@Override
	protected void renPlay (SpriteBatch batch) {
		batch.draw(kayak1,X_OFFSET + 2,Y_OFFSET + kayak1Y);
		batch.draw(kayak2,X_OFFSET + 32, Y_OFFSET + kayak2Y);
		batch.draw(finish, X_OFFSET + 3 , finishY);
	}

	@Override
	protected void renTut (SpriteBatch batch) {
		Ld37Game.getGame().getFont().draw(batch, tutLayout, X_OFFSET + WIDTH / 2 - tutLayout.width / 2, Y_OFFSET + HEIGHT / 2 + tutLayout.height / 2);
	}

	@Override
	public void handleInputPrivate (boolean isZPressed, boolean isXPressed, boolean isZJustPressed, boolean isXJustPressed) {
		if (canForce){
			if ((kayak1 == kayak_right || kayak1 == kayak_idle) && isZJustPressed){
				kayak1 = kayak_left;
				canForce=false;
				kayak1Y+=1;
				Ld37Game.getGame().playSfx("games/hit.wav");
			} else if ((kayak1 == kayak_left || kayak1 == kayak_idle) && isXJustPressed){
				kayak1 = kayak_right;
				canForce=false;
				kayak1Y+=1;
				Ld37Game.getGame().playSfx("games/hit.wav");
			} 
		}
			
	}

}