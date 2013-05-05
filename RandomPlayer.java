package Java;

import java.awt.Color;

public class RandomPlayer extends GridworldGamePlayer{
	public RandomPlayer(Color col){
		super(col);
	}
	public int getMove(GridworldGame game){
		return (int)(Math.random()*game.getWidth());
	}
}
