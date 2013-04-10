package Java;

import java.util.ArrayList;
import java.util.List;

public class ChainWords {
	public static void main(String[] args){
		System.out.println(linkStrength("wheat","toast"));
		System.out.println(linkStrength("ripe","peach"));
		System.out.println(linkStrength("wheat","eat"));
		System.out.println(linkStrength("sand","sandwich"));
		System.out.println(linkStrength("take","cake"));
		String[] a={"stop","top","place","wheat","eat","cake"};
		ArrayList<String> words=new ArrayList<String>();
		for(String b:a)
			words.add(b);
		keepFirstChain(words);
		for(String b:words)
			System.out.println(b);
	}
	public static int linkStrength(String word1,String word2){
		int strength=0;
		for(int i=0;i<word1.length() && i<word2.length();i++){
			if(word1.substring(word1.length()-1-i).equals(word2.substring(0,i+1)))
				strength=i+1;
		}
		return strength;
	}
	public static void keepFirstChain(List<String> words){
		boolean broken=false;
		for(int i=1;i<words.size();i++){
			if(linkStrength(words.get(i-1),words.get(i)) <= 0)
				broken=true;
			if(broken){
				words.remove(i);
				i--;
			}
		}
	}
}