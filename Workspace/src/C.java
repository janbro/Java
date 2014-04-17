
public class C {
	
	public static void main(String[] args){
		int n=10;
		int k=4;
		System.out.println(Choose(n,k));
		System.out.println(c2(n,k));
	}
	
	public static int Choose(int n,int k){
		if(k==0||k==n)
			return 1;
		return Choose(n-1,k-1)+Choose(n-1,k);
	}
	public static int c2(int n,int k){
		if(k==0 || k==n)
			return 1;
		return n*c2(n-1,k-1)/k;
	}

}
