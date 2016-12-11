package minigames.games;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

import minigames.ld_minibase;
import minigames.ld_timer;
import net.hollowbit.ld37.Ld37Game;

public class NoseGame extends ld_minibase {

	private static final int FINGER_OFFSET = 9;
	private static final int NOSTRIL_1_OFFSET = 3;
	private static final int NOSTRIL_2_OFFSET = 14;
	private static final int NOSTRIL_WIDTH = 6;
	
	Texture nose;
	Texture finger;
	ld_timer timer;
	
	float fingerX = X_OFFSET, fingerY = Y_OFFSET + 2;
	float fingerSpeed = 60;
	GlyphLayout tutLayout;
	
	boolean stopFinger = false;
	
	public NoseGame (GameEndHandler... gameEndHandlers) {
		super(gameEndHandlers);
		timer = new ld_timer(5);
		
		//Get textures from asset manager
		nose = Ld37Game.getGame().getAssetManager().get("games/nose.png", Texture.class);
		finger = Ld37Game.getGame().getAssetManager().get("games/finger.png", Texture.class);
		
		tutLayout = new GlyphLayout(Ld37Game.getGame().getFont(), "Press Z to pick nose!", Color.ORANGE, WIDTH, Align.left, true);
	}
	
	@Override
	public void start () {
		//Code to run at game start
	}

	@Override
	protected void upPlay (float delta) {
		if (!stopFinger) {
			fingerX += fingerSpeed * delta;
			
			if (fingerX + finger.getWidth() > X_OFFSET + WIDTH - 2) {
				fingerX = X_OFFSET + WIDTH - finger.getWidth() - 2;
				fingerSpeed = -fingerSpeed;
			}
			
			if (fingerX < X_OFFSET) {
				fingerX = X_OFFSET;
				fingerSpeed = -fingerSpeed;
			}
		} else {
			fingerSpeed = Math.abs(fingerSpeed);
			fingerY += fingerSpeed * delta;
			if (fingerY > Y_OFFSET + HEIGHT / 2 - nose.getHeight() / 2 + 4) {
				if ((fingerX + FINGER_OFFSET >= X_OFFSET + WIDTH / 2 - nose.getWidth() / 2 + NOSTRIL_1_OFFSET && fingerX + FINGER_OFFSET <= X_OFFSET + WIDTH / 2 - nose.getWidth() / 2 + NOSTRIL_1_OFFSET + NOSTRIL_WIDTH) || (fingerX + FINGER_OFFSET >= X_OFFSET + WIDTH / 2 - nose.getWidth() / 2 + NOSTRIL_2_OFFSET && fingerX + FINGER_OFFSET <= X_OFFSET + WIDTH / 2 - nose.getWidth() / 2 + NOSTRIL_2_OFFSET + NOSTRIL_WIDTH))
					endGame(true);
				else
					endGame(false);
			}
		}
		
		timer.count(delta);
		if (timer.done) {
			endGame(false);
		}
	}

	@Override
	protected void renPlay (SpriteBatch batch) {
		batch.draw(nose, X_OFFSET + WIDTH / 2 - nose.getWidth() / 2, Y_OFFSET + HEIGHT / 2 - nose.getHeight() / 2 + 8);
		batch.draw(finger, fingerX, fingerY);
		
		GlyphLayout timerLayout = new GlyphLayout(Ld37Game.getGame().getFont(), String.format("%.1f", timer.maxTime - timer.timer) + "s");
		Ld37Game.getGame().getFont().draw(batch, timerLayout, X_OFFSET + WIDTH / 2 - timerLayout.width / 2, Y_OFFSET);
	}

	@Override
	protected void renTut (SpriteBatch batch) {
		Ld37Game.getGame().getFont().draw(batch, tutLayout, X_OFFSET + WIDTH / 2 - tutLayout.width / 2, Y_OFFSET + HEIGHT / 2 + tutLayout.height / 2);
	}

	@Override
	public void handleInputPrivate (boolean isZPressed, boolean isXPressed, boolean isZJustPressed, boolean isXJustPressed) {
		if (isZJustPressed)
			stopFinger = true;
	}

}