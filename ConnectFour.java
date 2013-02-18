package Java;

public class ConnectFour {
	private int[][][] gameBoard;
	public static void main(String[] args){
		
	}
	public void play(int team,ConnectFourPlayer player){
		
	}
	public ConnectFour(){
		new ConnectFour(4,4,4);
	}
	public ConnectFour(int x,int y,int z){
		gameBoard=new int[x][y][z];
	}
	public int getLength(){
		return gameBoard.length;
	}
	public int getWidth(){
		return gameBoard[0].length;
	}
	public int getHeight(){
		return gameBoard[0][0].length;
	}
	public int[][][] getBoard(){
		int[][][] board=new int[getLength()][getWidth()][getHeight()];
		for(int i=0;i<getLength();i++)
			for(int j=0;j<getWidth();j++)
				for(int k=0;k<getHeight();k++)
					board[i][j][k]=gameBoard[i][j][k];
		return board;
	}
	public int getPos(int x,int y,int z){
		return gameBoard[x][y][z];
	}
}
