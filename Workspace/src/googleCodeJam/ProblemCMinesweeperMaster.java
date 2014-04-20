package googleCodeJam;

import java.io.PrintWriter;
import java.util.Scanner;

public class ProblemCMinesweeperMaster {


	public static void main(String[] args){
		PrintWriter pw = new PrintWriter(System.out);
		Scanner buffer = new Scanner(System.in);//new BufferedReader(new InputStreamReader(System.in));
		int inputTimes = Integer.parseInt(buffer.nextLine());
		
		for(int i=0;i<inputTimes;i++){
			String[] input = buffer.nextLine().split(" ");
			int[] in = new int[input.length];
			for(int j=0;j<input.length;j++){
				in[j] = Integer.parseInt(input[j]);
			}
			int tiles = in[0]*in[1];
			int mines = in[2];
			pw.println("Case #"+(i+1)+": ");
			if(tiles-mines<4&&in[0]>1&&in[1]>1&&tiles-mines!=1&&mines>0&&!(in[0]==1||in[1]==1))
				pw.println("Impossible");
			else if((in[0]==1||in[1]==1)&&tiles-mines<2&&mines>0&&tiles-mines!=1)
				pw.println("Impossible");
			else{
				int[][] board=new int[in[0]][in[1]];
				int place = (int) (Math.sqrt(mines)+in[1]);
				for(int t=0;t<board.length;t++){
					for(int y=0;y<board[t].length;y++){
						if(y<place-t&&mines>0)
						{
							pw.print("*");
							mines--;
						}
						else if(tiles>1)
							pw.print(".");
						else
							pw.print("c");
						tiles--;
					}pw.println();
				}
			}
		}pw.flush();
	}
}
