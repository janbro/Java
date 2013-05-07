package Java.GridworldGame;

import info.gridworld.grid.Location;

public class RandomPlayer extends GridworldGamePlayer{
	public Location getMove(GridworldGame game,int numTried){
		return new Location(0,(int)(Math.random()*game.getWidth()));
	}
}
