package HSCTF;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class WallOfNorthal {
	
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
			ArrayList<Applicant> applicants =new ArrayList<Applicant>();
			for(Applicant l:appls){
				applicants.add(l);
			}
			System.out.println("Cases Generated Made");
			System.out.println(traverse(applicants,applicants.get(0).getEnd(),0));
		}br.close();
		System.out.println("LEAST: "+least);
	}
	
	public static int traverse(ArrayList<Applicant> applicants,int end,int totPrice){
		while(!applicants.isEmpty()){
			if(applicants.get(0).getStart()==end){
				ArrayList<Applicant> newList = applicants;
				newList.remove(0);
				totPrice+=applicants.get(0).getPay();
				if(newList.size()==0||applicants.size()==0)
					return -1;
				return traverse(applicants,applicants.get(0).getEnd(),totPrice);
			}
			applicants.remove(0);
		}
		return totPrice;
	}
	
}
