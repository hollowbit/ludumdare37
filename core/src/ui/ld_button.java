package ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import net.hollowbit.ld37.Ld37Game;

public class ld_button {
	
	private static NinePatch button9Patch;
	private static final int BUTTON_PATCH_OFFSET = 4;

	GlyphLayout textLayout;
	float x, y, w, h;
	
	public ld_button (String text, float x, float y, float padding) {
		this.textLayout = new GlyphLayout(Ld37Game.getGame().getFont(), text);
		this.x = x;
		this.y = y;
		
		this.w = textLayout.width + padding * 2;
		this.h = textLayout.height + padding * 2;
		
		if (button9Patch == null)
			button9Patch = new NinePatch(Ld37Game.getGame().getAssetManager().get("ui_box.png", Texture.class), BUTTON_PATCH_OFFSET, BUTTON_PATCH_OFFSET, BUTTON_PATCH_OFFSET, BUTTON_PATCH_OFFSET);
	}
	
	public boolean checkMouse (Vector3 mouse) {
		if (mouse.x >= getDrawX() && mouse.x <= getDrawX() + w && mouse.y >= y && mouse.y <= y + h)
			return true;
		else
			return false;
	}
	
	public void render (SpriteBatch batch) {
		button9Patch.draw(batch, getDrawX(), y, w, h);
		Ld37Game.getGame().getFont().draw(batch, textLayout, getDrawX() + (w / 2 - textLayout.width / 2), y + (h / 2 + textLayout.height / 2));
	}
	
	private float getDrawX () {
		return x - w / 2;
	}

}
