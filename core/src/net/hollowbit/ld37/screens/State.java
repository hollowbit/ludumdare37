package net.hollowbit.ld37.screens;

public enum State {
    MAIN(0),
    OPTNS(4),
    GAME(1),
    CREDITS(5);
	
	private int wallNo;
	
	private State (int wallNo) {
		this.wallNo = wallNo;
	}
	
	public int getWallNo () {
		return wallNo;
	}
}