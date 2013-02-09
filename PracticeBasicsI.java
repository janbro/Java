package Java;

import java.util.ArrayList;

public class PracticeBasicsI {
	
	public static void main(String[] args){
		int[] a = FunnyNumbers(5,0,10);
		for(int i=0;i<a.length;i++){
			System.out.println(a[i]);
		}
		System.out.println(RemoveDuplicates(a));
	}
	
	public static int[] FunnyNumbers(int amt, int a, int b){
		int[] res = new int[amt];
		for(int i=0;i<res.length;i++){
			res[i]=(int)(Math.random()*(b-a));
		}
		return res;
	}
	
	public static ArrayList<Integer> RemoveDuplicates(int[] input){
		ArrayList<Integer> res = new ArrayList<Integer>(input[0]);
		for(int i=0;i<input.length;i++){
			if(!res.contains(input[i])){
				res.add(input[i]);
			}
		}
		return res;
	}
	
	public static int AddNumbers(int[] input){
		int res=0;
		for(int i=0;i<input.length;i++){
			res+=input[i];
		}
		return res;
	}
	
	public static void PrintNums(int[] input){
		for(int i=0;i<input.length;i++){
			System.out.println(input[i]);
		}
	}
	
	public static void PrintNums(ArrayList<Integer> input){
		for(int i=0;i<input.size();i++){
			System.out.println(input.get(i));
		}
	}
	
	
	
}
