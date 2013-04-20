package Java;

import info.gridworld.actor.*;
import java.awt.Color;

public class DropBug extends Bug {
	public DropBug(Color col){
		super(col);
	}
	public void act(){
		setDirection(180);
		if(canMove())
			move();
		setDirection(0);
	}
}
