package Java;

public class RandomPlayer implements ConnectFourPlayer{
	public int[] getMove(ConnectFour game){
		int x=(int)(Math.random()*(game.getLength()-1));
		int y=(int)(Math.random()*(game.getWidth()-1));
		return new int[]{x,y};
	}
}
