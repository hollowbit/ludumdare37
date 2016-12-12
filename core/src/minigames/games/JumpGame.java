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
	
	private static final float GRAVITY = 30f;
	private static final float JUMP_SPEED = 25f;
	
	Texture saw = new Texture("games/saw.png");
	Texture dude = new Texture("games/character.png");
	Texture ground = new Texture("games/floor.png");
	ld_timer timer;
	
	float dudeX = X_OFFSET, dudeY = ground.getHeight();
	float dudeSpeed = -0.2f, sawSpeed;
	float saw1X = 1, saw2X = WIDTH - saw.getWidth() - 3;
	float saw1Speed = 23, saw2Speed = -23;
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
		dudeSpeed -= GRAVITY * delta;
		
		dudeY += dudeSpeed * delta;
		if (dudeY < ground.getHeight()) {
			dudeY = ground.getHeight();
			dudeSpeed = 0;
			grounded = true;
		}
		
		saw1X += saw1Speed * delta;
		if (saw1X < 1) {
			saw1X = 1;
			saw1Speed = -saw1Speed;
		}
		
		if (saw1X > WIDTH - saw.getWidth() - 3) {
			saw1X = WIDTH - saw.getWidth() - 3;
			saw1Speed = -saw1Speed;
		}
		
		saw2X += saw2Speed * delta;
		if (saw2X < 1) {
			saw2X = 1;
			saw2Speed = -saw2Speed;
		}
		
		if (saw2X > WIDTH - saw.getWidth() - 3) {
			saw2X = WIDTH - saw.getWidth() - 3;
			saw2Speed = -saw2Speed;
		}
		
		dudeX = WIDTH / 2 - dude.getWidth() / 2;
		boolean collidesWithSaw1 = dudeX < saw1X + saw.getWidth() / 4 + saw.getWidth() / 2 && dudeX + dude.getWidth() > saw1X + saw.getWidth() / 4 && dudeY < ground.getHeight() + saw.getHeight();
		boolean collidesWithSaw2 = dudeX < saw2X + saw.getWidth() / 4 + saw.getWidth() / 2 && dudeX + dude.getWidth() > saw2X + saw.getWidth() / 4 && dudeY < ground.getHeight() + saw.getHeight();
		
		if (collidesWithSaw1 || collidesWithSaw2)
			endGame(false);
		
		timer.count(delta);
		if (timer.done) {
			endGame(true);
		}
	}

	@Override
	protected void renPlay (SpriteBatch batch) {
		batch.draw(dude, X_OFFSET + WIDTH / 2 - dude.getWidth() / 2, Y_OFFSET + dudeY);
		batch.draw(ground, X_OFFSET, Y_OFFSET);

		batch.draw(saw, X_OFFSET + saw1X, Y_OFFSET + ground.getHeight());
		batch.draw(saw, X_OFFSET + saw2X, Y_OFFSET + ground.getHeight());
		
		GlyphLayout timerLayout = new GlyphLayout(Ld37Game.getGame().getFont(), String.format("%.1f", timer.maxTime - timer.timer) + "s");
		Ld37Game.getGame().getFont().draw(batch, timerLayout, X_OFFSET + WIDTH - timerLayout.width, Y_OFFSET+ HEIGHT );
	}

	@Override
	protected void renTut (SpriteBatch batch) {
		Ld37Game.getGame().getFont().draw(batch, tutLayout, X_OFFSET + WIDTH / 2 - tutLayout.width / 2, Y_OFFSET + HEIGHT / 2 + tutLayout.height / 2);
	}

	@Override
	public void handleInputPrivate (boolean isZPressed, boolean isXPressed, boolean isZJustPressed, boolean isXJustPressed) {
		if (isZJustPressed && grounded){
			grounded = false;
			dudeSpeed += JUMP_SPEED;
			Ld37Game.getGame().playSfx("games/miss.wav");
		}
	}

}