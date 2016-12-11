package minigames;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.hollowbit.ld37.Ld37Game;

public abstract class ld_minibase extends ld_entity {
	
	public static final int X_OFFSET = 6;
	public static final int Y_OFFSET = 7;
	public static final int WIDTH = 54;
	public static final int HEIGHT = 54;
	
	public Texture bgrnd = new Texture("games/bgrnd.png");
	public ld_timer tut = new ld_timer(2);
	public ld_timer end = new ld_timer(2);
	public boolean reachedEnd = false;
	public State minist = State.TUT;
	private GameEndHandler[] gameEndHandlers;
	
	GlyphLayout textLayout;
	GlyphLayout gameWinTextLayout;
	GlyphLayout gameLoseTextLayout;
	boolean won = false;
	
	public ld_minibase (GameEndHandler... gameEndHandlers) {
		super(X_OFFSET, Y_OFFSET, WIDTH, HEIGHT);
		this.gameEndHandlers = gameEndHandlers;
		this.textLayout = new GlyphLayout(Ld37Game.getGame().getFont(), "Game Over");
		this.gameWinTextLayout = new GlyphLayout(Ld37Game.getGame().getFont(), "You Win :)");
		this.gameLoseTextLayout = new GlyphLayout(Ld37Game.getGame().getFont(), "You Lose :(");
	}
	
	public abstract void start ();
	
	/**
	 * Do not override this! Override renTut, renPlay and renEnd instead.
	 * @param batch
	 */
	public void render (SpriteBatch batch) {
		drawBgrnd(batch);
		switch(minist){
		case TUT:
			renTut(batch);
			break;
		case PLAY:
			renPlay(batch);
			break;
		case END:
			renEnd(batch);
			break;
		}
		return;
	}
	
	protected void drawBgrnd(SpriteBatch batch){			
		batch.draw(this.bgrnd,(int)this.x,(int)this.y);
	}
	
	/**
	 * Do not override this, override upTut, upPlay and upEnd instead.
	 * @param delta
	 */
	public void update (float delta) {
		switch(minist){
		case TUT:
			upTut(delta);
			break;
		case PLAY:
			upPlay(delta);
			break;
		case END:
			upEnd(delta);
			break;
		}
		return;
	}
	
	
	protected void upTut(float delta) {
		tut.count(delta);
		if (tut.done) minist = State.PLAY;
	}
	
	protected abstract void upPlay(float delta);
	
	protected void upEnd(float delta) {
		end.count(delta);
		if (end.done) {
			for (GameEndHandler handler : gameEndHandlers) {
				if (handler != null)
					handler.handleGameEnd(won);
			}
		}
	}

	protected abstract void renPlay(SpriteBatch batch);
	protected abstract void renTut(SpriteBatch batch);
	
	protected void endGame (boolean won) {
		minist = State.END;
		this.won = won;
	}
	
	protected void renEnd(SpriteBatch batch) {
		Ld37Game.getGame().getFont().draw(batch, textLayout,(float) (w / 2 - textLayout.width / 2) + X_OFFSET,(float)( y + (h / 2 + textLayout.height / 2) + 10));
		if (won)
			Ld37Game.getGame().getFont().draw(batch, gameWinTextLayout,(float) (w / 2 - gameWinTextLayout.width / 2) + X_OFFSET,(float)( y + (h / 2 + gameWinTextLayout.height / 2) - 10));
		else
			Ld37Game.getGame().getFont().draw(batch, gameLoseTextLayout,(float) (w / 2 - gameLoseTextLayout.width / 2) + X_OFFSET,(float)( y + (h / 2 + gameLoseTextLayout.height / 2) - 10));
	}
	
	public abstract void handleInput (boolean isZPressed, boolean isXPressed, boolean isZJustPressed, boolean isXJustPressed);
	
	public interface GameEndHandler {
		public abstract void handleGameEnd (boolean won);
	}
}
