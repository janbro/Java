package AICECompetition;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class SortByGeorge {
	static Hashtable<String,Integer> alph = new Hashtable<String, Integer>();

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		int times = sc.nextInt();
		for(int i=0;i<times;i++){
			ArrayList<String> all = new ArrayList<String>();
			alph.clear();
			sc.nextLine();
			String arr[]  = sc.nextLine().split("");
			int count=0;
			for(String letter:arr){
				alph.put(letter, count);
				count++;
			}
			int words = sc.nextInt();
			for(int k=0;k<words;k++){
				String word = sc.next();
				if(all.size()==0)
					all.add(word);
				else{
					for(int l=0;l<all.size();l++){
						if(compareTo(word,all.get(l))<0){
							all.add(l,word);
							break;
						}else if(l==all.size()-1){
							all.add(word);
							break;
						}
					}
				}
			}for(int p=0;p<all.size();p++)
				pw.println(all.get(p));
			if(i!=times-1)
				pw.println();
		}
		pw.flush();
	}
	
	public static int compareTo(String str1,String str2){
		if(str1.length()==0)
			return -1;
		else if(str2.length()==0)
			return 1;
		else if(str1.equals(str2))
			return 0;
		else if(str1.substring(0, 1).equals(str2.substring(0, 1)))
			return compareTo(str1.substring(1),str2.substring(1));
		else if((int)alph.get(str1.substring(0, 1))<(int)alph.get(str2.substring(0, 1))){
			return -1;
		}else
			return 1;
	}
	
}
