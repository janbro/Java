package AICE;

import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class Phones {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		String input;
		int times = Integer.parseInt(sc.nextLine());
		for(int i=0;i<times;++i){
			input = sc.nextLine();
			if(matchCase(input,"\\d{3}-\\d{3}-\\d{4}")){
				if(input.split("-")[0].length()==3&&input.split("-")[1].length()==3&&input.split("-")[2].length()==4)
					pw.println(input);
				else
					pw.println("INVALID PHONE NUMBER");
			}else
				pw.println("INVALID PHONE NUMBER");
		}pw.flush();
	}

	public static boolean matchCase(String search,String regex){
		Pattern pattern = 
				Pattern.compile(regex);

		Matcher matcher = 
				pattern.matcher(search);

		boolean found = false;
		while (matcher.find()) {

			matcher.group();
			matcher.start();
			matcher.end();
			found = true;
		}
		if(!found){
			return false;
		}else
			return true;
	}

}
