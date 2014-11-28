package AICECompetition;

import java.io.PrintWriter;
import java.util.Scanner;

public class SavingsAccount {

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		String input;
		
		while(sc.hasNextInt()){
			int in = sc.nextInt();
			pw.print(in);
			int sum=0;
			int days=0;
			int count=1;
			while(days+count<in){
				sum+=(Math.pow(count,2));
				days+=count;
				count++;
			}sum+=(count*(in-days));
			pw.println(" $"+sum);
			pw.flush();
		}
		pw.flush();
	}
	
}
