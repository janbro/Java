package dataStructures;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class Alphabetical {
	
	public static void main(String[] args) throws IOException{
		System.out.println(checkAlpha("index.txt"));
	}
	static boolean checkAlpha(String file) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(file));
		Stack<String> myStack = new Stack<String>();
		String currentWord;
		while((currentWord = br.readLine())!=null)
		{
			System.out.println(myStack.toString());
			if(myStack.isEmpty()) //Check if stack is empty
				myStack.add(currentWord);
			else if(currentWord.lastIndexOf("\t")>=myStack.peek().lastIndexOf("\t")){ //Check if the indent at current word is greater than or equal to the next indent
				myStack.add(currentWord);
			}
			else{ //Else, we've gone back down an indent and want to check the previous level
				String temp = currentWord; //Save trigger word so we don't lose it when popping the previous level
				System.out.println("trigger:"+currentWord);
				while((currentWord=myStack.pop()).lastIndexOf("\t")==myStack.peek().lastIndexOf("\t")){ //Keep checking alpha order while we're at the same indent level
					System.out.print("Check:"+currentWord+" >= "+myStack.peek());
					if(!myStack.isEmpty()&&currentWord.compareTo(currentWord = myStack.peek())<0){ //If the first word on the stack is less than the next word on the stack alphabetically, then the list is out of order and we're done!
						System.out.println(" <-FALSE!");
						return false;
					}System.out.println(" <-TRUE");
				}
				System.out.println("Add trigger:"+temp);
				myStack.add(temp); //Add back that trigger word
			}
		}
		return true; //No issues! List is ordered!
	}

}
