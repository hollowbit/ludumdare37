package minigames;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import minigames.games.JumpGame;
import minigames.games.MatchGame;
import minigames.games.MathGame;
import minigames.games.NoseGame;
import net.hollowbit.ld37.screens.RoomScreen;

public class ld_manager implements ld_minibase.GameEndHandler {
	
	private ld_minibase currentGame;
	private RoomScreen roomScreen;
	
	public ld_manager (RoomScreen roomScreen) {
		this.roomScreen = roomScreen;
		getNextGame();
	}
	
	private void getNextGame () {
		currentGame = getRandomGame();
		currentGame.start();
	}
	
	public void update (float delta, boolean paused) {
		if (!paused)
			currentGame.update(delta);
	}
	
	public void render (SpriteBatch batch) {
		currentGame.render(batch);
	}
	
	public void handleInput () {
		currentGame.handleInput(Gdx.input.isKeyPressed(Keys.Z), Gdx.input.isKeyPressed(Keys.X), Gdx.input.isKeyJustPressed(Keys.Z), Gdx.input.isKeyJustPressed(Keys.X));
	}
	
	private ld_minibase getRandomGame(){
		Random randomGenerator = new Random();
		int r = randomGenerator.nextInt(3);
		return chooseOne(r);
		
	}
	
	
	public ld_minibase chooseOne(int choice){
		return (new JumpGame(this, roomScreen));
		/*switch (choice){
		default:
			return (new MatchGame(this, roomScreen));
		case 0:
			return (new MathGame(this, roomScreen));
		case 1:
			return (new NoseGame(this, roomScreen));
		}*/
	}

	@Override
	public void handleGameEnd (boolean won) {
		getNextGame();
	}
}
