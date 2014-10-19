import java.util.ArrayList;


public class linkStrength {
	
	public static void main(String[] args){
		System.out.println(linkStrengh("Ripe","peach"));
		ArrayList<String> words = new ArrayList<String>();
		words.add("stop");
		words.add("top");
		words.add("place");
		words.add("wheat");
		words.add("eat");
		words.add("cake");
		keepFirstChain(words);
		System.out.println(words.toString());
	}
	
	public static void keepFirstChain(ArrayList<String> words){
		int i=0;
		while(linkStrengh(words.get(i),words.get(i+1))!=0)
			i++;
		for(int j=words.size()-1;j>i;j--)
			words.remove(j);
	}
	
	public static int linkStrengh(String word1, String word2){
		int x=0;
		for(int i=1; i<word1.length()-1; i++){
		if(word1.indexOf(word2.substring(0,i))>=0)
		x= x+1;
		}
		return x;
		}
}
