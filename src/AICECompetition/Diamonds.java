package AICECompetition;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Diamonds {

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		int times = sc.nextInt();
		for(int i=0;i<times;i++){
			 long seed = sc.nextLong();
			 Random rnd = new Random();
			 rnd.setSeed(seed);
			 ArrayList<Integer> diamonds = new ArrayList<Integer>();
			 int val = 15;
			 for(int j=0;j<14;j++){
				 diamonds.add(val);
				 val+=5;
			 }
			 int players[]={0,0};
			 int count=0;
			 while(diamonds.size()>0){
				 int flip = rnd.nextInt(2);
				 if(flip==0)
					 players[count%2] += diamonds.remove(diamonds.size()-1);
				 else
					 players[count%2] += diamonds.remove(0);
				 count++;
			 }
			 if(players[0]==players[1])
				 pw.println("TIE");
			 else if(players[0]>players[1])
				 pw.println("PLAYER A WON");
			 else
				 pw.println("PLAYER B WON");
			 
		}
		pw.flush();
	}
	
}
