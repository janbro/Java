package googleCodeJam;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Scanner;

public class ProblemAMagicTrick {
	
	public static void main(String[] args){
		PrintWriter pw = new PrintWriter(System.out);
		Scanner buffer = new Scanner(System.in);//new BufferedReader(new InputStreamReader(System.in));
		int inputTimes = Integer.parseInt(buffer.nextLine());
		
		for(int i=0;i<inputTimes;i++){
			int rownum = Integer.parseInt(buffer.nextLine());
			String[] row = null;
			for(int k=0;k<rownum;k++){
				row = buffer.nextLine().split(" ");
			}for(int k=rownum;k<4;k++){
				buffer.nextLine();
			} rownum = Integer.parseInt(buffer.nextLine());
			String[] row2 = null;
			for(int k=0;k<rownum;k++){
				row2 = buffer.nextLine().split(" ");
			}for(int k=rownum;k<4;k++){
				buffer.nextLine();
			}
			int equal=0;
			int card=0;
			for(int j=0;j<4;j++){
				for(int k=0;k<4;k++){
					if(row[j].equals(row2[k])){
						equal++;
						card = Integer.parseInt(row[j]);
					}
				}
			}
			pw.print("Case #"+(i+1)+": ");
			if(equal==1)
				pw.println(card);
			else if(equal==0)
				pw.println("Volunteer cheated!");
			else if(equal>1)
				pw.println("Bad magician!");
		}
		pw.flush();
	}

}
