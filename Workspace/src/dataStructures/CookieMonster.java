package dataStructures;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Stack;

import javax.swing.JEditorPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.EditorKit;

public class CookieMonster {
	public static void main(String[] args) throws IOException, BadLocationException{
		BufferedReader br = new BufferedReader(new FileReader("cookie.txt"));
		String documentText=br.readLine();
		String input;
		while(( input=br.readLine())!= null)
			documentText+=input;
		//System.out.println(documentText);

		int[][] newBoard = {{ 1, 3, 0, 5,-1, 7,-1,-1, 0, 4, 2, 1},
							{-1, 3, 2, 1,-1, 4,-1, 5, 3,-1, 1, 0},
							{ 5, 4, 8,-1, 3, 2, 2,-1, 4,-1, 0, 0},
							{ 2, 1, 0, 4, 1,-1, 8, 0, 2,-1, 2, 5},
							{ 1, 4, 0, 1,-1, 0, 3, 2, 2, 4, 1, 4},
							{ 0, 1, 4, 1, 1, 6, 1, 4, 5, 2, 1, 0},
							{ 3, 2, 5, 2, 0, 7,-1, 2, 1, 0,-1, 3},
							{ 0,-1, 4,-1,-1, 3, 5, 1, 4, 2, 1, 2},
							{ 5, 4, 8,-1, 3, 2, 2,-1, 4,-1, 0, 0},
							{ 2, 1, 0, 4, 1,-1, 8, 0, 2,-1, 2, 5},
							{ 1, 3, 0, 5,-1,-1, 7,-1, 0, 4, 2, 1},
							{ 0, 0, 3, 1, 5, 2, 1, 5, 4, 1, 3, 3}};
		System.out.println("done~!"+checkBoard(newBoard));
	}
	
	static int checkBoard(int[][] board){
		Stack<Integer> myStack = new Stack<Integer>();
		int total = board[0][0];
		int[] pos = {0,0};
		int intPos = 000000;
		while(pos[0]!=board.length&&pos[1]!=board[0].length)
		{
			if(!myStack.isEmpty())
				pos = intToPos((int)myStack.peek());
			System.out.println("{"+pos[1]+","+pos[0]+"} ::"+board[pos[0]][pos[1]]);
			if((pos[0]<11&&board[pos[0]+1][pos[1]]==-1)&&(pos[1]<11&&board[pos[0]][pos[1]+1]==-1)){
				if(myStack.isEmpty())
					return -1;
				board[pos[0]][pos[1]] = -1;
				myStack.pop();
				System.out.println("Back Up!");
			}else{
			if(pos[0]<11&&(board[pos[0]+1][pos[1]]>board[pos[0]][pos[1]+1]))
				pos[0]++;
			else
				pos[1]++;
			myStack.add(posToInt(pos));
			}
		}myStack.pop();
		while(!myStack.isEmpty()) //Add cookies
			total+=board[intToPos(myStack.peek())[0]][intToPos(myStack.pop())[1]];
		return total;
	}
	static int[] intToPos(int intPos){
		return new int[]{intPos/1000,intPos%1000};
	}
	
	static int posToInt(int[] pos){
		return pos[0]*1000+pos[1];
	}
}
