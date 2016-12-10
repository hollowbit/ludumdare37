package minigames;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class game_jump extends ld_minibase {
	public Texture character = new Texture("games/character.png");
	public Texture floor = new Texture("games/floor.png");
	public Texture saw = new Texture("games/saw.png");
	public double s1s; //saw1 speed vector
	public double s2s; //saw1 speed vector
	public ld_entity ent_char, ent_floor, ent_saw1, ent_saw2; 
	public boolean grounded = false;
	public double grav = 9.8;
	public double vs = 0;
	public int timer = 0;
	public int maxTime = 5000;
	
	public game_jump(int x, int y, int w, int h){
		super(x,y,w,h);
		ent_char = new ld_entity(this.x+this.w/2,this.y+12,7,7);
		ent_floor = new ld_entity(this.x,this.y,this.w,11);
		ent_saw1 = new ld_entity(this.x+3,this.y+12,11,6);
		s1s = 1;
		ent_saw2 = new ld_entity(this.x+this.w-14,this.y+12,11,6);
		s2s = -1;
	}
	
	@Override
	public void render(SpriteBatch batch){
		super.drawBgrnd(batch);
		//batch.draw(character, (float)ent_char.x, (float)ent_char.y, (float)this.w,(float) this.y);
		batch.draw(floor, (float)ent_floor.x, (float)ent_floor.y, (float)ent_floor.w, (float)ent_floor.h);
		//batch.draw(saw, (float)ent_saw1.x, (float)ent_saw1.y, (float)this.w, (float)this.y);
		//batch.draw(saw, (float)ent_saw2.x, (float)ent_saw2.y, (float)this.w, (float)this.y);
	}
	
	@Override
	public void update(){
		if (vs < 12) vs+=grav;
		if (ent_char.isIn(ent_floor)){
			ent_char.y=ent_floor.y;
			vs=0;
			grounded = true;
		}
		else{
			grounded = false;
			ent_char.y += vs;
		}
		ent_saw1.x+=this.s1s;
		ent_saw2.x+=this.s2s;
		
		if (ent_saw1.x <= 1 ||
			ent_saw1.x + ent_saw1.w >= this.w)
			s1s=-s1s;
		
		if (ent_saw2.x <= 1 ||
			ent_saw2.x + ent_saw2.w >= this.w)
			s2s=-s2s;
		
		if (ent_char.isIn(ent_saw1) || ent_char.isIn(ent_saw2)){
			super.stop(timer);
		}

	}
	public void addTimer(){
		if (timer < maxTime){
			timer++;
		} else {
			super.stop(timer);
		}
	}
	
	@Override
	public void Zpressed(){
		if (grounded)
			vs-=12;
	}
	
}
