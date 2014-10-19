import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class IronBank {
	
	public static void main(String[] args) throws NumberFormatException, IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pr = new PrintWriter(System.out);
		int numArrs = 0;
		int cas=0;
		while((numArrs = Integer.parseInt(br.readLine()))!=0){
			int a=0;
			int b=0;
			cas++;
			for(int i=0;i<numArrs;i++){
				String[] army = br.readLine().split(" ");
				a+=Integer.parseInt(army[0]);
				b+=Integer.parseInt(army[1]);
			}
			pr.print("Case "+cas+":");
			if(b>a)
				pr.println(" We should support King B, with a total of "+b+" troop(s). King A only has a total of "+a+" troop(s).");
			else if(a>b)
				pr.println(" We should support King A, with a total of "+a+" troop(s). King B only has a total of "+b+" troop(s).");
			else
				pr.println(" Both kings can bring a force of "+a+" troop(s)!");
		}pr.flush();
		
	}

}
