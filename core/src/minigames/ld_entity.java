package minigames;

public class ld_entity
{
	public double x, y, w, h;
	public ld_entity(double x, double y, double w, double h)
	{
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	public boolean isIn(ld_entity other)
	{
		if (other.x >= this.x+this.w &&
			other.x + other.w <= this.x &&
			other.y >= this.y+this.h &&
			other.y + other.h <= this.y){
			return true;
		}
		return false;
			
	}
}
