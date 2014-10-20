
public class GFC {
	public static void main(String[] args){
		System.out.println(GCF(27,12));
	}
	
	static int GCF(int m,int n){
		if(m%n!=0)
			return GCF(m,m%n);
		else
			return n;
	}

}
