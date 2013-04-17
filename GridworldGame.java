package Java;

public abstract class GridworldGame {
	private int[][] gameBoard;
	//create bounded grid
	public GridworldGame(){
		gameBoard=new int[4][4];
	}
	public GridworldGame(int x,int y){
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
	public static boolean isValid(int move){
		return true;
	}
}
