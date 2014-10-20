package dataStructures;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Stack;

import javax.swing.JEditorPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.EditorKit;

public class StacksnQueues {
	
	public static void main(String[] args) throws IOException, BadLocationException{
		JEditorPane p = new JEditorPane();
		p.setContentType("text/rtf");
		EditorKit rtfKit = p.getEditorKitForContentType("text/rtf");
		rtfKit.read(new FileReader("index.rtf"), p.getDocument(), 0);
		rtfKit = null;

		// convert to text
		EditorKit txtKit = p.getEditorKitForContentType("text/plain");
		Writer writer = new StringWriter();
		txtKit.write(writer, p.getDocument(), 0, p.getDocument().getLength());
		String documentText = writer.toString();
		System.out.println(checkStack(documentText));
	}
	static boolean checkStack(String documentText) throws FileNotFoundException, IOException, BadLocationException{
		
		Stack<String> stack = new Stack<String>();
		Stack<String> secondLvl = new Stack<String>();
		Stack<String> thirdLvl = new Stack<String>();
		String[] words = documentText.split("\n");
		//System.out.println(documentText+"::END");
		for(String wor:words){
			if(wor.startsWith(" ")){
				if(wor.substring(3).startsWith(" ")){
					thirdLvl.add(wor.replace(" ", "").replace("\n", ""));
				}
				else{
					if(!checkStack(thirdLvl))
						return false;
					secondLvl.add(wor.replace(" ", "").replace("\n", ""));
				}
			}
			else{
				if(!checkStack(secondLvl))
					return false;
				stack.add(wor.replace("\n", ""));
			}
		}
		if(!checkStack(secondLvl))
			return false;
		if(!checkStack(thirdLvl))
			return false;
		return checkStack(stack);
	}
	static boolean checkStack(Stack stack){
		if(stack.isEmpty())
			return true;
		String lastChar = ((String) stack.pop()).replace(" ", "");
		while(!stack.isEmpty()){
			if(lastChar.compareTo((lastChar = ((String) stack.pop())).replace(" ", ""))<0)
				return false;
		}
		return true;
	}

}
