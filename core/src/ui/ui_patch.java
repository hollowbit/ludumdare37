package ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;

public class ui_patch {
	
	public NinePatch patch;
	public ui_patch(){
		patch = new NinePatch(new Texture(Gdx.files.internal("assets/ui_box.png")), 24, 24, 24, 24);
	}
	
	public NinePatch getPatch(){
		return patch;
	}
}
