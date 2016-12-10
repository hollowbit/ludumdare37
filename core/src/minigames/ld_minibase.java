package minigames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class ld_minibase extends ld_entity
{
	public int WALL_H  = 1024, WALL_W = 1024;
	public int TEX_R = 64;
	public int R = WALL_H/TEX_R;
	public Texture bgrnd = new Texture("games/bgrnd.png");
	public ld_minibase(int x, int y, int w, int h)
	{
		super(x,y,w,h);
	}
	
	boolean update = false;
	
	public void drawBgrnd(SpriteBatch batch){			
		batch.draw(this.bgrnd,(int)this.x,(int)this.y,(int)this.w,(int)this.h);
	}
	public void render(SpriteBatch batch){		//To be overwritten
		return;
	}
	public void update(float delta){					//To be overwritten
		return;
	}
	
	public void start(){
		this.update = true;
	}
	public void readKeys(){		//Do not overwrite this
		
		
		 if(Gdx.input.isKeyPressed(Input.Keys.Z))
			 Zpressed();
		 else
			 ZnotPressed();
		 
		 
	     if(Gdx.input.isKeyPressed(Input.Keys.X))
	    	 Xpressed();
		 else
			 XnotPressed();

	        
	}
	public void Zpressed(){			//Overwrite these
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
	public void stop(int timeSurvived){
		this.update = false;
	}
	public void stop(boolean success){
		this.update = false;
	}
}
