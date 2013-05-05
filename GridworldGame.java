package Java;

import java.awt.Color;
import info.gridworld.actor.*;
import info.gridworld.grid.*;

public abstract class GridworldGame {
	private BoundedGrid<Actor> gameBoard;
	private ActorWorld world;
	public GridworldGame(){
		this(4,4);
	}
	public GridworldGame(int x,int y){
		gameBoard=new BoundedGrid<Actor>(x,y);
		world=new ActorWorld(gameBoard);
		world.show();
//		dropBug(1,Color.RED);
//		System.out.println("done");
	}
	public boolean move(Location loc,Color col){
		if(isValid(loc)){
			dropBug(loc,col);
			return true;
		}
		return false;
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
	public void dropBug(Location loc,Color col){
		DropBug db=new DropBug(col);
		world.add(loc,db);
		while(db.inLoop()){}
	}
}
