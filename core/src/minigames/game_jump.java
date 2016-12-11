package minigames;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.hollowbit.ld37.Ld37Game;

public class game_jump extends ld_minibase {
	public int TEX_R = 64;
	public int R;
	public Texture character = new Texture("games/character.png");
	public Texture floor = new Texture("games/floor.png");
	public Texture saw = new Texture("games/saw.png");
	public double s1s; //saw1 speed vector
	public double s2s; //saw1 speed vector
	public ld_entity ent_char, ent_floor, ent_saw1, ent_saw2; 
	public boolean grounded = false;
	public double grav = 0.0098;
	public double vs = 100;
	public int timer = 0;
	public int maxTime = 500;
	
	public game_jump(int size,int x, int y, int w, int h){
		super(x,y,w,h);
		this.R = size/TEX_R;
		ent_char = new ld_entity(this.x+this.w/2,this.y+12*R,7*R,7*R);
		ent_floor = new ld_entity(this.x,this.y,this.w,11*R);
		ent_saw1 = new ld_entity(this.x+3*R,this.y+11*R,11*R,6*R);
		s1s = 40;
		ent_saw2 = new ld_entity(this.x+(52-14)*R,this.y+11*R,11*R,6*R);
		s2s = -40;
	}
	
	@Override
	public void render(SpriteBatch batch){
		super.drawBgrnd(batch);
		batch.draw(character, (float)ent_char.x, (float)ent_char.y, (float)ent_char.w,(float) ent_char.h);
		batch.draw(floor, (float)ent_floor.x, (float)ent_floor.y, (float)ent_floor.w, (float)ent_floor.h);
		batch.draw(saw, (float)ent_saw1.x, (float)ent_saw1.y, (float)ent_saw1.w, (float)ent_saw1.h);
		batch.draw(saw, (float)ent_saw2.x, (float)ent_saw2.y, (float)ent_saw2.w, (float)ent_saw2.h);
	}
	@Override
	public void upEnd(float delta){
		
	}
	@Override
	public void upPlay(float delta){
		addTimer(delta);
		if (this.vs+this.grav*delta < 0.50)
		      this.vs+=this.grav*delta;
		this.y+=this.vs*delta;
		if (ent_char.isIn(ent_floor)){
			ent_char.y=ent_floor.h+ent_char.h+1;
			this.grounded = true;
	        this.vs = 0;
		}

		ent_saw1.x+=this.s1s*delta;
		ent_saw2.x+=this.s2s*delta;
		
		if (ent_saw1.x <= 6*R ||
			ent_saw1.x + ent_saw1.w >= 58*R){
			if (ent_saw1.x <= 6*R)
				ent_saw1.x = 6*R+R;
			else
				ent_saw1.x = 58*R - ent_saw1.w - R;
			s1s=-s1s;
		}
		if (ent_saw2.x <= 6*R ||
			ent_saw2.x + ent_saw2.w >= 58*R){
			if (ent_saw2.x <= 6*R)
				ent_saw2.x = 6*R+R;
			else
				ent_saw2.x = 58*R - ent_saw2.w - R;
			s2s=-s2s;
		}
		if (ent_char.isIn(ent_saw1) || ent_char.isIn(ent_saw2)){
			super.stop(timer);
		}

	}
	@Override
	public void upTut(float delta){
		
	}
	public void addTimer(float delta){
		if (timer < maxTime){
			timer+=delta*100;
			
		} else {
			super.minist = State.END;
		}
	}
	
	@Override
	public void Zpressed(){
		if (grounded){
			vs=-12;
			grounded = false;
		}
	}
	
}
