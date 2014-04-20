package googleCodeJam;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ProblemDDeceitfulWar {
	
	public static void main(String[] args){
		PrintWriter pw = new PrintWriter(System.out);
		Scanner buffer = new Scanner(System.in);//new BufferedReader(new InputStreamReader(System.in));
		int inputTimes = Integer.parseInt(buffer.nextLine());
		
		for(int i=0;i<inputTimes;i++){
			double[][] nums = new double[2][Integer.parseInt(buffer.nextLine())];
			for(int k=0;k<2;k++){
				String input = buffer.nextLine();
				String[] input2 = input.split(" ");
				for(int j=0;j<input2.length;j++){
					nums[k][j]=Double.parseDouble(input2[j]);
					//System.out.print(nums[k][j]+",");
				}//System.out.println();
			}
			/*int score1 = 0;
			for(int f=0;f<nums[0].length;f++){
				if(nums[0][f]>nums[1][f])
					score1++;
				System.out.println(score1+" "+nums[0][f]+">?"+nums[1][f]);
			}*/pw.print("Case #"+(i+1)+": ");
			Arrays.sort(nums[0]);
			Arrays.sort(nums[1]);
			ArrayList<Double> naomi = new ArrayList<Double>();
			ArrayList<Double> naomi2 = new ArrayList<Double>();
			for(int l=0;l<nums[0].length;l++){
				naomi.add(nums[0][l]);
				naomi2.add(nums[0][l]);
			}
			ArrayList<Double> ken = new ArrayList<Double>();
			ArrayList<Double> ken2 = new ArrayList<Double>();
			for(int l=0;l<nums[0].length;l++){
				ken.add(nums[1][l]);
				ken2.add(nums[1][l]);
			}
			//System.out.println(naomi.toString());
			//System.out.println(ken.toString());
			int tot=naomi.size();
			int score = 0;
			int score2 = 0;
			while(naomi2.size()>0){
				int index=0;
				while(naomi2.get(0)>ken2.get(index)&&ken2.size()-1>index)
					index++;
				if(naomi2.get(0)>ken2.get(index))
					score2++;
				naomi2.remove(0);
				ken2.remove(index);
				
			}
			while(ken.size()>0){
				if(naomi.get(0)<ken.get(0)){
					naomi.remove(0);
					ken.remove(ken.size()-1);
				}else if(naomi.get(0)>ken.get(0)){
					naomi.remove(0);
					ken.remove(0);
					score++;
				}
			}pw.println(score+" "+score2);
		}pw.flush();
	}

}
