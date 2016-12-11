package minigames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.hollowbit.ld37.Ld37Game;

public abstract class ld_minibase extends ld_entity
{
	
	public Texture bgrnd = new Texture("games/bgrnd.png");
	public ld_minibase(int x, int y, int w, int h)
	{
		super(x,y,w,h);
	}
	
	boolean update = false;
	public State minist = State.TUT;
	
	public void render(SpriteBatch batch){		//To be overriden
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
	public void drawBgrnd(SpriteBatch batch){			
		batch.draw(this.bgrnd,(int)this.x,(int)this.y);
	}
	public void update(float delta){					//Do not override this, overwrite tutorialUpdate, playUpdate and endUpdate.
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
	
	
	public void upTut(float delta) {
		// override this
		
	}
	public void upPlay(float delta) {
		// override this
		
	}
	public void upEnd(float delta) {
		// override this
		
	}
	public void renTut(SpriteBatch batch) {
		// override this
		
	}
	public void renPlay(SpriteBatch batch) {
		// override this
		
	}
	public void renEnd(SpriteBatch batch) {
		// override this
		
	}
	public void start(){
		this.update = true;
	}
	public void readKeys(){		//Do not override this
		 if(Gdx.input.isKeyPressed(Input.Keys.Z))
			 Zpressed();
		 else
			 ZnotPressed();
	     if(Gdx.input.isKeyPressed(Input.Keys.X))
	    	 Xpressed();
		 else
			 XnotPressed();      
	}
	public void Zpressed(){			//override these
		return;
	}
	public void ZnotPressed(){
		return;
	}
	public void Xpressed(){
		return;
	}
	public void XnotPressed(){
		return;
	}
	public void stop(float timer){
		this.update = false;
	}
	public void stop(boolean success){
		this.update = false;
	}
}
