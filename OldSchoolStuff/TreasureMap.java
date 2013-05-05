package Java.OldSchoolStuff;

public class TreasureMap {
	private boolean[][] map;
	public TreasureMap(int x,int y){
		map=new boolean[x][y];
		for(int i=(int)(Math.sqrt(x*y)+0.5);i>0;){
			int rx=(int)(Math.random()*x);
			int ry=(int)(Math.random()*y);
			if(!map[rx][ry]){
				map[rx][ry]=true;
				i--;
			}
		}	
	}
	public boolean hasTreasure(int row,int col){
		if(row<map.length || col<map[0].length)
			throw new IllegalArgumentException();
		return map[row][col];
	}
	public int numAdjacent(int row,int col){
		int sum=0;
		for(int i=-1;i<=1;i++)
			for(int j=-1;j<=1;j++)
				if(hasTreasure(row+i,col+j) && !(i==0 && j==0))
					sum++;
		return sum;
	}
	public String toString(){
		String str="";
		for(int i=0;i<map.length;i++){
			for(int j=0;j<map[0].length;j++)
				str+=" "+map[i][j];
			str+="\n";
		}
		return str;
	}
	public int[][] computeCounts(){
		int[][] counts=new int[map.length][map[0].length];
		for(int i=0;i<map.length;i++)
			for(int j=0;j<map[0].length;j++){
				if(hasTreasure(i,j))
					counts[i][j]=-1;
				else
					counts[i][j]=numAdjacent(i,j);
			}
		return counts;
	}
}
