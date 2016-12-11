package ui;

import com.badlogic.gdx.Gdx;

public class ld_button {
	int x, y, w, h;
	public ld_button(int x, int y, int w, int h){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		
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
