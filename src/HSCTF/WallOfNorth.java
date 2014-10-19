package HSCTF;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;

public class WallOfNorth {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br= null;
		int least=Integer.MAX_VALUE;
		for(int j=0;j<51;j++){
			br = new BufferedReader(new FileReader(new File(System.getProperty("user.dir")+"/src/HSCTF/WallOfNorthCases.txt")));
			String line = "";
			PriorityQueue<Applicant> appls = new PriorityQueue<Applicant>();
			while((line = br.readLine())!=null){
				appls.add(new Applicant(Integer.parseInt(line.substring(0,line.indexOf(" "))),Integer.parseInt(line.substring(line.indexOf(" ")+1,line.lastIndexOf(" "))),Integer.parseInt(line.substring(line.lastIndexOf(" ")+1))));
			}
			System.out.println("Cases Generated Made");
			int totalPrice = 0;
			for(int i=0;i<j;i++){
				appls.poll();
			}boolean flag=false;
			while(appls.peek() != null)
			{
				System.out.println("Start"+appls.peek().getStart()+"END"+appls.peek().getEnd());
				totalPrice +=appls.peek().getPay();
				if(appls.peek().getStart()==0)
					flag=true;
				Applicant cur = appls.poll();
				while(appls.peek() != null && appls.peek().getEnd() != cur.getStart())
				{
					appls.poll();
				}
			}
			if(totalPrice<least&&flag)
				least = totalPrice;
		}br.close();
		System.out.println("LEAST: "+least);
	}
}
