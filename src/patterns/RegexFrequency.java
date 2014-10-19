package patterns;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexFrequency {

	public static void main(String[] args){
		String string = new Scanner(System.in).nextLine().toLowerCase();
		double[] letters = new double[26];
		int mostFreq=-1;
		double highestFreq=0.0;
		for(int i=0;i<26;i++){
			letters[i] = regexFrequency(String.valueOf((char)(97+i)),removeNonLetterChars(string).toLowerCase());
			System.out.println(((char)(97+i))+":"+letters[i]);
			if(letters[i]>highestFreq){
				mostFreq = i;
				highestFreq = letters[i];
			}
		}
		System.out.println(((char)(97+mostFreq))+" was the most frequent letter.("+letters[mostFreq]+"%)");
		System.out.println("Shift is "+(mostFreq-4));
		System.out.println(caesarShift(string,-(mostFreq-4)));
		//for(int i=1;i<26;i++)
		//	System.out.println("Shift "+i+": "+caesarShift(string,i));
	}
	
	public static double regexFrequency(String regex,String string){
		int totalChars = string.length();
		int count = 0;
		Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
		while (matcher.find()) {
            count++;
        }
		return (double)count/totalChars*100;
	}
	
	public static String removeNonLetterChars(String phrase){
		Pattern pattern = Pattern.compile("[a-z]");
        Matcher matcher = pattern.matcher(phrase);
        String res="";
		while (matcher.find()) {
            res+=phrase.substring(matcher.start(),matcher.end());
        }
		return res;
	}
	
	public static String caesarShift(String phrase, int shift){
		String res="";
		for(int i=0;i<phrase.length();i++){
			Pattern pattern = Pattern.compile("[a-z]");
			Matcher matcher = pattern.matcher(phrase.substring(i,i+1));
			if(matcher.find())
				res+=(char)(97+wrap(25,(int)phrase.charAt(i)-97,shift));
			else
				res+=phrase.substring(i,i+1);
		}return res;
	}
	
	private static int wrap(int max,int x,int shift){
		for(int i=0;i<Math.abs(shift);i++){
			if(shift<0)
				x--;
			else
				x++;
			if(x>max)
				x=0;
			if(x<0)
				x=max;
		}
		return x;
	}
}
