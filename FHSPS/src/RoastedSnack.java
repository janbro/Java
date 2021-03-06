import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class RoastedSnack {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pr = new PrintWriter(System.out);
		String gridSize[];
		int casenum=1;
		while(!(gridSize = br.readLine().split(" "))[0].equals("0")&&!gridSize[1].equals("0")){
			String grid[][] = new String[Integer.parseInt(gridSize[0])][Integer.parseInt(gridSize[1])];
			for(int i=0;i<grid.length;i++){
				grid[i] = br.readLine().split("");
			}
			int fire=0;		
			while(hasSheep(grid)){
				int greatestcolcount=0;
				int greatestrow = 0;
				int greatestrowcount=0;
				int columncount=0;
				int greatestcol = 0;
				for(int i=0;i<grid.length;i++){
					int rowCount=0;
					for(int j=0;j<grid[i].length;j++){
						if(grid[i][j].equals("S")){
							rowCount++;
						}
					}if(rowCount>greatestrowcount){
						greatestrowcount = rowCount;
						greatestrow = i;
					}
					rowCount = 0;
				}for(int j=0;j<grid[0].length;j++){
					for(int i=0;i<grid.length;i++){
						if(grid[i][j].equals("S")){
							columncount++;
						}
					}if(columncount>greatestcolcount){
						greatestcolcount = columncount;
						greatestcol = j;
					}
					columncount=0;
				}
				if(colSheep(grid)>rowSheep(grid))
					for(int i=0;i<grid[0].length;i++)
						grid[greatestrow][i] = ".";
				else
					for(int i=0;i<grid.length;i++)
						grid[i][greatestcol] = ".";
				fire++;
			}
			pr.println("Case "+casenum+": The dragons must breathe fire "+fire+" time(s).");
			casenum++;
		}pr.flush();
	}
	
	static boolean hasSheep(String gr[][]){
		for(String row[]:gr)
			for(String str:row)
				if(str.equals("S"))
					return true;
		return false;
	}
	static int rowSheep(String gr[][]){
		int tot=0;
		for(String row[]:gr)
			for(String str:row)
				if(str.equals("S")){
					tot++;
					break;
				}

		return tot;
	}
	
	static int colSheep(String gr[][]){
		int tot=0;
		for(int i=0;i<gr[0].length;i++){
			for(int j=0;j<gr.length;j++){
				if(gr[j][i].equals("S")){
					tot++;
					break;
				}
			}
		}
		return tot;
	}

}
