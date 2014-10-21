import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EnglishWordReader {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(new File(System.getProperty("user.dir")+"/src/Eng2.txt")));
		String line = "";
		ArrayList<String> engDict = new ArrayList<String>();
		while((line = br.readLine())!=null){
			if(line.length()>5)
				engDict.add(line.toLowerCase());
		}
		System.out.println("Dictionary Made");
		BufferedReader br2 = new BufferedReader(new FileReader(new File(System.getProperty("user.dir")+"/src/inout.txt")));
		ArrayList<String> input = new ArrayList<String>();
		while((line = br2.readLine())!=null){
			input.add(line);
		}
		System.out.println("Input done");
		ArrayList<String> foundWords = new ArrayList<String>();
		ArrayList<Integer> endIndex = new ArrayList<Integer>();
		for(String str:input){
			for(String str2:str.split(" "))
				for(String check:engDict){
					Pattern p = Pattern.compile(check);
					Matcher m = p.matcher(str2);
					while(m.find()){
						String word = "";
						int ind= -1;
						int sind = -1;
						//System.out.println("Found "+(word=m.group())+" at "+(sind=m.start())+" to "+(ind=m.end()));
						word=m.group();
						sind=m.start();
						ind=m.end();
						foundWords.add(word);
						endIndex.add(ind);
						for(int i=0;i<foundWords.size();i++){
							if(sind-endIndex.get(i)>0&&sind-endIndex.get(i)<15)
								System.out.println(foundWords.get(i)+":"+str2.substring(endIndex.get(i),sind));
						}
					}
					//if(str2.contains(check))
					//	System.out.println(str2);
					
				}
		}
		
	}
	
	public static int indexOfEnglish(String in){
		int start = 0;
		Pattern p = Pattern.compile("[A-Za-z]*"); //English dictionary
		Matcher m = p.matcher(in);
		while(m.find()){
			//System.out.println("Found "+m.group()+" at "+m.start()+" to "+m.end());
			start = m.start();
		}
		return start;
	}

}
