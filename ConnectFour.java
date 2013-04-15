package Java;

public class ConnectFour {
	private int[][] gameBoard;
	public static void main(String[] args){
		
	}
	public void play(int team,ConnectFourPlayer player){
		
	}
	public ConnectFour(){
		new ConnectFour(4,4);
	}
	public ConnectFour(int x,int y){
		gameBoard=new int[x][y];
	}
	public int getWidth(){
		return gameBoard.length;
	}
	public int getHeight(){
		return gameBoard[0].length;
	}
	public int[][] getBoard(){
		int[][] board=new int[getWidth()][getHeight()];
		for(int i=0;i<getWidth();i++)
			for(int j=0;j<getHeight();j++)
				board[i][j]=gameBoard[i][j];
		return board;
	}
	public int getPos(int x,int y){
		return gameBoard[x][y];
	}
}
