package Java.GridworldGame;

import info.gridworld.actor.*;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;

public class DropBug extends Bug {
	private boolean inLoop;
	public DropBug(Color col){
		super(col);
		inLoop=true;
	}
	public void act(){
		setDirection(180);
		if(canMove())
			move();
		else
			inLoop=false;
		setDirection(0);
	}
	public void move(){
		Grid<Actor> gr=getGrid();
		if(gr==null)
			return;
		Location loc=getLocation();
		Location next=loc.getAdjacentLocation(getDirection());
		if(gr.isValid(next))
			moveTo(next);
		else
			removeSelfFromGrid();
	}
	public boolean inLoop(){
		return inLoop;
	}
}
