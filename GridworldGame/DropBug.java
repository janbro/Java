package Java.GridworldGame;

import info.gridworld.actor.*;
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
	public boolean inLoop(){
		return inLoop;
	}
}
