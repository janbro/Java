package Java;

public class RandomPlayer implements ConnectFourPlayer{
	public int getMove(ConnectFour game){
		return (int)(Math.random()*game.getWidth());
	}
}
