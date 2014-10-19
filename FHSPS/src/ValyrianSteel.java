import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class ValyrianSteel {
	
	public static void main(String[] args) throws NumberFormatException, IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pr = new PrintWriter(System.out);
		System.out.println(findWidth(77.54/100,3.78,7.6,12.3));
		int cases = Integer.parseInt(br.readLine());
		int casenum = 0;
		for(int i=0;i<cases;i++){
			casenum++;
			String stuff[] = br.readLine().split(" ");
			double ans;
			if(stuff[3].equals("L"))
				ans = findWidth(Double.parseDouble(stuff[2])/100.0,Double.parseDouble(stuff[4]),Double.parseDouble(stuff[0]),Double.parseDouble(stuff[1]));
			else
				ans = findLength(Double.parseDouble(stuff[2])/100.0,Double.parseDouble(stuff[4]),Double.parseDouble(stuff[0]),Double.parseDouble(stuff[1]));
			pr.print("Case "+casenum+": ");
			if(ans == 0.0)
				pr.printf("Impossible!");
			else{
			if(stuff[3].equals("L"))
				pr.printf("%.03f %.03f",Double.parseDouble(stuff[4]),ans);
			else
				pr.printf("%.03f %.03f",ans,Double.parseDouble(stuff[4]));
			}pr.println();
				
		}pr.flush();
	}
	
	static double findWidth(double p,double l2,double w1,double l1){
		double top = 2.0*Math.sqrt(3.0)*(Math.sqrt(Math.pow(l2,2)+p*(l1*w1+(Math.pow(w1,2)*Math.sqrt(3.0))/4.0)*Math.sqrt(3.0))-l2)/3.0;
		
		return top;
		
	}
	
	static double findLength(double p,double w2, double w1,double l1){
		double top = -(Math.sqrt(3.0)*Math.pow(w2,2)-4.0*p*(l1*w1+(Math.pow(w1,2)*Math.sqrt(3.0))/4.0))/(4.0*w2);
		
		return top;
	}

}
