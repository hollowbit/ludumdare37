package ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*		batch.begin();
		ld_button play = new ld_button(new Texture ("ui_box.png"),0,0,128,128,16);
		ld_button options = new ld_button(new Texture ("ui_box.png"),128,0,128,128,16);
		ld_button exit = new ld_button(new Texture ("ui_box.png"),256,0,128,128,16);
		play.draw(batch);
		options.draw(batch);
		exit.draw(batch);
		batch.end();

 */
public class ld_button {
	private int x, y, w, h, ts;
	private Texture t;
	private NinePatch np;
	public ld_button(Texture t, int x,int y,int w,int h,int tilesize){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.t = t;
		this.ts = tilesize;
		this.np = new NinePatch(t, ts, ts, ts, ts);
	}
	public ld_button(Texture t, int x,int y,int w,int h,int tilesize, SpriteBatch batch){
		x = x;
		y = y;
		w = w;
		h = h;
		t = t;
		ts = tilesize;
		NinePatch np = new NinePatch(t, ts, ts, ts, ts);
		this.draw(batch);
	}
	public void draw(SpriteBatch batch){
		np.draw(batch,x,y,w,h);
	}
	public boolean checkMouse(){
		double mx =Gdx.input.getX();
		double my =Gdx.input.getY();
		if (mx >= x && my >= y && mx < x+w && my > y+h){
			//mouse in button's bounds
			return true;
		}
		return false;
	}
	
}
