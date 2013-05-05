package Java.GridworldGame;

import java.awt.Color;

public abstract class GridworldGamePlayer {
	public final Color col;
	public GridworldGamePlayer(Color col){
		this.col=col;
	}
	public int getMove(GridworldGame game){
		return 0;
	}
}
