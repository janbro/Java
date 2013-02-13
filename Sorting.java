package Java;

public class Sorting {
	public static void main(String[] args){
		int[] a={1,2,3,4,5,6,7,8,9,0};
	}
	public static void InsertionSort(int[] num){
		int j;           // the number of items sorted so far
		int key;         // the item to be inserted
		int i;  
		for(j=1;j<num.length;j++){   // Start with 1 (not 0)
			key=num[j];
			for(i=j-1;(i>=0)&&(num[i]<key);i--)   // Smaller values are moving up
				num[i+1]=num[i];
			num[i+1]=key;  // Put the key in its proper location
	     }
	}
	public static void selectionSort(int[] x){
	    for(int i=0;i<x.length-1;i++){
	        int minIndex=i;    // Index of smallest remaining value.
	        for(int j=i+1;j<x.length;j++)
	            if(x[minIndex]>x[j])
	                minIndex=j;  // Remember index of new minimum
	        if(minIndex!=i){ 
	            //...  Exchange current element with smallest remaining.
	            int temp=x[i];
	            x[i]=x[minIndex];
	            x[minIndex]=temp;
	        }
	    }
	}
	
	public static void mergeSort(int[] a){
		if(a.length <= 1)
			return;
		int first[]=new int[a.length/2];
		int second[]=new int[a.length-first.length];
		for(int x=0;x<first.length;x++)
			first[x]=a[x];
		for(int x=first.length,y=0;x<a.length;x++,y++)
			first[y]=a[x];
		//Split the array again
		mergeSort(first);
		mergeSort(second);
		merge(a,first, second);
	}
	private static void merge(int[] a,int[] first, int[] second){
		int x=0;
		int y=0;
		int z=0;
		while(x<first.length&&y<second.length){
			if(first[x]<second[y]){
				a[z]=first[x];
				x++;
			} else{
				a[z]=second[y];
				y++;
			}
			z++;	
		}
		//copy remaining elements to the tail of a[];
		for(int i=x;i<first.length;i++){
			a[z]=first[i];
			z++;
		}
		for(int i=y;i<second.length;i++){
			a[z]=second[i];
			z++;
		}
	}
}
