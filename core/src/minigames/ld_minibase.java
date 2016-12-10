package minigames;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class ld_minibase extends ld_entity
{
	Texture bgrnd = new Texture("games/bgrnd.png");
	public ld_minibase(int x, int y, int w, int h)
	{
		super(x,y,w,h);
	}
	
	boolean update = false;
	
	public void drawBgrnd(SpriteBatch batch){
		batch.draw(this.bgrnd,(int)this.x,(int)this.y);
	}
	
	public void start(){
		this.update = true;
	}
	
	public void stop(){
		this.update = false;
	}
}
