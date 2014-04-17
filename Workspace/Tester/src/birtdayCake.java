import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class birtdayCake {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		int[][] points;
		String input;
		boolean notGood=false;
		while(!(Integer.parseInt(input=br.readLine())==0)){
			int n = Integer.parseInt(input);
			points = new int[2*n][2];
			for(int i=0;i<points.length;i++){
				String[] temp = br.readLine().split(" ");
				points[i][0] = Integer.parseInt(temp[0]);
				points[i][1] = Integer.parseInt(temp[1]);
			}int count=0;
			int xLine = 0,yLine = 0;
			for(int i=0;i<points.length;i++)
				System.out.println(points[i][0]+" "+points[i][1]);
			for(int x=-500;x<=500;x++){
				for(int y=-500;y<=500;y++){
					for(int p=0;p<points.length;p++){
						xLine=x;
						yLine=y;
						if(x*points[p][0]+y*points[p][1]>0)
							count++;
						else if(x*points[p][0]+y*points[p][1]<0)
							count--;
						else{
							notGood=true;
							break;
						}
					}if(count==0&&!notGood){
						pw.println(xLine+" "+yLine);
						x=501;y=501;
						break;
					}count=0;
					notGood=false;
				}
			}
			
		}
		pw.flush();
		System.exit(0);
	}
}
