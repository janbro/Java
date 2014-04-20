package googleCodeJam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Scanner;

public class ProblemBCookieClickerAlpha {
	
	public static void main(String[] args) throws NumberFormatException, IOException{
		PrintWriter pw = new PrintWriter(System.out);
		Scanner buffer = new Scanner(System.in);//new BufferedReader(new InputStreamReader(System.in));
		int inputTimes = Integer.parseInt(buffer.nextLine());
		DecimalFormat df = new DecimalFormat("#.##########");
		String input = buffer.nextLine();
		System.out.println(inputTimes);
		for(int i=0;i<inputTimes;i++){
			String[] input2 = input.split(" ");
			double[] nums = new double[input2.length];
			for(int j=0;j<input2.length;j++){
				nums[j] = Double.parseDouble(input2[j]);
			}
			double cookiesMadePerSec = 2;
			double cookieFarmCost = nums[0];
			double cookiesAddedPerFarm = nums[1];
			double cookieWinNum = nums[2];
			double totalTime=0.0;
			while(timeToCookie(cookieFarmCost,cookiesMadePerSec)+timeToCookie(cookieWinNum,cookiesMadePerSec+cookiesAddedPerFarm)<timeToCookie(cookieWinNum,cookiesMadePerSec)){
				totalTime+=timeToCookie(cookieFarmCost,cookiesMadePerSec);
				cookiesMadePerSec+=cookiesAddedPerFarm;
			}
			totalTime+=timeToCookie(cookieWinNum,cookiesMadePerSec);
			pw.println("Case #"+(i+1)+": "+df.format(totalTime));
			System.out.println("GetInput");
			input = buffer.nextLine();
		}pw.flush();
	}
	
	public static double timeToCookie(double cookiesNeeded,double cookieRate){
		return cookiesNeeded/cookieRate;
	}

}
