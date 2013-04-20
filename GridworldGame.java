package Java;

import info.gridworld.actor.Actor;
import info.gridworld.grid.*;

public abstract class GridworldGame {
	private BoundedGrid<Actor> gameBoard;
	public GridworldGame(){
		gameBoard=new BoundedGrid<Actor>(4,4);
	}
	public GridworldGame(int x,int y){
		gameBoard=new BoundedGrid<Actor>(x,y);
	}
	public int getWidth(){
		return gameBoard.getNumRows();
	}
	public int getHeight(){
		return gameBoard.getNumCols();
	}
	public Actor getPos(Location loc){
		return gameBoard.get(loc);
	}
	public boolean isValid(Location loc){
		return gameBoard.isValid(loc);
	}
}
