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
	public boolean isIn(ld_entity rect){
		if (rect.x <= this.x + this.w && rect.x + rect.w >= this.x &&
				rect.y <= this.y + this.h && rect.y + rect.h >= this.y	)
				return true;
			return false;
		}
}
