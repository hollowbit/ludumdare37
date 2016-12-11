package minigames;

public class ld_timer {
	public float timer = 0;
	public float maxTime;
	public boolean done = false;
	public ld_timer(float maxTime){
		this.maxTime = maxTime;
	}
	public void count(float delta) {
		if (timer < maxTime){
			timer+=delta;
			
		} else {
			done = true;
		}
	}
}
