package competition;

import java.io.PrintWriter;
import java.util.Scanner;

public class Problem {
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		int inputAm=sc.nextInt();
		for(int i=0;i<inputAm;i++){
			String inp=sc.next();
			int[] in = {Integer.parseInt(inp),Integer.parseInt(sc.next())};
			int tot=in[0];
			int timetook=in[0];
			for(int j=1;j<in[1];j++){
				timetook=(int)(timetook*.9);
				tot=(int)(tot+(timetook));
			}
			pw.println((int)(tot));
		}pw.flush();
	}

}
