package Java;

import static Java.Utilities.*;

public class Statistics {
	public static void main(String[] args){
//		print(getMean(new int[]{10,18,17}));
//		print(getMean(new int[]{10,15}));
		print(getMode(new int[]{10,10,13,17,17,17,18,21}));
		print(getMode(new int[]{54,54,54,60,60,70,85,85}));
		print(getMode(new int[]{20,20,20,30,30,30,40,40,40}));
		print(getMode(new int[]{50}));
	}
	public static int getSum(int[] list){
		int sum=0;
		for(int n:list)
			sum+=n;
		return sum;
	}
	public static double getMean(int[] list){
		return getSum(list)/(double)list.length;
	}
	public static int getMode(int[] list){
		sortList(list);
		int best=list[0];
		int numBest=0;
		int current=list[0];
		int numCurrent=0;
		for(int n:list){
			if(n == current)
				numCurrent++;
			else{
				numCurrent=1;
				current=n;
			}
			if(numCurrent > numBest){
				best=current;
				numBest=numCurrent;
			}
		}
		return best;
	}
	public static void sortList(int[] list){
		int minI=0;
		for(int oi=0;oi<list.length;oi++){
			for(int i=oi;i<list.length;i++)
				if(list[i]<list[minI])
					minI=i;
			int temp=list[oi];
			list[oi]=list[minI];
			list[minI]=temp;
		}
	}
}
