package net.hollowbit.ld37.walls;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Align;

import net.hollowbit.ld37.Ld37Game;
import net.hollowbit.ld37.screens.RoomScreen;

public class CreditsWall extends Wall {
	
	GlyphLayout creditsTextLayout;
	
	public CreditsWall(Vector3 dir, RoomScreen roomScreen) {
		super(dir, roomScreen);
		creditsTextLayout = new GlyphLayout(Ld37Game.getGame().getFont(), "Congralutions! You acqire freedums!", Color.WHITE, SIZE, Align.center, true);
	}

	@Override
	public void update (float delta, boolean currentWall) {
		
	}

	@Override
	protected void render (SpriteBatch batch) {
		batch.draw(Ld37Game.getGame().getAssetManager().get("purp_b.png", Texture.class), 0, 0);
		Ld37Game.getGame().getFont().draw(batch, creditsTextLayout, SIZE / 2 - creditsTextLayout.width / 2, SIZE / 2 + creditsTextLayout.height / 2);
	}

	@Override
	public void dispose () {
		
	}

	@Override
	public void handleInput() {
		
	}
	
}
