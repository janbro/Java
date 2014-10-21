package patterns;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTester {
	
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		while(true){
			System.out.print("Enter regex: ");
			Pattern p = Pattern.compile(scanner.nextLine());
			System.out.print("Enter string: ");
			Matcher m = p.matcher(scanner.nextLine());
			boolean found = false;
			while(m.find()){
				System.out.println("Found "+m.group()+" at "+m.start()+" to "+m.end());
				found = true;
			}if(!found){
				System.out.println("No match found.");
			}System.out.println();
		}
	}
}