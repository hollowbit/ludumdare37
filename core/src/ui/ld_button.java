package ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class ld_button {
	int x, y, w, h;
	ld_nine test;
	public ld_button(int x, int y, int w, int h){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;		
	}
	
	public boolean checkMouse( Vector2 eh){
		if (eh.x >= x && eh.x <= x+w &&
				eh.y >= y && eh.y <= y+h	)
					return true;
				return false;
	}

}
