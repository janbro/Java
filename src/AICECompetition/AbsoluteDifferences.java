package AICECompetition;

import java.io.PrintWriter;
import java.util.Scanner;

public class AbsoluteDifferences {

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		String input;
		int times = sc.nextInt();
		for(int i=0;i<times;i++){
			int nums[] = {sc.nextInt(),sc.nextInt(),sc.nextInt(),sc.nextInt()};
			boolean allEqual = false;
			int count = 0;
			if(nums[0]==nums[1]&&nums[1]==nums[2]&&nums[2]==nums[3])
				allEqual=true;
			while(!allEqual)
			{
				int first = nums[0];
				nums[0] = Math.abs(nums[0]-nums[1]);
				nums[1] = Math.abs(nums[1]-nums[2]);
				nums[2] = Math.abs(nums[2]-nums[3]);
				nums[3] = Math.abs(nums[3]-first);
				if(nums[0]==nums[1]&&nums[1]==nums[2]&&nums[2]==nums[3])
					allEqual=true;
				count++;
			}pw.println(count+" "+nums[0]);
		}
		pw.flush();
	}
	
}
