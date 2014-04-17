package dataStructures;

public class asdf {

	public static void main(String[] args){
		int[] counts = new int[3];
		int i,j;
		for(i=0; i<100; i++){
			for(j=0; j<10; j++)
				counts[j%3]++;
		}
		System.out.println((counts[1]+counts[2])/counts[0]);
	}
}
