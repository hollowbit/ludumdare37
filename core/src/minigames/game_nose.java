package minigames;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class game_nose extends ld_minibase {
	private int size;
	public Texture nose = new Texture("games/nose.png");
	public ld_entity ent_nose;
	public float timer;
	public float maxTime = 900;
	public game_nose(int size, int x, int y, int w, int h) {
		super(x, y, w, h);
		this.size = size;
	}
	@Override
	public void upTut(float delta) {
		super.upTut(delta);
		// override this
		
	}
	@Override
	public void upPlay(float delta) {
		// override this
		
	}
	@Override
	public void upEnd(float delta) {
		// override this
		
	}
	@Override
	public void renTut(SpriteBatch batch) {
		// override this
		
	}
	@Override
	public void renPlay(SpriteBatch batch) {
		// override this
		
	}
	@Override
	public void renEnd(SpriteBatch batch) {
		// override this
		
	}
	public void addTimer(float delta){
		if (timer < maxTime){
			timer+=delta*100;
			
		} else {
			super.stop(timer);
		}
	}
}
