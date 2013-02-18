package Java;

public class ConnectFour {
	private int[][][] gameBoard;
	public static void main(String[] args){
		
	}
	public ConnectFour(){
		new ConnectFour(4,4,4);
	}
	public ConnectFour(int x,int y,int z){
		gameBoard=new int[x][y][z];
	}
	public int[][][] getBoard(){
		int[][][] board=new int[gameBoard.length][gameBoard[0].length][gameBoard[0][0].length];
		for(int i=0;i<board.length;i++)
			for(int j=0;j<board.length;j++)
				for(int k=0;k<board.length;k++)
					board[i][j][k]=gameBoard[i][j][k];
		return board;
	}
	public int getPos(int x,int y,int z){
		return gameBoard[x][y][z];
	}
}
