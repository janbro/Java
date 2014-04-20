
import java.util.*;
import java.io.*;

public class VocabularyTest {
	private ArrayList<VocabularyWord> words;
	private int numCorrect;
	public VocabularyTest(ArrayList<String> arrayList) throws FileNotFoundException{
			words=new ArrayList<VocabularyWord>();
			ArrayList<String> str=new ArrayList<String>();
			File inputFile = new File("Vocabulary.txt");
			Scanner input = new Scanner(inputFile);
			while(input.hasNextLine()){
			str.add(input.nextLine());
			}
			processStrings(str);
			for(int i = 0; i<100; i++)
			swapWords();
			}
	private void processStrings(ArrayList<String> lines){
		String w="",d="";
		int x;
		for(int i=0; i<lines.size()-1;i++){
			x=lines.get(i).indexOf(":");
			w=lines.get(i).substring(0,x);
			d=lines.get(i).substring(x,lines.get(i).length());
			VocabularyWord word= new VocabularyWord(w,d);
			words.add(word);
		}
	}
	private void swapWords(){
		int x=(int)(Math.random()*(words.size()));
		int y=(int)(Math.random()*(words.size()));
		VocabularyWord w1= words.get(x);
		VocabularyWord w2= words.get(y);
		words.set(y,w1);
		words.set(x,w2);
	}
	public void quiz(){
		int x=0;
		while(x< words.size()){
			VocabularyWord t=words.get(x);
			System.out.println(t.getDefinition());
			Scanner kb= new Scanner(System.in);
			String p= kb.nextLine();
			if(p.equals(t.getWord()))
				numCorrect++;
			else 
				System.out.println(t.getWord());
			x++;
		}
		System.out.println(numCorrect);
	}
}