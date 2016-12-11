package ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ld_button {
	int x, y, w, h;
	ld_nine test;
	public ld_button(int x, int y, int w, int h){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		test = new ld_nine(new Texture("ui_box.png"),x,y,w,h,4);
		
	}
	
	public boolean checkMouse(SpriteBatch batch){
		test.draw(batch);
		double mx =Gdx.input.getX();
		double my =Gdx.input.getY();
		if (mx >= x && my >= y && mx < x+w && my > y+h){
			//mouse in button's bounds
			return true;
		}
		return false;
	}

}
