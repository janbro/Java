import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Problem2 {
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		int time=Integer.parseInt(buffer.readLine());
		for(int i=0;i<time;i++){
		String as[]=buffer.readLine().split(" ");
		int x=Integer.parseInt(as[0]),y=Integer.parseInt(as[1]);
		float myPoint[]={0,0};
		float center[]={x,y};
		float r=5f;
		double vX = myPoint[0] - center[0];
		double vY = myPoint[1] - center[1];
		double magV = Math.sqrt(vX*vX + vY*vY);
		double aX = center[0] + vX / magV * r;
		double aY = center[1] + vY / magV * r;
		aX = (double) Math.round(aX * 10000) / 10000;
		aY = (double) Math.round(aY * 10000) / 10000;
		String ox=""+aX;
		String oy=""+aY;
		if(((int)aX)==0){
			if(aX<0)
				ox=ox.substring(0,1)+ox.substring(2);
			else
				ox=ox.substring(1);
		}
		if(((int)aY)==0){
			if(aY<0)
				oy=oy.substring(0,1)+oy.substring(2);
			else
				oy=oy.substring(1);
		}for(int k=4;ox.substring(ox.indexOf(".")+1).length()<4;k--){
			ox+="0";
		}for(int k=4;oy.substring(oy.indexOf(".")+1).length()<4;k--){
			oy+="0";
		}
		pw.println("("+ox+", "+oy+")");
		}pw.flush();
	}
}
