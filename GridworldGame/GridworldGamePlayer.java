package Java.GridworldGame;

import info.gridworld.grid.Location;

public abstract class GridworldGamePlayer {
	public abstract Location getMove(GridworldGame game,int numTried);
}
