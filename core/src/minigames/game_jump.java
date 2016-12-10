package minigames;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class game_jump extends ld_minibase {
	public Texture character = new Texture("games/character.png");
	public Texture floor = new Texture("games/floor.png");
	public Texture saw = new Texture("games/saw.png");
	public game_jump(int x, int y, int w, int h){
		super(x,y,w,h);
	}
	public void render(SpriteBatch batch){
		super.drawBgrnd(batch);
	}
	public void update(){
		
	}
}
