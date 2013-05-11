package Java.GridworldGame;

import java.awt.Color;
import java.util.ArrayList;
import info.gridworld.grid.Location;

public class ConnectFour extends GridworldGame {
	public static void main(String[] args){
		ConnectFour game=new ConnectFour();
		game.play(new RandomPlayer(),new RandomPlayer());
	}
	public ConnectFour(){
		super();
	}
	public ConnectFour(int x,int y){
		super(x,y);
	}
	public void play(GridworldGamePlayer p1,GridworldGamePlayer p2){
		ArrayList<GridworldGamePlayer> players=new ArrayList<GridworldGamePlayer>();
		players.add(p1);
		players.add(p2);
		super.play(players);
	}
	public int win(int winningMove){
		return 0;
	}
	public boolean isValid(Location loc){
		return super.isValid(loc);
	}
	public Color getColor(int i){
		if(i==0)
			return Color.RED;
		return Color.BLUE;
	}
	public int winningMove(int i){
		return -1;
	}
}
