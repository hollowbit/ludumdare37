package minigames;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ld_manager {
	private int score = 0;
	private int WALL_H  = 1024, WALL_W = 1024;
	private int TEX_R = 64;
	private int R = WALL_H/TEX_R;
	private ld_minibase games[] = new ld_minibase[99];
	private int curIndex = 0;
	private boolean playing = false;
	public ld_manager(){
		populate();
		start();
	}
	
	
	public void transition(){
		curIndex++;
		start();
	}
	
	public void start(){
		playing = true;
		games[curIndex].start();
	}
	public void stop(){

	}
	public void update(float delta){
		if (playing){
			games[curIndex].update(delta);
			if (!games[curIndex].update) transition();

		}
	}
	public void render(SpriteBatch batch){
		if (playing){
			games[curIndex].render(batch);
		}
	}
	
	public void populate(){
		for (int i = 0; i < 99; i++)
			games[i] = getRandomGame();
			
	}
	
	
	
	public ld_minibase getRandomGame(){
		Random randomGenerator = new Random();
		int r = randomGenerator.nextInt(2);
		return chooseOne(r);
		
	}
	
	
	public ld_minibase chooseOne(int choice){
		switch (choice){
		default:
			return (new game_jump(WALL_H,R*6,R*7,R*52,R*52)); // Should be victory screen
		case 0:
			return (new game_nose(WALL_H,R*6,R*7,R*52,R*52));
		case 1:
			return (new game_jump(WALL_H,R*6,R*7,R*52,R*52));
		}
	}
}
