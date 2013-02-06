package pakage;

import java.util.ArrayList;

public class PracticeBasicsII {
  
	public static void main(String[] args){
		int[] a = {1,5,4,4,3};
		FunnyNumbers();
		a=(AvgNums(a));
		for(int i=0;i<a.length;i++){
			System.out.println(a[i]);
		}
		Higher(a);
		PracticeBasicsI.PrintNums(a);
		System.out.println(OddNums(a));
	}
	
	public static int[] FunnyNumbers(){
		int[] nums = new int[35];
		for(int i=0;i<nums.length;i++){
			nums[i]=((int)(Math.random()*380))-200;
			System.out.println(nums[i]);
		}
		for(int i=0;i<nums.length;i++){
			nums[i]=Math.abs(nums[i]);
		}
		return nums;
	}
	
	public static int[] AvgNums(int[] input){
		int avg=0;
		for(int i=0;i<input.length;i++){
			avg+=input[i];
		}avg/=input.length;
		input[0]=avg;
		input[input.length-1]=avg;
		return input;
	}
	
	public static void Higher(int[] input){
		int sum=0;
		for(int i=1;i<input.length;i++){
			if(input[i-1]>input[i]){
				input[i]=21;
			}sum+=input[i];
		}
		System.out.println(sum);
	}
	
	public static ArrayList<Integer> OddNums(int[] nums){
		ArrayList<Integer> res = new ArrayList<Integer>();
		for(int i=0;i<nums.length;i++){
			if(nums[i]%2==1&&!res.contains(nums[i])){
				res.add(nums[i]);
			}
		}
		return res;
	}

}
