package Java;

import info.gridworld.grid.Location;

public class ConnectFour extends GridworldGame {
	public static void main(String[] args){
		new ConnectFour();
	}
	public ConnectFour(){
		super();
	}
	public ConnectFour(int x,int y){
		super(x,y);
	}
	public boolean isValid(Location loc){
		return super.isValid(loc);
	}
}
