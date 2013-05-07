package Java.GridworldGame;

import java.awt.Color;
import java.util.List;
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
	}
	public void play(List<GridworldGamePlayer> players){
		int winningMove=-1;
		for(int i=0;winningMove<0;i++){
			if(players.size()<=i)
				i=0;
			for(int numTried=0;!move(players.get(i).getMove(this,numTried),getColor(i));numTried++){}
			winningMove=winningMove(i);
		}
		win(winningMove);
	}
	public abstract Color getColor(int i);
	public abstract int winningMove(int i);
	public abstract void win(int winningMove);
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
		if(gameBoard.get(loc)!=null)
			return false;
		return gameBoard.isValid(loc);
	}
	public void dropBug(Location loc,Color col){
		DropBug db=new DropBug(col);
		world.add(loc,db);
		while(db.inLoop()){}
	}
}
