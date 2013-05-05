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
		dropBug(1,Color.RED);
		System.out.println("done");
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
	public void dropBug(int col,Color color){
		DropBug db=new DropBug(Color.RED);
		world.add(db);
		while(db.inLoop()){}
	}
}
