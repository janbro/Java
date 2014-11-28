package AICE;

import java.io.PrintWriter;
import java.util.Scanner;

public class Squares {

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		String sinput = sc.nextLine();
		for(String in:sinput.split(" ")){
			int input = Integer.parseInt(in);
			String[][] board = new String[input][input];
			for(int i=0;i<board.length;i++){
				for(int j=0;j<board[i].length;j++){
					board[i][j] = " ";
				}
			}
			int start = 0;
			while(input>0){
				for(int i=start;i<board.length-start;i++){
					board[i][start] = "*";
					board[i][board.length-1-start] = "*";
					board[start][i] = "*";
					board[board.length-1-start][i] = "*";
				}input-=4;
				start+=2;
			}
			for(int i=0;i<board.length;i++){
				for(int j=0;j<board[i].length;j++){
					pw.print(board[i][j]);
				}pw.println();
			}
			pw.println();
			pw.flush();
		}sc.close();
	}
}